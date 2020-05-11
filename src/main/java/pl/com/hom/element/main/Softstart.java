package pl.com.hom.element.main;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Point;
import pl.com.hom.element.Element;
import pl.com.hom.page.Page;

import static pl.com.hom.configuration.Resource.getImage;

public class Softstart extends Element {
	public Softstart(Page page, float x, float y, String steerPotential) {
		this.name       = "Softstart";
		this.visibility = true;
		this.image      = getImage(name, page);

//		fix bo L1 przesunięte do 400f
		this.x = x - 300f * Measures.SCALE;
		this.y = y;
		this.page = page;

		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;
		
		this.symbol = symbol(page, "TR");

		this.symbolX = x - 300f * Measures.SCALE - 22f;
		this.symbolY = y + this.height()/1.3f;

		points = new ArrayList<Point>();

		points.add(Point.up(page, this, 400f, false, "L1"));
		points.add(Point.up(page, this, 500f, false, "L2"));
		points.add(Point.up(page, this, 600f, false, "L3"));
		points.add(Point.up(page, this, 100f, false, "MAINL10"));
		points.add(Point.left(page, this, 200f, false, steerPotential));

		points.add(Point.down(page, this, 400f, false, "L1"));
		points.add(Point.down(page, this, 500f, false, "L2"));
		points.add(Point.down(page, this, 600f, false, "L3"));
		points.add(Point.down(page, this, 100f, false, "GROUNDN"));
		
		page.add(this);
	}
}
