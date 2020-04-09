package pl.com.hom.configuration;

import java.util.HashMap;

public final class MainPotentialHeight {
	private static HashMap<String,Float> heights;

	public static void initialize() {
		heights = new HashMap<String, Float>();

		heights.put("L1____", new Float(50f)  * Measures.SCALE);
		heights.put("L2____", new Float(100f) * Measures.SCALE);
		heights.put("L3____", new Float(150f) * Measures.SCALE);

		heights.put("LSTER_", new Float(500f) * Measures.SCALE);

		heights.put("DC24__", new Float(200f));
		heights.put("DCPLC_", new Float(250f));

//		TODO
//		add(new Potential("N_____"));
//		add(new Potential("PE____"));
//		add(new Potential("DC0___"));
	}

	public static float getMainLineHeight(String potential) {
		return heights.get(potential);
	}
}
