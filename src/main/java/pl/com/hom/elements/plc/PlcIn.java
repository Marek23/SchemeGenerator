package pl.com.hom.elements.plc;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.Element;
import pl.com.hom.scheme.Page;

import static pl.com.hom.configuration.Resource.getImage;

public class PlcIn extends Element {
	public PlcIn(Page parent, PlcModule module, float x, float y) {
		this.name       = "PlcModule";
		this.visibility = true;
		this.image      = getImage(name);

		this.x = x;
		this.y = y;
		
		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;
		
		this.symbol = module.symbol();

		this.symbolX = x - 20f;
		this.symbolY = 595.0f - y;

		points = new ArrayList<Point>();

		points.add(Point.upOrDownPotential(this, "PLCINPUT__", Direction.Up));

		parent.addElement(this);
	}

	public PlcIn(Page parent, PlcCpu cpu, float x, float y) {
		this.name       = "PlcModule";
		this.visibility = true;
		this.image      = getImage(name);

		this.x = x;
		this.y = y;
		
		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;
		
		this.symbol = cpu.symbol();

		this.symbolX = x - 20f;
		this.symbolY = 595.0f - y;

		points = new ArrayList<Point>();

		points.add(Point.upOrDownPotential(this, "PLCINPUT__", Direction.Up));

		parent.addElement(this);
	}
}
