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

	private MksChildsPage mksErr;

	private ArrayList<Motor>     motors;
	private ArrayList<SapInput>  sapInputs;
	private ArrayList<SapOutput> sapOutputs;
	private ArrayList<Page>      pages;

	public HashMap<String, ArrayList<Motor>> steeringsWithMotors;
	public HashMap<String, String> prettyPrintSteerings;

	private ArrayList<PlcMain> plcMains;

	public Fps(String name) throws FileNotFoundException {
		super(new PdfWriter(name + ".pdf"));

		this.name = name;

		this.motors     = new ArrayList<Motor>();
		this.sapInputs  = new ArrayList<SapInput>();
		this.sapOutputs = new ArrayList<SapOutput>();
		this.pages      = new ArrayList<Page>();
		this.plcMains   = new ArrayList<PlcMain>();

		this.steeringsWithMotors  = new HashMap<String, ArrayList<Motor>>();
        this.prettyPrintSteerings = new HashMap<String, String>();

		System.out.println("Utworzono rozdzielnice: " + name);
	}

	public String name() {
		return this.name;
	}

	public void draw() {
		int mkls       = (int) Math.ceil(sapInputs.size() / 4); // 4 sygnały SAP to 8 w PLC
		int plcInputs  = mkls * 8;
		int plcOutputs = sapOutputs.size() + this.prettyPrintSteerings.size();

		int plcMainsNumber = 0;
		int temp = 4;
		while (temp < plcOutputs) {
			plcMainsNumber++;
			temp += 8;
		}

		temp = plcMainsNumber * 8;
		while (temp < plcInputs) {
			plcMainsNumber++;
			temp += 8;
		}

		PlcMainPage plcPage = new PlcMainPage(this, "Cpu");
		this.plcMains.add(plcPage.plcMain());
		pages.add(plcPage);

		temp = 0;
		while (temp < plcMainsNumber) {
			PlcMainPage page = new PlcMainPage(this, "Module");
			this.plcMains.add(page.plcMain());
			pages.add(page);
			temp++;
		}

		Iterator<String> s = this.steerings().iterator();

		while (s.hasNext()) {
			ArrayList<String> patch = new ArrayList<String>();

			for (int i = 0; i < MAX_PLC_STEERING_AT_PAGE; i++) {
				if (s.hasNext())
					patch.add(s.next());
				else
					break;
			}

			pages.add(new MotorsSteeringsPage(this, patch));
		}

		int toFetch = MKL_INPUTS - sapInputs.size() % MKL_INPUTS;
		for (int i = 0; i < toFetch; i++)
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

			pages.add(new MklPage(this, patch));
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

		for (Motor m: motors)
			if (m.steering1 == null || m.steering2 == null)
				throw new RuntimeException("Brak sterowania dla  " + m.name + " w matrycy sterowań.");
			else
				pages.add(m.page());

		for(Page p: pages) {
			if (p.mks() != null) {
				if (mksErr == null)
					mksErr = new MksChildsPage(this);

				mksErr.addMksChild(p.mks());
			}
		}

		pages.add(mksErr);

		targets();

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

	public void add(Motor m) {
		this.motors.add(m);
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

	public pl.com.cs.schema.main.PlcMain nextInput() {
		for (pl.com.cs.schema.main.PlcMain p: plcMains)
			if (p.input() > 0)
				return p;

		throw new RuntimeException("Run out of plc's for inputs.");
	}

	public pl.com.cs.schema.main.PlcMain nextOutput() {
		for (pl.com.cs.schema.main.PlcMain p: plcMains)
			if (p.output() > 0)
				return p;

		throw new RuntimeException("Run out of plc's for outputs.");
	}

	public int nextValueOf(String g) {
		if (group.containsKey(g))
			group.put(g, group.get(g)+1);
		else
			group.put(g, 1);

		return group.get(g);
	}

	public void addSteering1B(Motor m, String k) {
		String key = pretty1B(k);

		if (steeringsWithMotors.containsKey(key))
			steeringsWithMotors.get(key).add(m);
		else
			steeringsWithMotors.put(key, new ArrayList<Motor>(Arrays.asList(m)));

		Potentials.add(new Potential(key, 100f, 1800f));
		m.steering1(key);
	}

	public void addSteering2B(Motor m, String k) {
		String key = pretty2B(k);

		if (steeringsWithMotors.containsKey(key))
			steeringsWithMotors.get(key).add(m);
		else
			steeringsWithMotors.put(key, new ArrayList<Motor>(Arrays.asList(m)));

		Potentials.add(new Potential(key, 100f, 1900f));
		m.steering2(key);
	}

	public void addSteeringL(Motor m, String k) {
		String key = prettyL(k);

		if (steeringsWithMotors.containsKey(key))
			steeringsWithMotors.get(key).add(m);
		else
			steeringsWithMotors.put(key, new ArrayList<Motor>(Arrays.asList(m)));

		Potentials.add(new Potential(key, 100f, 2000f));
		m.steeringL(key);
	}

	public void addSteeringR(Motor m, String k) {
		String key = prettyR(k);

		if (steeringsWithMotors.containsKey(key))
			steeringsWithMotors.get(key).add(m);
		else
			steeringsWithMotors.put(key, new ArrayList<Motor>(Arrays.asList(m)));

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
