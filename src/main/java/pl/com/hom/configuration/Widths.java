package pl.com.hom.configuration;

import java.util.HashMap;

import static pl.com.hom.configuration.Measures.scaled;

public final class Widths {
	private static HashMap<String, Float> widths = new HashMap<String, Float>();

	public static void initialize() {
		widths.put("0", 35f);
		widths.put("1", 80f);
		widths.put("colSpace", 80f);
		widths.put("2", x("1") + x("colSpace"));
		widths.put("3", x("1") + 2 * x("colSpace"));
		widths.put("4", x("1") + 3 * x("colSpace"));
		widths.put("5", x("1") + 4 * x("colSpace"));

		widths.put("coilsBegin", 480f);
		widths.put("coilSpace", 70f);

		widths.put("pageBegin", x("0"));
		widths.put("pageEnd", 800f);
		widths.put("steeringBegin", x("coilsBegin") - 5f);
		widths.put("steeringEnd", x("pageEnd"));

		widths.put("mks", x("5"));
		widths.put("mkl", x("1"));
		widths.put("softstart", x("3"));

		widths.put("errorsStart", x("0"));

		widths.put("plcBegin", x("0"));
		widths.put("plcModuleWidth", scaled(520f));
		widths.put("plcSignalBegin", x("0"));
		widths.put("plcSignalWidth", scaled(570f));
	}

	public static float x(String name) {
		if (!widths.containsKey(name))
			throw new RuntimeException("Missing -" + name + "- width configuration.");

		return widths.get(name);
	}
}
