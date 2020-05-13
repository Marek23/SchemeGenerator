package pl.com.hom.configuration;

import java.util.HashMap;

import static pl.com.hom.configuration.Measures.scaled;

public final class Widths {
	private static HashMap<String, Float> widths = new HashMap<String, Float>();

	public static void initialize() {
		widths.put("1", 80f);
		widths.put("colSpace", 80f);
		widths.put("2", x("1") + x("colSpace"));
		widths.put("3", x("1") + 2 * x("colSpace"));
		widths.put("4", x("1") + 3 * x("colSpace"));
		widths.put("5", x("1") + 4 * x("colSpace"));

		widths.put("coilsBegin", 480f);
		widths.put("coilSpace", 70f);

		widths.put("pageBegin", 40f);
		widths.put("pageEnd", 800f);
		widths.put("steeringBegin", x("coilsBegin") - 5f);
		widths.put("steeringEnd", x("pageEnd"));

		widths.put("mks", x("5"));
		widths.put("softstart", x("3"));

		widths.put("errorsStart", x("pageBegin"));

		widths.put("plcCpu", x("pageBegin"));
		widths.put("plcCpuWidth", scaled(1000f));
		widths.put("plcModuleBegin", x("plcCpu") + x("plcCpuWidth"));
		widths.put("plcModuleWidth", scaled(480f));
		widths.put("plcSignalBegin", x("pageBegin"));
		widths.put("plcSignalWidth", scaled(500f));
	}

	public static float x(String name) {
		if (!widths.containsKey(name))
			throw new RuntimeException("Missing -" + name + "- width configuration.");

		return widths.get(name);
	}
}
