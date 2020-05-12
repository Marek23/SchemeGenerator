package pl.com.hom.board;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import pl.com.hom.connections.Point;
import pl.com.hom.data.Reader;
import pl.com.hom.data.Signal;
import pl.com.hom.page.MksErrors;
import pl.com.hom.page.Page;
import pl.com.hom.page.Plc;
import pl.com.hom.page.Steering;

public class Board extends PdfDocument {
	private static final long serialVersionUID = 1L;
	
	private static final long MAX_PLC_SIGNALS_AT_PAGE = 8;
	private String name;
	private String current;
	private String power;
	private String cable;

	private MksErrors mksErr;

	private ArrayList<Receiver> receivers;
	private ArrayList<Signal>   sapInput;
	private ArrayList<Signal>   sapOutput;
	private ArrayList<Page>     pages;

	private ArrayList<pl.com.hom.element.main.Plc> plcs;

	public Board(String name) throws FileNotFoundException {
		super(new PdfWriter(name + ".pdf"));

		this.name = name;

		this.receivers = new ArrayList<Receiver>();
		this.sapInput  = new ArrayList<Signal>();
		this.sapOutput = new ArrayList<Signal>();
		this.pages = new ArrayList<Page>();
		this.plcs  = new ArrayList<pl.com.hom.element.main.Plc>();
	}

	public String name() {
		return this.name;
	}

	public void draw() {
		int mkls      = (int) Math.ceil(sapInput.size()/4);
		int sapInputs = mkls * 8;

		int plcInputs = sapInputs;
		int plcOutputs = sapOutput.size() + Reader.steerings(this.name).size();

		int modules = 0;
		int temp = 4;
		while (temp < plcOutputs) {
			modules++;
			temp += 8;
		}

		temp = 8 + modules * 8;
		while (temp < plcInputs) {
			modules++;
			temp += 8;
		}

		Plc plcPage = new Plc(this, modules);
		this.plcs = plcPage.plcs();
		pages.add(plcPage);

		
		Iterator<String> s = Reader.steerings(this.name).iterator();

		while(s.hasNext()) {
			ArrayList<String> patch = new ArrayList<String>();

			for (int i = 0; i < MAX_PLC_SIGNALS_AT_PAGE; i++) {
				if (s.hasNext())
					patch.add(s.next());
				else
					break;
			}

			pages.add(new Steering(this, patch));
		}

		for (Receiver r: receivers)
			pages.add(r.page());

		for(Page p: pages) {
			if (p.mks() != null) {
				if (mksErr == null)
					mksErr = new MksErrors(this);

				mksErr.add(p.mks());
			}
		}

		pages.add(mksErr);

		targets();

		for(Page p: pages)
			p.draw();

		this.close();
	}

	public void addSapInput(Signal signal) {
		this.sapInput.add(signal);
	}

	public void addSapOutput(Signal signal) {
		this.sapOutput.add(signal);
	}

	public void add(Receiver receiver) {
		this.receivers.add(receiver);
	}

	public void show() {
		System.out.println(name);
		System.out.println("SIGNALS");
		System.out.println("Receivers");
		for (Receiver r: receivers)
			System.out.println(r.toString());
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

	public pl.com.hom.element.main.Plc nextInput() {
		for (pl.com.hom.element.main.Plc p: plcs)
			if (p.input() > 0)
				return p;

		throw new RuntimeException("Run out of plc modules for inputs.");
	}

	public pl.com.hom.element.main.Plc nextOutput() {
		for (pl.com.hom.element.main.Plc p: plcs)
			if (p.output() > 0)
				return p;

		throw new RuntimeException("Run out of plc modules for outputs.");
	}
}
