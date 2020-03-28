package pl.com.hom.utils;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;

import com.itextpdf.kernel.color.Color;

import pl.com.hom.electric.Potential;

public final class Measures {
	public static int lineDistance  = 25;
	public static int firstDistance = 25;

	private static ArrayList<Potential> linesOrder = new ArrayList<Potential>(Arrays.asList(
		Potential.DCPLC,
		Potential.DC24,
		Potential.L1,
		Potential.L2,
		Potential.L3,
		Potential.N,
		Potential.DC0,
		Potential.PE
	));

	private static ArrayList<Potential> upLinesOrder = new ArrayList<Potential>(Arrays.asList(
		Potential.L1,
		Potential.L2,
		Potential.L3,
		Potential.DCPLC,
		Potential.DC24
	));

	private static ArrayList<Potential> downLinesOrder = new ArrayList<Potential>(Arrays.asList(
		Potential.N,
		Potential.DC0,
		Potential.PE
	));

	private static HashMap<Potential, Pair> lineSizes;
	static {
		lineSizes.put(Potential.DCPLC, new Pair(10, Color.BLACK));
		lineSizes.put(Potential.DC24,  new Pair(10, Color.BLACK));
		lineSizes.put(Potential.L1,    new Pair(10, Color.BLACK));
		lineSizes.put(Potential.L2,    new Pair(10, Color.BLACK));
		lineSizes.put(Potential.L3,    new Pair(10, Color.BLACK));
		lineSizes.put(Potential.N,     new Pair(10, Color.BLACK));
		lineSizes.put(Potential.DC0,   new Pair(10, Color.BLACK));
		lineSizes.put(Potential.PE,    new Pair(10, Color.BLACK));
	}

	private static int getPotentialOrder(ArrayList<Potential> array, Potential p) {
		return array.indexOf(p);
	}

	public static int getColumnPotentialOrder(Potential p) {
		return firstDistance + getPotentialOrder(linesOrder, p)*lineDistance;
	}

	public static int getUpLinePotentialOrder(Potential p) {
		return firstDistance + getPotentialOrder(upLinesOrder, p)*lineDistance;
	}

	public static int getDownLinePotentialOrder(Potential p) {
		return firstDistance + getPotentialOrder(downLinesOrder, p)*lineDistance;
	}

	public static int getThickness(Potential p) {
		return lineSizes.get(p).size;
	}

	public static Color getColor(Potential p) {
		return lineSizes.get(p).color;
	}
}

class Pair {
	int   size;
	Color color;
	Pair(int size, Color color) {
		this.size  = size;
		this.color = color;
	}
}

