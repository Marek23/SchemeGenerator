package pl.com.hom.elements.plc;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Direction;
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

		points.add(Point.upOrDownPotential(parent, this, "MAINDCPLC_", Direction.Up));
		points.add(Point.upOrDownPotential(parent, this, "GROUNDDC__", Direction.Down));
		points.add(Point.upOrDownPotential(parent, this, "GROUNDPE__PLC", Direction.Down));

		Point a = Point.upOrDownPotential(parent, this, "A+________", Direction.Down);
		Point b = Point.upOrDownPotential(parent, this, "B-________", Direction.Down);

		points.add(a);
		points.add(b);

		parent.terminal(new Terminal(parent, a, "XT"));
		parent.terminal(new Terminal(parent, b, "XT"));

		parent.addElement(this);
	}
}
