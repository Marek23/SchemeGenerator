package pl.com.hom.configuration;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.ArrayList;

import com.itextpdf.kernel.color.Color;

import pl.com.hom.electric.Potential;

public final class Measures {

	public final static float SCALE = 0.1f;

	private final static float commonLineDistance =  100 * SCALE;

	public final static float PAGE_WIDTH  = 842.0f;
	public final static float PAGE_HEIGHT = 595.0f;

	public final static float COL_LEV_MARGIN = 20;
	public final static float COL_LEV_HEIGHT = 30;

	public final static float COL_WIDTH        = 120;
	public final static float COL_WIDTH_MARGIN = 120;

	private static float upLineDistance = 6;
	private static float upLineMargin   = 2;

	private static float downLineDistance = 5;
	private static float downLineMargin   = 3;

	private static ArrayList<Potential> commonLineOrder = new ArrayList<Potential>(Arrays.asList(
		Potential.DCPLC,
		Potential.DC24,
		Potential.L1,
		Potential.L2,
		Potential.L3,
		Potential.N,
		Potential.DC0,
		Potential.PE
	));

	private static ArrayList<Potential> upLineOrder = new ArrayList<Potential>(Arrays.asList(
		Potential.L1,
		Potential.L2,
		Potential.L3,
		Potential.DCPLC,
		Potential.DC24
	));

	private static ArrayList<Potential> downLineOrder = new ArrayList<Potential>(Arrays.asList(
		Potential.N,
		Potential.DC0,
		Potential.PE
	));

	private static EnumMap<Potential, Param> lineParams;
	static {
		lineParams = new EnumMap<Potential, Param>(Potential.class);
		lineParams.put(Potential.DCPLC, new Param(10, Color.BLACK));
		lineParams.put(Potential.DC24,  new Param(10, Color.BLACK));
		lineParams.put(Potential.L1,    new Param(10, Color.BLACK));
		lineParams.put(Potential.L2,    new Param(10, Color.BLACK));
		lineParams.put(Potential.L3,    new Param(10, Color.BLACK));
		lineParams.put(Potential.N,     new Param(10, Color.BLACK));
		lineParams.put(Potential.DC0,   new Param(10, Color.BLACK));
		lineParams.put(Potential.PE,    new Param(10, Color.BLACK));
	}

	private static int getPotentialOrder(ArrayList<Potential> array, Potential p) {
		return array.indexOf(p);
	}

	public static float countCommonWidth(Potential p) {
		return (getPotentialOrder(commonLineOrder, p) + 1) * commonLineDistance;
	}

	public static float countUpHeight(Potential p) {
		return upLineMargin + getPotentialOrder(upLineOrder, p) * upLineDistance;
	}

	public static float countDownHeight(Potential p) {
		return downLineMargin + getPotentialOrder(downLineOrder, p) * downLineDistance;
	}

	public static float getThickness(Potential p) {
		return lineParams.get(p).size;
	}

	public static Color getColor(Potential p) {
		return lineParams.get(p).color;
	}
}

class Param {
	int   size;
	Color color;
	Param(int size, Color color) {
		this.size  = size;
		this.color = color;
	}
}

