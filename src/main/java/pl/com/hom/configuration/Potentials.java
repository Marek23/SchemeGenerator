package pl.com.hom.configuration;

import java.util.HashMap;

import pl.com.hom.connections.Potential;

public final class Potentials {
	private static HashMap<String,Potential> potentials;

	public static void initialize() {
		potentials = new HashMap<String, Potential>();

		add(new Potential("L1________", 100f, 100f));
		add(new Potential("L2________", 200f, 200f));
		add(new Potential("L3________", 300f, 300f));

		add(new Potential("DC24______", 100f, 400f));
		add(new Potential("DCPLC_____", 100f, 500f));
		add(new Potential("L10_______", 100f, 600f));
		add(new Potential("LSTER_____", 100f, 100f));

		add(new Potential("DC0_______", 100f, 100f));
		add(new Potential("N_________", 200f, 100f));
		add(new Potential("PE________", 300f, 200f));

		add(new Potential("L1________INHORLINE", 100f, -1f));
		add(new Potential("L2________INHORLINE", 200f, -1f));
		add(new Potential("L3________INHORLINE", 300f, -1f));

		add(new Potential("MAINL1____", 100f, 30f));
		add(new Potential("MAINL2____", 200f, 60f));
		add(new Potential("MAINL3____", 300f, 90f));

		add(new Potential("MAINL1____INHORLINE", 100f, -1f));
		add(new Potential("MAINL2____INHORLINE", 200f, -1f));
		add(new Potential("MAINL3____INHORLINE", 300f, -1f));

		add(new Potential("MAINL1____INVERLINE", -1f, 30f));
		add(new Potential("MAINL2____INVERLINE", -1f, 60f));
		add(new Potential("MAINL3____INVERLINE", -1f, 90f));

		add(new Potential("L1________INVERLINE", -1f, 100f));
		add(new Potential("L2________INVERLINE", -1f, 200f));
		add(new Potential("L3________INVERLINE", -1f, 300f));

		add(new Potential("L1________LEFT", 100f, 300f));
		add(new Potential("L2________LEFT", 200f, 200f));
		add(new Potential("L3________LEFT", 300f, 100f));
	}

	public static void add(Potential potential) {
		potentials.put(potential.fullName(), potential);
	}

	public static Potential potential(String name) {
		if (!potentials.containsKey(name))
			throw new RuntimeException("Potential: " + name + " is not declared.");

		return potentials.get(name);
	}
}