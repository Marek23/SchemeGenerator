package pl.com.hom.element.main;

import java.util.ArrayList;

import pl.com.hom.connections.Point;
import pl.com.hom.element.Element;
import pl.com.hom.page.Page;

import static pl.com.hom.configuration.Measures.scaled;
import static pl.com.hom.configuration.Resource.getImage;

import static pl.com.hom.configuration.Widths.x;
import static pl.com.hom.configuration.Heights.y;

public class Softstart extends Element {
	private static float xSymbolMargin = 20f;
	private static float ySymbolMargin;

	public Softstart(Page page, String steerPotential) {
		this.name       = "Softstart";
		this.visibility = true;
		this.image      = getImage(name, page);

//		fix bo L1 przesunięte do 400f
		this.x = x("softstart") - scaled(300f);
		this.y = y("softstart");
		this.page = page;

		this.width  = image.getWidth();
		this.height = image.getHeight();
		
		this.symbol = symbol(page, "TR");

		ySymbolMargin = this.height()/1.3f;
		this.symbolX = x - scaled(300f) - xSymbolMargin;
		this.symbolY = y + ySymbolMargin;

		points = new ArrayList<Point>();

		points.add(Point.up(page, this, 400f, false, "L1"));
		points.add(Point.up(page, this, 500f, false, "L2"));
		points.add(Point.up(page, this, 600f, false, "L3"));
		points.add(Point.up(page, this, 100f, false, "MAINL10"));

		points.add(Point.down(page, this, 400f, false, "L1"));
		points.add(Point.down(page, this, 500f, false, "L2"));
		points.add(Point.down(page, this, 600f, false, "L3"));
		points.add(Point.down(page, this, 100f, false, "GROUNDN"));

		points.add(Point.left(page, this, 200f, false, steerPotential));

		page.add(this);
	}
}
