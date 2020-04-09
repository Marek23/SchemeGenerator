package pl.com.hom.configuration;

import java.util.HashMap;

import pl.com.hom.connections.Potential;

public final class Potentials {
	private static HashMap<String,Potential> potentials;

	public static void initialize() {
		potentials = new HashMap<String, Potential>();

		add(new Potential("L1____", 100f, 100f));
		add(new Potential("L2____", 200f, 200f));
		add(new Potential("L3____", 300f, 300f));
		add(new Potential("DC24__", 100f, 400f));
		add(new Potential("DCPLC_", 100f, 500f));
		add(new Potential("L10___", 100f, 600f));
		add(new Potential("LSTER_", 100f, 100f));

		add(new Potential("DC0___", 100f, 100f));
		add(new Potential("N_____", 200f, 100f));
		add(new Potential("PE____", 300f, 200f));

		add(new Potential("L1____STANDARD__", 100f, -1f));
		add(new Potential("L2____STANDARD__", 200f, -1f));
		add(new Potential("L3____STANDARD__", 300f, -1f));

		add(new Potential("L1____ABVCONBRGE", 100f, -1f));
		add(new Potential("L2____ABVCONBRGE", 200f, -1f));
		add(new Potential("L3____ABVCONBRGE", 300f, -1f));

		add(new Potential("L1____MKS_______", -1f, 100f));
		add(new Potential("L2____MKS_______", -1f, 200f));
		add(new Potential("L3____MKS_______", -1f, 300f));

		add(new Potential("L1____LEFT______", 100f, 300f));
		add(new Potential("L2____LEFT______", 200f, 200f));
		add(new Potential("L3____LEFT______", 300f, 100f));
	}

	public static void add(Potential potential) {
		potentials.put(potential.getFullName(), potential);
	}

	public static Potential getPotential(String name) {
		if (!potentials.containsKey(name))
			throw new RuntimeException("Potential: " + name + " is not declared.");

		return potentials.get(name);
	}
}