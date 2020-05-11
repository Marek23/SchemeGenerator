package pl.com.hom.data;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import pl.com.hom.connections.Point;
import pl.com.hom.element.main.Mks;
import pl.com.hom.page.Errors;
import pl.com.hom.page.Page;

public class Board extends PdfDocument {
	private static final long serialVersionUID = 1L;

	private String name;
	private String current;
	private String power;
	private String cable;

	private ArrayList<Receiver> receivers;
	private ArrayList<Signal>   signals;
	private ArrayList<Page>     pages;

	public Board(String name) throws FileNotFoundException {
		super(new PdfWriter(name + ".pdf"));

		this.name = name;

		this.receivers = new ArrayList<Receiver>();
		this.signals   = new ArrayList<Signal>();
		this.pages     = new ArrayList<Page>();
	}

	public String name() {
		return this.name;
	}

	public void draw() {
		for (Receiver r: receivers)
			if (r instanceof TwoGear || r instanceof BiDirectionJet || r instanceof BiDirectionSoftstart || r instanceof Softstart) {
				Page page = r.page();
				pages.add(page);
			}

		targets();

		
		Errors mksErr = new Errors(this);

		for(Page p: pages)
			mksErr.addMks(p.mks());

		pages.add(mksErr);

		for(Page p: pages)
			p.draw();

		this.close();
	}

	public void add(Signal signal) {
		this.signals.add(signal);
	}

	public void add(Receiver receiver) {
		this.receivers.add(receiver);
	}

	public void show() {
		System.out.println(name);
		System.out.println("SIGNALS");
		for (Signal s: signals)
			System.out.println(s.toString());
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
