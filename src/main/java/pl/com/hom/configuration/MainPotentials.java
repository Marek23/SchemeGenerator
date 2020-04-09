package pl.com.hom.configuration;

import java.util.HashMap;

public final class MainPotentials {
	private static HashMap<String,Float> heights;

	public static void initialize() {
		heights = new HashMap<String, Float>();

		heights.put("L1____", new Float(50f)  * Measures.SCALE);
		heights.put("L2____", new Float(100f) * Measures.SCALE);
		heights.put("L3____", new Float(150f) * Measures.SCALE);

		heights.put("LSTER_", new Float(300f) * Measures.SCALE);

		heights.put("DC24__", new Float(200f) * Measures.SCALE);
		heights.put("DCPLC_", new Float(250f) * Measures.SCALE);

		heights.put("N_____", new Float(500f) * Measures.SCALE);
		heights.put("PE____", new Float(550f) * Measures.SCALE);
		heights.put("DC0___", new Float(470f) * Measures.SCALE);
	}

	public static float getMainLineHeight(String potential) {
		return heights.get(potential);
	}
}
