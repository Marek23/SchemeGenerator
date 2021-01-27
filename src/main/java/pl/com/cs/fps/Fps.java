package pl.com.cs.fps;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.HashMap;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import pl.com.cs.schema.Point;
import pl.com.cs.schema.Potential;
import pl.com.cs.schema.Potentials;
import pl.com.cs.schema.main.PlcMain;
import pl.com.cs.schema.page.FirstPage;
import pl.com.cs.schema.page.FuseChildsPage;
import pl.com.cs.schema.page.MklPage;
import pl.com.cs.schema.page.MksChildsPage;
import pl.com.cs.schema.page.Page;
import pl.com.cs.schema.page.PlcMainPage;
import pl.com.cs.schema.page.SapOutputsPage;
import pl.com.cs.schema.page.MotorsSteeringsPage;

public class Fps extends PdfDocument {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Integer> group = new HashMap<String, Integer>();

	private static final int MAX_PLC_STEERING_AT_PAGE = 10;
	private static final int MKL_INPUTS = 4;
	private static final float SAP_OUTPUTS_AT_PAGE = 5;

	private String name;

	private MksChildsPage mksChildsPage;

	private ArrayList<Executable> executables;
	private ArrayList<SapInput>   sapInputs;
	private ArrayList<SapOutput>  sapOutputs;
	private ArrayList<Page>       pages;
	private ArrayList<Page>       plcPages;
	private ArrayList<Page>       motorSteeringsPages;
	private ArrayList<Page>       mklPages;

	private ArrayList<FuseChildsPage> fuseChildsPages;

	public HashMap<String, ArrayList<Executable>> steeringsWithMotors;
	public HashMap<String, String> prettyPrintSteerings;

	private ArrayList<PlcMain> plcMains;

	public Fps(String name) throws FileNotFoundException {
		super(new PdfWriter(name + ".pdf"));

		this.name = name;

		this.executables = new ArrayList<Executable>();
		this.sapInputs  = new ArrayList<SapInput>();
		this.sapOutputs = new ArrayList<SapOutput>();
		this.pages      = new ArrayList<Page>();
		this.plcPages   = new ArrayList<Page>();
		this.plcMains   = new ArrayList<PlcMain>();

		this.motorSteeringsPages = new ArrayList<Page>();
		this.mklPages            = new ArrayList<Page>();

		this.mksChildsPage   = new MksChildsPage(this);
		this.fuseChildsPages = new ArrayList<FuseChildsPage>();

		this.steeringsWithMotors  = new HashMap<String, ArrayList<Executable>>();
        this.prettyPrintSteerings = new HashMap<String, String>();

		System.out.println("Utworzono rozdzielnice: " + name);
	}

	public String name() {
		return this.name;
	}

	public void draw() {
		var firstPage = new FirstPage(this);

		this.plcMains.add(new PlcMain("Cpu"));
		this.plcPages.add(new PlcMainPage(this, this.plcMains.get(0)));

		Iterator<String> s = this.steerings().iterator();
		while (s.hasNext()) {
			ArrayList<String> patch = new ArrayList<String>();

			for (int i = 0; i < MAX_PLC_STEERING_AT_PAGE; i++) {
				if (s.hasNext())
					patch.add(s.next());
				else
					break;
			}

			motorSteeringsPages.add(new MotorsSteeringsPage(this, patch));
		}

		int freeInputs = MKL_INPUTS - sapInputs.size() % MKL_INPUTS;
		for (int i = 0; i < freeInputs; i++)
			new SapInput(this, "Rezerwa");

		Iterator<SapInput> sapIn = sapInputs.iterator();
		while (sapIn.hasNext()) {
			ArrayList<SapInput> patch = new ArrayList<SapInput>();

			for (int i = 0; i < MKL_INPUTS; i++) {
				if (sapIn.hasNext())
					patch.add(sapIn.next());
				else
					break;
			}

			mklPages.add(new MklPage(this, patch));
		}

		Iterator<SapOutput> sapOut = sapOutputs.iterator();

		while (sapOut.hasNext()) {
			ArrayList<SapOutput> patch = new ArrayList<SapOutput>();

			for (int i = 0; i < SAP_OUTPUTS_AT_PAGE; i++) {
				if (sapOut.hasNext())
					patch.add(sapOut.next());
				else
					break;
			}

			pages.add(new SapOutputsPage(this, patch));
		}

		for (Executable m: executables)
			if ((m.runMethod.equals("TWOGEAR") && (m.steering1 == null || m.steering2 == null)) ||
			   (!m.runMethod.equals("TWOGEAR") && m.steeringMain == null))
			{
				throw new RuntimeException("Brak sterowania dla  " + m.name + " w matrycy sterowań.");
			}
			else
				pages.add(m.page());


		targets();

		for (var plc: this.plcMains)
			plc.updateChildsByMainNr();

		for(Page p: pages)
			if (p.mks() != null)
				mksChildsPage.addMksChild(p.mks());

		pages.add(firstPage);
		pages.addAll(plcPages);
		pages.addAll(mklPages);
		pages.addAll(motorSteeringsPages);

		if (!mksChildsPage.isEmpty())				
			pages.add(mksChildsPage);

		for(Page p: pages)
			p.draw();

		this.close();
	}

