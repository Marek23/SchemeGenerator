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

		points.add(Point.upDownRightBridge(this, "L1________"));
		points.add(Point.upDownRightBridge(this, "L2________"));
		points.add(Point.upDownRightBridge(this, "L3________"));

		parent.addElement(this);
	}
}
