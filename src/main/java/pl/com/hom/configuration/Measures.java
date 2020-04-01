package pl.com.hom.configuration;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.ArrayList;

import com.itextpdf.kernel.color.Color;

import pl.com.hom.electric.Potential;

import static pl.com.hom.configuration.Potentials.getPotential;

public final class Measures {

	public final static float SCALE = 0.1f;

	public final static float PAGE_WIDTH  = 842.0f;

	public final static float COL_LEV_MARGIN = 20;
	public final static float COL_LEV_HEIGHT = 20;

	public final static float COL_WIDTH_MARGIN = 10;

	private static float upLineDistance = 6;
	private static float upLineMargin   = 2;

	private static float downLineDistance = 6;
	private static float downLineMargin   = 2;

//	private static EnumMap<Potential, Param> lineParams;
//	static {
//		lineParams = new EnumMap<Potential, Param>(Potential.class);
//		lineParams.put(getPotential"DCPLC"), new Param(10, Color.BLACK));
//		lineParams.put(getPotentialPotential.DC24,  new Param(10, Color.BLACK));
//		lineParams.put(Potential.L1,    new Param(10, Color.BLACK));
//		lineParams.put(Potential.L2,    new Param(10, Color.BLACK));
//		lineParams.put(Potential.L3,    new Param(10, Color.BLACK));
//		lineParams.put(Potential.N,     new Param(10, Color.BLACK));
//		lineParams.put(Potential.DC0,   new Param(10, Color.BLACK));
//		lineParams.put(Potential.PE,    new Param(10, Color.BLACK));
//	}
}

class Param {
	int   size;
	Color color;
	Param(int size, Color color) {
		this.size  = size;
		this.color = color;
	}
}

