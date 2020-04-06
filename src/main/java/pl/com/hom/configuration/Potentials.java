package pl.com.hom.configuration;

import java.util.HashMap;

import pl.com.hom.connections.Potential;

public final class Potentials {
	private static HashMap<String,Potential> potentials;

	public static void initialize() {
		potentials = new HashMap<String, Potential>();

		add(new Potential("L1____"));
		add(new Potential("L2____"));
		add(new Potential("L3____"));
		add(new Potential("DC24__"));
		add(new Potential("DCPLC_"));

		add(new Potential("N_____"));
		add(new Potential("PE____"));
		add(new Potential("DC0___"));
	}

	public static void add(Potential potential) {
		potentials.put(potential.getPotential(), potential);
	}

	public static Potential getPotential(String name) {
		return potentials.get(name);
	}
}
