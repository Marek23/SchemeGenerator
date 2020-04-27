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

		add(new Potential("DC24______", 100f, -1f));
		add(new Potential("DCPLC_____", 100f, -1f));
		add(new Potential("L10_______", 100f, 600f));

		add(new Potential("DC0_______", 100f, 100f));
		add(new Potential("N_________", 200f, 100f));
		add(new Potential("PE________", 300f, 200f));

		add(new Potential("GROUNDN___", 100f, 2800f));
		add(new Potential("GROUNDDC__", 100f, 2850f));
		add(new Potential("GROUNDPE__", 200f, 2900f));

		add(new Potential("MAINL1____", 100f, 40f));
		add(new Potential("MAINL2____", 200f, 80f));
		add(new Potential("MAINL3____", 300f, 120f));
		add(new Potential("MAINDC24__", 100f, 160f));
		add(new Potential("MAINDCPLC_", 100f, 200f));

		add(new Potential("A+________", 500f, -1f));
		add(new Potential("B-________", 600f, -1f));

		add(new Potential("1B________", -1f, 1500f));
		add(new Potential("2B________", -1f, 1550f));

		add(new Potential("PLCINPUT__", 100f, -1f));
		add(new Potential("PLCOUTPUT_", 100f, -1f));
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