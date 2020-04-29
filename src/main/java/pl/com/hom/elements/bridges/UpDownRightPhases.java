package pl.com.hom.elements.bridges;

import java.util.ArrayList;

import pl.com.hom.connections.Point;
import pl.com.hom.element.Element;
import pl.com.hom.scheme.Page;

public class UpDownRightPhases extends Element {
	public UpDownRightPhases(Page page, float x, float y) {
		this.visibility = false;
		this.image      = null;

		this.x = x;
		this.y = y;

		points = new ArrayList<Point>();

		points.add(Point.upDownRight(page, this, 100f, 100f, true, "L1"));
		points.add(Point.upDownRight(page, this, 200f, 200f, true, "L2"));
		points.add(Point.upDownRight(page, this, 300f, 300f, true, "L3"));

		page.add(this);
	}

	public Element child() {
		return null;
	}
}
