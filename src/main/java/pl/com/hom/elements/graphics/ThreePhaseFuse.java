package pl.com.hom.elements.graphics;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.Element;
import pl.com.hom.scheme.Page;

import static pl.com.hom.configuration.Resource.getImage;

public class ThreePhaseFuse extends Element {
	public ThreePhaseFuse(Page parent, float x, float y, int number) {
		this.name       = "ThreePhaseFuse";
		this.visibility = true;
		this.image      = getImage(name, parent.getDocument());

		this.x = x;
		this.y = y;
		
		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;
		
		this.symbol = symbol(parent, "F");

		this.symbolX = x - 22f;
		this.symbolY = 595.0f - y - this.height()/1.5f;

		points = new ArrayList<Point>();

		points.add(Point.up(parent, this, 100f, false, "MAINL1____"));
		points.add(Point.up(parent, this, 200f, false, "MAINL2____"));
		points.add(Point.up(parent, this, 300f, false, "MAINL3____"));

		points.add(Point.down(parent, this, 100f, false, "L1________"));
		points.add(Point.down(parent, this, 200f, false, "L2________"));
		points.add(Point.down(parent, this, 300f, false, "L3________"));
		
		parent.add(this);
	}
}
