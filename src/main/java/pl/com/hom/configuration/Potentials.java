package pl.com.hom.configuration;

import java.util.HashMap;

import pl.com.hom.connections.Potential;

public final class Potentials {
	private static HashMap<String,Potential> potentials;

	public static void initialize() {
		potentials = new HashMap<String, Potential>();

		add(new Potential("L1", 100f, 100f));
		add(new Potential("L2", 200f, 200f));
		add(new Potential("L3", 300f, 300f));

		add(new Potential("DC24", 100f, 100f));
		add(new Potential("DCPLC", 100f, -1f));
		add(new Potential("L10", 100f, 600f));

		add(new Potential("DC0", 100f, 100f));
		add(new Potential("N", 200f, 100f));
		add(new Potential("PE", 300f, 200f));

		add(new Potential("GROUNDN", 100f, 3000f));
		add(new Potential("GROUNDDC", 100f, 3050f));
		add(new Potential("GROUNDPE", 200f, 3100f));

		add(new Potential("MAINL1", 100f, 60f));
		add(new Potential("MAINL2", 200f, 120f));
		add(new Potential("MAINL3", 300f, 180f));
		add(new Potential("MAINL10", 300f, 240f));

		add(new Potential("MAINDC24", 100f, 300f));
		add(new Potential("MAINDCPLC", 100f, 360f));

		add(new Potential("A+", 500f, -1f));
		add(new Potential("B-", 600f, -1f));

		add(new Potential("1B", -1f, 1500f));
		add(new Potential("2B", -1f, 1550f));

		add(new Potential("PLCINPUT", 100f, -1f));
		add(new Potential("PLCOUTPUT", 100f, -1f));
	}

	public static void add(Potential potential) {
		potentials.put(potential.name(), potential);
	}

	public static Potential potential(String name) {
		if (!potentials.containsKey(name))
			throw new RuntimeException("Potential: " + name + " is not declared.");

		return potentials.get(name);
	}
}