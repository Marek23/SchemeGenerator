package pl.com.hom.elements;

import java.util.HashSet;
import java.util.List;

import com.itextpdf.kernel.pdf.canvas.wmf.WmfImageData;

import pl.com.hom.connections.Point;
import pl.com.hom.electric.Potential;

public abstract class ElectricElement {
	protected List<Potential> potentials;
	protected HashSet<Point>  points;

	protected int x;
	protected int y;

	protected String id;
	protected String desc;

	protected WmfImageData image;
	protected boolean      visibility;

	protected int columnLevel;

	public List<Potential> getPotentials() {
		return potentials;
	}
}
