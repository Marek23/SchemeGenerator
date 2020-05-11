package pl.com.hom.element.main;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Point;
import pl.com.hom.connections.Terminal;
import pl.com.hom.element.Element;
import pl.com.hom.page.Page;

import static pl.com.hom.configuration.Resource.getImage;

public class PlcCpu extends Element {
	public PlcCpu(Page page, float x, float y) {
		this.name       = "PlcCpu";
		this.visibility = true;
		this.image      = getImage(name, page);

		this.x = x;
		this.y = y;
		this.page = page;
		
		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;
		
		this.symbol = symbol(page, "S");

		this.symbolX = x - 20f;
		this.symbolY = 595.0f - y;

		points = new ArrayList<Point>();

		points.add(Point.up(page, this, 100f, false, "MAINDCPLC"));
		points.add(Point.down(page, this, 100f, false, "GROUNDDC"));
		points.add(Point.down(page, this, 200f, false, "GROUNDPE"));

		Point a = Point.down(page, this, 500f, false, "A+");
		Point b = Point.down(page, this, 600f, false, "B-");

		points.add(a);
		points.add(b);

		page.add(new Terminal(page, a, "XT"));
		page.add(new Terminal(page, b, "XT"));

		page.add(this);
	}
}
