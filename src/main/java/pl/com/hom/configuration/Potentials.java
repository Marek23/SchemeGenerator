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

		add(new Potential("MAINL1____", -1f, 100f));
		add(new Potential("MAINL2____", -1f, 200f));
		add(new Potential("MAINL3____", -1f, 300f));

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

		add(new Potential("L1________STANDARD__", 100f, -1f));
		add(new Potential("L2________STANDARD__", 200f, -1f));
		add(new Potential("L3________STANDARD__", 300f, -1f));

		add(new Potential("MAINL1____STANDARD__", 100f, -1f));
		add(new Potential("MAINL2____STANDARD__", 200f, -1f));
		add(new Potential("MAINL3____STANDARD__", 300f, -1f));

		add(new Potential("L1________ABVCONBRGE", 100f, -1f));
		add(new Potential("L2________ABVCONBRGE", 200f, -1f));
		add(new Potential("L3________ABVCONBRGE", 300f, -1f));

		add(new Potential("L1________MKS_______", -1f, 100f));
		add(new Potential("L2________MKS_______", -1f, 200f));
		add(new Potential("L3________MKS_______", -1f, 300f));

		add(new Potential("L1________LEFT______", 100f, 300f));
		add(new Potential("L2________LEFT______", 200f, 200f));
		add(new Potential("L3________LEFT______", 300f, 100f));
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