package pl.com.hom.element.main;

import java.util.ArrayList;

import pl.com.hom.connections.Point;
import pl.com.hom.element.Element;
import pl.com.hom.element.pointer.Pointer;
import pl.com.hom.page.Page;

import static pl.com.hom.configuration.Resource.getImage;

public class Ckf extends Element {
	private static float xSymbolMargin = 20f;

	public Ckf(Page page, float x, float y) {
		this.name       = "Ckf";
		this.visibility = true;
		this.image      = getImage(name, page);

		this.x = x;
		this.y = y;
		this.page = page;
		
		this.width  = image.getWidth();
		this.height = image.getHeight();
		
		this.symbol = symbol(page, "CKF");

		this.symbolX = x - xSymbolMargin;
		this.symbolY = y;

		points = new ArrayList<Point>();
		pointers = new ArrayList<Pointer>();

		points.add(Point.up(page, this, 100f, false, "L1"));
		points.add(Point.up(page, this, 200f, false, "L2"));
		points.add(Point.up(page, this, 300f, false, "L3"));

		points.add(Point.down(page, this, 200f, false, "GROUNDN"));

		page.add(this);
	}
}
