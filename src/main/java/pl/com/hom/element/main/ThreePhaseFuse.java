package pl.com.hom.element.main;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Point;
import pl.com.hom.element.Element;
import pl.com.hom.scheme.Page;

import static pl.com.hom.configuration.Resource.getImage;

public class ThreePhaseFuse extends Element {
	public ThreePhaseFuse(Page page, float x, float y, int number) {
		this.name       = "ThreePhaseFuse";
		this.visibility = true;
		this.image      = getImage(name, page);

		this.x = x;
		this.y = y;
		
		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;
		
		this.symbol = symbol(page, "F");

		this.symbolX = x - 22f;
		this.symbolY = y + this.height()/1.5f;

		points = new ArrayList<Point>();

		points.add(Point.up(page, this, 100f, false, "MAINL1"));
		points.add(Point.up(page, this, 200f, false, "MAINL2"));
		points.add(Point.up(page, this, 300f, false, "MAINL3"));

		points.add(Point.down(page, this, 100f, false, "L1"));
		points.add(Point.down(page, this, 200f, false, "L2"));
		points.add(Point.down(page, this, 300f, false, "L3"));
		
		page.add(this);
	}
}
