package pl.com.hom.utils;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.ArrayList;

import com.itextpdf.kernel.color.Color;

import pl.com.hom.electric.Potential;
import pl.com.hom.elements.ColumnRow;

public final class Measures {
	private static int commonLineDistance = 7;
	private static int commonLineMargin   = 3;

	private static int upLineDistance = 6;
	private static int upLineMargin   = 2;

	private static int downLineDistance = 5;
	private static int downLineMargin   = 3;

	private static int colWidth  = 120;
	private static int colMargin = 50;

	private static int colLevelHeigth = 120;
	private static int colLevelMargin = 5;

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
		return commonLineMargin + getPotentialOrder(commonLineOrder, p) * commonLineDistance;
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

	public static float countColumnWidth(int index) {
		return colMargin + index*colWidth;
	}

	public static float countColumnRowHeight(ColumnRow element) {
		return colLevelMargin + ColumnLevels.getRowLevel(element)*colLevelHeigth;
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

