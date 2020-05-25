package pl.com.hom.element.receiver;


import java.util.ArrayList;

import pl.com.hom.connections.Point;
import pl.com.hom.connections.Terminal;
import pl.com.hom.element.Element;
import pl.com.hom.page.Page;

import static pl.com.hom.configuration.Resource.getImage;
import static pl.com.hom.configuration.Heights.y;


public class SapIn extends Element {
	private static float xSymbolMargin = 15f;
	private static float ySymbolMargin = 33f;

	public SapIn (Page page, pl.com.hom.data.SapIn in, float x) {
		this.name       = "SapIn";
		this.visibility = true;
		this.image      = getImage(name, page);

		this.x = x;
		this.y = y("receiver");

		this.symbol = in.symbol();

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
