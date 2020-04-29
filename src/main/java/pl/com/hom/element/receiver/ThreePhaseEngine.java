package pl.com.hom.element.receiver;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Point;
import pl.com.hom.element.Element;
import pl.com.hom.scheme.Page;

import static pl.com.hom.configuration.Resource.getImage;

public class ThreePhaseEngine extends Element {
	public ThreePhaseEngine(Page page, float x, float y) {
		this.name       = "ThreePhaseEngine";
		this.visibility = true;
		this.image      = getImage(name, page);

		this.x = x;
		this.y = y;

		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;

		points = new ArrayList<Point>();

		points.add(Point.up(page, this, 100f, false, "L1"));
		points.add(Point.up(page, this, 200f, false, "L2"));
		points.add(Point.up(page, this, 300f, false, "L3"));

		page.add(this);
	}
}
