package pl.com.hom.data;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

public class Board extends PdfDocument {
	private static final long serialVersionUID = 1L;

	private String name;
	private String current;
	private String power;
	private String cable;
	private String switchboard;

	private ArrayList<Receiver> receivers;
	private ArrayList<Signal>   signals;

	public Board(String name) throws FileNotFoundException {
		super(new PdfWriter(name));

		this.name = name;

		this.receivers = new ArrayList<Receiver>();
		this.signals   = new ArrayList<Signal>();
	}

	public String name() {
		return this.name + " " + this.switchboard;
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
}
