package pl.com.hom.elements.bridges;

import java.util.ArrayList;

import pl.com.hom.connections.Point;
import pl.com.hom.elements.Element;
import pl.com.hom.scheme.Page;

public class UpDownRightPhases extends Element {
	public UpDownRightPhases(Page parent, float x, float y) {
		this.visibility = false;
		this.image      = null;

		this.x = x;
		this.y = y;

		points = new ArrayList<Point>();

		points.add(Point.upDownRight(parent, this, 100f, 100f, true, "L1________"));
		points.add(Point.upDownRight(parent, this, 200f, 200f, true, "L2________"));
		points.add(Point.upDownRight(parent, this, 300f, 300f, true, "L3________"));

		parent.add(this);
	}
}
