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

		points.add(Point.upRightPoint(parent, this, "L1________LEFT"));
		points.add(Point.upRightPoint(parent, this, "L2________LEFT"));
		points.add(Point.upRightPoint(parent, this, "L3________LEFT"));

		parent.addElement(this);
	}
}
