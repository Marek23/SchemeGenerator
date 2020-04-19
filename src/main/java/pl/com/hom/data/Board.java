package pl.com.hom.data;

import java.util.ArrayList;

public class Board {
	private String name;
	private String current;
	private String power;
	private String cable;
	private String switchboard;

	private static ArrayList<Engine> engines;
	private static ArrayList<String> signals;

	public Board(String name) {
		this.name = name;

		engines = new ArrayList<Engine>();
		signals = new ArrayList<String>();
	}

	public String name() {
		return this.name + " " + this.switchboard;
	}

	public void add(String signal) {
		this.signals.add(signal);
	}

	public void add(Engine engine) {
		this.engines.add(engine);
	}
}
