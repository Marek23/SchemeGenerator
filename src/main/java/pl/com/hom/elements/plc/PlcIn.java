package pl.com.hom.elements.plc;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Point;
import pl.com.hom.element.Element;
import pl.com.hom.element.main.PlcCpu;
import pl.com.hom.page.Page;

import static pl.com.hom.configuration.Resource.getImage;

public class PlcIn extends Element {
	public PlcIn(Page page, PlcModule module, float x, float y) {
		this.name       = "PlcModule";
		this.visibility = true;
		this.image      = getImage(name, page);

		this.x = x;
		this.y = y;
		
		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;
		
		this.symbol = module.symbol();

		this.symbolX = x - 20f;
		this.symbolY = 595.0f - y;

		points = new ArrayList<Point>();

		points.add(Point.up(page, this, 200f, false, "PLCINPUT"));

		page.add(this);
	}

	public PlcIn(Page page, PlcCpu cpu, float x, float y) {
		this.name       = "PlcModule";
		this.visibility = true;
		this.image      = getImage(name, page);

		this.x = x;
		this.y = y;
		
		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;
		
		this.symbol = cpu.symbol();

		this.symbolX = x - 20f;
		this.symbolY = 595.0f - y;

		points = new ArrayList<Point>();

		points.add(Point.up(page, this, 200f, false, "PLCINPUT"));

		page.add(this);
	}
}
