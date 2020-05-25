package pl.com.hom.configuration;

import java.util.HashMap;

public final class Heights {
	private static HashMap<String, Float> heights = new HashMap<String, Float>();

	public static void initialize() {
		heights.put("terminal", 475f);
		heights.put("beginPointer", 475f);
		heights.put("pointerSpace", 5f);
		heights.put("coil", 500f);

		heights.put("gear", 350f);
		heights.put("direction", 250f);

		heights.put("spaceUp", 5f);
		heights.put("spaceDown", 5f);
		heights.put("mklSpace", 20f);

		heights.put("softstart", 150f);
		heights.put("plc", 150f);
		heights.put("coilSeparator", 300f);
		heights.put("steeringContact", 70f);
		heights.put("sapOutContact", 380f);
		heights.put("sapOutPot", y("sapOutContact") - y("spaceUp"));

		heights.put("steeringPot", 150f);

		heights.put("mainPhuse", 50f);
		heights.put("directionPhuse", 280f);

		heights.put("receiver", 500f);

		heights.put("errorsStart", Measures.scaled(370));

		heights.put("plcSignal", 250f);

		heights.put("mkl", 350f);
		heights.put("mks", 380f);

		heights.put("ckf", 350f);
		heights.put("coil", 380f);
	}

	public static float y(String name) {
		if (!heights.containsKey(name))
			throw new RuntimeException("Missing -" + name + "- height configuration.");

		return heights.get(name);
	}
}
