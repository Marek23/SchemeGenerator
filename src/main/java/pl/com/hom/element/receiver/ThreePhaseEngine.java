package pl.com.hom.element.receiver;

import java.util.ArrayList;

import pl.com.hom.connections.Point;
import pl.com.hom.connections.Terminal;
import pl.com.hom.element.Element;
import pl.com.hom.page.Page;

import static pl.com.hom.configuration.Widths.x;
import static pl.com.hom.configuration.Heights.y;
import static pl.com.hom.configuration.Resource.getImage;

public class ThreePhaseEngine extends Element {
	public ThreePhaseEngine(Page page) {
		this.name       = "ThreePhaseEngine";
		this.visibility = true;
		this.image      = getImage(name, page);

		this.x = x("3");
		this.y = y("receiver");

		this.width  = image.getWidth();
		this.height = image.getHeight();

		points = new ArrayList<Point>();

		points.add(Point.up(page, this, 100f, false, "L1"));
		points.add(Point.up(page, this, 200f, false, "L2"));
		points.add(Point.up(page, this, 300f, false, "L3"));

		for (Point p: points)
			page.add(new Terminal(page, p, "X2"));

		Point pe = Point.pe(page, this, 400f, 0f, false);
		points.add(pe);

		page.add(new Terminal(page, pe, "X2"));
		
		page.add(this);
	}
}
