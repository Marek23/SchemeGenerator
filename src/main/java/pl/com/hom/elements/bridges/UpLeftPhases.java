package pl.com.hom.elements.bridges;

import java.util.ArrayList;

import pl.com.hom.connections.Point;
import pl.com.hom.element.Element;
import pl.com.hom.scheme.Page;

public class UpLeftPhases extends Element {
	public UpLeftPhases(Page page, float x, float y) {
		this.visibility = false;
		this.image      = null;

		this.x = x;
		this.y = y;

		this.points = new ArrayList<Point>();

		points.add(Point.upLeft(page, this, 100f, 100f, false, "L1"));
		points.add(Point.upLeft(page, this, 200f, 200f, false, "L2"));
		points.add(Point.upLeft(page, this, 300f, 300f, false, "L3"));

		page.add(this);
	}

	public Element child() {
		return null;
	}
}
