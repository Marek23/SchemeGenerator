package pl.com.hom.elements.plc;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Point;
import pl.com.hom.connections.Terminal;
import pl.com.hom.elements.Element;
import pl.com.hom.scheme.Page;

import static pl.com.hom.configuration.Resource.getImage;

public class PlcCpu extends Element {
	public PlcCpu(Page parent, float x, float y) {
		this.name       = "PlcCpu";
		this.visibility = true;
		this.image      = getImage(name, parent.getDocument());

		this.x = x;
		this.y = y;
		
		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;
		
		this.symbol = symbol(parent, "S");

		this.symbolX = x - 20f;
		this.symbolY = 595.0f - y;

		points = new ArrayList<Point>();

		points.add(Point.up(parent, this, 100f, false, "MAINDCPLC_"));
		points.add(Point.down(parent, this, 100f, false, "GROUNDDC__"));
		points.add(Point.down(parent, this, 200f, false, "GROUNDPE__"));

		Point a = Point.down(parent, this, 500f, false, "A+________");
		Point b = Point.down(parent, this, 600f, false, "B-________");

		points.add(a);
		points.add(b);

		parent.add(new Terminal(parent, a, "XT"));
		parent.add(new Terminal(parent, b, "XT"));

		parent.add(this);
	}
}
