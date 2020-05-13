package pl.com.hom.element.main;

import java.util.ArrayList;

import pl.com.hom.connections.Point;
import pl.com.hom.element.Element;
import pl.com.hom.page.Page;

import static pl.com.hom.configuration.Resource.getImage;

public class ThreePhaseFuse extends Element {
	private static float xSymbolMargin = 20f;
	private static float ySymbolMargin;

	public ThreePhaseFuse(Page page, float x, float y) {
		this.name       = "ThreePhaseFuse";
		this.visibility = true;
		this.image      = getImage(name, page);

		this.x = x;
		this.y = y;
		this.page = page;
		
		this.width  = image.getWidth();
		this.height = image.getHeight();
		
		this.symbol = symbol(page, "F");

		ySymbolMargin = this.height()/1.5f;
		this.symbolX = x - xSymbolMargin;
		this.symbolY = y + ySymbolMargin;

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
