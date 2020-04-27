package pl.com.hom.elements.bridges;

import java.util.ArrayList;

import pl.com.hom.connections.Point;
import pl.com.hom.elements.Element;
import pl.com.hom.scheme.Page;

public class UpLeftPhases extends Element {
	public UpLeftPhases(Page parent, float x, float y) {
		this.visibility = false;
		this.image      = null;

		this.x = x;
		this.y = y;

		this.points = new ArrayList<Point>();

		points.add(Point.upLeft(parent, this, 100f, 100f, false, "L1________"));
		points.add(Point.upLeft(parent, this, 200f, 200f, false, "L2________"));
		points.add(Point.upLeft(parent, this, 300f, 300f, false, "L3________"));

		parent.add(this);
	}
}
