package pl.com.hom.elements.bridges;

import java.util.ArrayList;

import pl.com.hom.connections.Point;
import pl.com.hom.elements.Element;
import pl.com.hom.scheme.Page;

public class UpRightPhases extends Element {
	public UpRightPhases (Page parent, float x, float y) {
		this.visibility = false;
		this.image      = null;

		this.x = x;
		this.y = y;

		this.points = new ArrayList<Point>();

		points.add(Point.upRight(parent, this, 100f, 300f, false, "L1________"));
		points.add(Point.upRight(parent, this, 200f, 200f, false, "L2________"));
		points.add(Point.upRight(parent, this, 300f, 100f, false, "L3________"));

		parent.add(this);
	}
}
