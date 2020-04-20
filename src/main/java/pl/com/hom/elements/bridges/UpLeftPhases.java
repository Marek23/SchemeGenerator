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

		points.add(Point.upLeftPoint(parent, this, "L1________"));
		points.add(Point.upLeftPoint(parent, this, "L2________"));
		points.add(Point.upLeftPoint(parent, this, "L3________"));

		parent.addElement(this);
	}
}