	public void addSapInput(SapInput signal) {
		this.sapInputs.add(signal);
	}

	public void addSapOutput(SapOutput signal) {
		this.sapOutputs.add(signal);
	}

	public void add(Executable m) {
		this.executables.add(m);
	}

	private void targets() {
		for (int i = 0; i < pages.size()-1; i++) {
			Page from = pages.get(i);

			int j = i+1;
			while (j < pages.size() && from.pendingEnds()) {
				Page to = pages.get(j++);
				for (Point f: from.ends())
					for (Point t: to.begins())
						if (f.potential() == t.potential())
						{
							f.target(t);
							t.target(f);
						}
			}
		}

		for (Page p: pages)
			p.clearPendingEdges();
	}

	public PlcMain nextPlcMainForInput() {
		for (var p: this.plcMains)
			if (p.nextInputNumber() > 0)
				return p;

		var plc = new PlcMain("Module");
		this.plcMains.add(plc);
		this.plcPages.add(new PlcMainPage(this, plc));

		return plc;
	}

	public PlcMain nextPlcMainForOutput() {
		for (var p: this.plcMains)
			if (p.nextOutputNumber() > 0)
				return p;

				var plc = new PlcMain("Module");
		this.plcMains.add(plc);
		this.plcPages.add(new PlcMainPage(this, plc));

		return plc;
	}

	public int nextValueOf(String g) {
		if (group.containsKey(g))
			group.put(g, group.get(g)+1);
		else
			group.put(g, 1);

		return group.get(g);
	}

	public void addSteering1B(Executable m, String k) {
		String key = pretty1B(k);

		if (steeringsWithMotors.containsKey(key))
			steeringsWithMotors.get(key).add(m);
		else
			steeringsWithMotors.put(key, new ArrayList<Executable>(Arrays.asList(m)));

		Potentials.add(new Potential(key, 100f, 1800f));
		m.steering1(key);
	}

	public void addSteering2B(Executable m, String k) {
		String key = pretty2B(k);

		if (steeringsWithMotors.containsKey(key))
			steeringsWithMotors.get(key).add(m);
		else
			steeringsWithMotors.put(key, new ArrayList<Executable>(Arrays.asList(m)));

		Potentials.add(new Potential(key, 100f, 1900f));
		m.steering2(key);
	}

	public void addMainSteering(Executable m, String k) {
		String key = prettyMain(k);

		if (steeringsWithMotors.containsKey(key))
			steeringsWithMotors.get(key).add(m);
		else
			steeringsWithMotors.put(key, new ArrayList<Executable>(Arrays.asList(m)));

		Potentials.add(new Potential(key, 100f, 1900f));
		m.steeringMain(key);
	}

	public void addSteeringL(Executable m, String k) {
		String key = prettyL(k);

		if (steeringsWithMotors.containsKey(key))
			steeringsWithMotors.get(key).add(m);
		else
			steeringsWithMotors.put(key, new ArrayList<Executable>(Arrays.asList(m)));

		Potentials.add(new Potential(key, 100f, 2000f));
		m.steeringL(key);
	}

	public void addSteeringR(Executable m, String k) {
		String key = prettyR(k);

		if (steeringsWithMotors.containsKey(key))
			steeringsWithMotors.get(key).add(m);
		else
			steeringsWithMotors.put(key, new ArrayList<Executable>(Arrays.asList(m)));

		Potentials.add(new Potential(key, 100f, 2100f));
		m.steeringR(key);
	}

	private String pretty1B(String key) {
		if (!prettyPrintSteerings.containsKey(key)) {
			prettyPrintSteerings.put(key, "1BSt" + nextValueOf("1B"));
		}

		return prettyPrintSteerings.get(key);
	}

	private String pretty2B(String key) {
		if (!prettyPrintSteerings.containsKey(key)) {
			prettyPrintSteerings.put(key, "2BSt" + nextValueOf("2B"));
		}

		return prettyPrintSteerings.get(key);
	}

	private String prettyMain(String key) {
		if (!prettyPrintSteerings.containsKey(key)) {
			prettyPrintSteerings.put(key, "Z" + nextValueOf("Z"));
		}

		return prettyPrintSteerings.get(key);
	}

	private String prettyL(String key) {
		if (!prettyPrintSteerings.containsKey(key)) {
			prettyPrintSteerings.put(key, "LEW" + nextValueOf("LEW"));
		}
		
		return prettyPrintSteerings.get(key);
	}

	private String prettyR(String key) {
		if (!prettyPrintSteerings.containsKey(key)) {
			prettyPrintSteerings.put(key, "PRA" + nextValueOf("PRA"));
		}
		
		return prettyPrintSteerings.get(key);
	}

	public ArrayList<String> steerings() {
		ArrayList<String> out = new ArrayList<String>();
		for (Entry<String, String> e: prettyPrintSteerings.entrySet())
			out.add(e.getValue());

		return out;
	}
}
