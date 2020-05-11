package pl.com.hom.data;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import pl.com.hom.connections.Point;
import pl.com.hom.page.MksErrors;
import pl.com.hom.page.Page;
import pl.com.hom.page.Plc;

public class Board extends PdfDocument {
	private static final long serialVersionUID = 1L;

	private String name;
	private String current;
	private String power;
	private String cable;

	private MksErrors mksErr;

	private ArrayList<Receiver> receivers;
	private ArrayList<Signal>   sapInput;
	private ArrayList<Signal>   sapOutput;
	private ArrayList<Page>     pages;

	public Board(String name) throws FileNotFoundException {
		super(new PdfWriter(name + ".pdf"));

		this.name = name;

		this.receivers = new ArrayList<Receiver>();
		this.sapInput  = new ArrayList<Signal>();
		this.sapOutput = new ArrayList<Signal>();
		this.pages  = new ArrayList<Page>();
	}

	public String name() {
		return this.name;
	}

	public void draw() {
		int mkls      = (int) Math.ceil(sapInput.size()/4);
		int sapInputs = mkls * 8;

		int plcInputs = sapInputs;
		int plcOutputs = sapOutput.size() + Reader.steerings(this.name);

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

		pages.add(new Plc(this, modules));

		for (Receiver r: receivers)
			pages.add(r.page());

		targets();

		for(Page p: pages) {
			if (p.mks() != null) {
				if (mksErr == null)
					mksErr = new MksErrors(this);

				mksErr.add(p.mks());
			}
		}

		pages.add(mksErr);

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
	}
}
