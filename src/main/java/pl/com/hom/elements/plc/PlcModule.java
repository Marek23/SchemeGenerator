package pl.com.hom.elements.plc;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.Element;
import pl.com.hom.scheme.Page;

import static pl.com.hom.configuration.Resource.getImage;

public class PlcModule extends Element {
	public PlcModule(Page parent, float x, float y) {
		this.name       = "PlcModule";
		this.visibility = true;
		this.image      = getImage(name);

		this.x = x;
		this.y = y;
		
		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;
		
		this.symbol = symbol(parent, "S");

		this.symbolX = x - 20f;
		this.symbolY = 595.0f - y;

		points = new ArrayList<Point>();

		points.add(Point.upOrDownPotential(this, "MAINDCPLC_", Direction.Up));
		points.add(Point.upOrDownPotential(this, "GROUNDDC__", Direction.Down));
		points.add(Point.upOrDownPotential(this, "GROUNDPE__PLC", Direction.Down));

		parent.addElement(this);
	}
}
