package pl.com.hom.element.receiver;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Point;
import pl.com.hom.connections.Terminal;
import pl.com.hom.element.Element;
import pl.com.hom.elements.bridges.UpLeftPhases;
import pl.com.hom.elements.bridges.UpRightPhases;
import pl.com.hom.scheme.Page;

import static pl.com.hom.configuration.Resource.getImage;

public class TwoGearEngine extends Element {
	public TwoGearEngine(Page page, float x, float y) {
		this.name       = "TwoGearEngine";
		this.visibility = true;
		this.image      = getImage(name, page);

		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;

		this.x = x;
		this.y = y;

		points = new ArrayList<Point>();

		points.add(Point.left(page, this, 300f, false, "L1"));
		points.add(Point.left(page, this, 200f, false, "L2"));
		points.add(Point.left(page, this, 100f, false, "L3"));

		points.add(Point.right(page, this, 100f, false, "L1"));
		points.add(Point.right(page, this, 200f, false, "L2"));
		points.add(Point.right(page, this, 300f, false, "L3"));

		Point pe = Point.pe(page, this, 200f, 0f, false);
		points.add(pe);
		
		page.add(this);

		Element left  = new UpRightPhases(page, Measures.FIRST_JET_COL, y);
		Element right = new UpLeftPhases(page, Measures.SEC_JET_COL, y);

		for (Point p: left.points())
			page.add(new Terminal(page, p, "X2"));

		for (Point p: right.points())
			page.add(new Terminal(page, p, "X2"));

		page.add(new Terminal(page, pe, "X2"));
	}
}