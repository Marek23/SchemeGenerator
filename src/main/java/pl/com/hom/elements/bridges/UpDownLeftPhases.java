package pl.com.hom.elements.bridges;

import java.util.ArrayList;

import pl.com.hom.connections.Point;
import pl.com.hom.elements.Element;
import pl.com.hom.scheme.Page;

public class UpDownLeftPhases extends Element {
	public UpDownLeftPhases(Page parent, float x, float y) {
		this.visibility = false;
		this.image      = null;

		this.x = x;
		this.y = y;

		points = new ArrayList<Point>();

		points.add(Point.upDownLeft(parent, this, 100f, 300f, true, "L1________"));
		points.add(Point.upDownLeft(parent, this, 200f, 200f, true, "L2________"));
		points.add(Point.upDownLeft(parent, this, 300f, 100f, true, "L3________"));

		parent.add(this);
	}
}
