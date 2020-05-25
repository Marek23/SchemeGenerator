package pl.com.hom.element.receiver;


import java.util.ArrayList;

import pl.com.hom.connections.Point;
import pl.com.hom.connections.Terminal;
import pl.com.hom.element.Element;
import pl.com.hom.page.Page;

import static pl.com.hom.configuration.Resource.getImage;
import static pl.com.hom.configuration.Heights.y;


public class SapOut extends Element {
	private static float xSymbolMargin = 15f;
	private static float ySymbolMargin = 33f;

	public SapOut (Page page, pl.com.hom.data.SapOut out, float x) {
		this.name       = "SapOut";
		this.visibility = true;
		this.image      = getImage(name, page);

		this.x = x;
		this.y = y("receiver");

		this.symbol = out.symbol();

		this.symbolX = x + xSymbolMargin;
		this.symbolY = y + ySymbolMargin;

		this.width  = image.getWidth();
		this.height = image.getHeight();

		this.points = new ArrayList<Point>();

		points.add(Point.up(page, this, 0f, false, "DC24"));
		points.add(Point.up(page, this, 200f, false, "DC24"));

		for (Point p: points)
			new Terminal(page, p, "XS");

		page.add(this);
	}
}
