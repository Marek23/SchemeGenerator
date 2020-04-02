package pl.com.hom.configuration;

import java.util.HashMap;

import pl.com.hom.connections.Potential;

public final class Potentials {
	private static HashMap<String,Potential> potentials;

	public static void initialize() {
		potentials = new HashMap<String, Potential>();

		add(new Potential("L1", 100f, 100f, 100f));
		add(new Potential("L2", 200f, 200f, 200f));
		add(new Potential("L3", 300f, 300f, 300f));
		add(new Potential("DC24",  400f, 400f, 400f));
		add(new Potential("DCPLC", 500f, 500f, 500f));

		add(new Potential("N",   100f, 100f, 100f));
		add(new Potential("PE",  200f, 200f, 200f));
		add(new Potential("DC0", 300f, 300f, 300f));
	}

	public static void add(Potential potential) {
		potentials.put(potential.getName(), potential);
	}

	public static Potential getPotential(String name) {
		return potentials.get(name);
	}
}
