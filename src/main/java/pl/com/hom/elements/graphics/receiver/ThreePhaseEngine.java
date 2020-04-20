package pl.com.hom.elements.graphics.receiver;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.Element;
import pl.com.hom.scheme.Page;

import static pl.com.hom.configuration.Resource.getImage;

public class ThreePhaseEngine extends Element {
	public static float L1WIDTH = 100f * Measures.SCALE;
	public static float L2WIDTH = 200f * Measures.SCALE;
	public static float L3WIDTH = 300f * Measures.SCALE;

	public ThreePhaseEngine(Page parent, float x, float y) {
		this.name       = "ThreePhaseEngine";
		this.visibility = true;
		this.image      = getImage(name, parent.getDocument());

		this.x = x;
		this.y = y;

		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;

		points = new ArrayList<Point>();

		points.add(Point.upOrDownPotential(parent, this, "L1________", Direction.Up));
		points.add(Point.upOrDownPotential(parent, this, "L2________", Direction.Up));
		points.add(Point.upOrDownPotential(parent, this, "L3________", Direction.Up));

		parent.addElement(this);
	}
}
