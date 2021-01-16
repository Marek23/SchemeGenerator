
package pl.com.cs.schema.out;

import java.util.ArrayList;

import pl.com.cs.schema.Drawable;
import pl.com.cs.schema.Point;
import pl.com.cs.schema.page.Page;

import static pl.com.cs.config.Images.getImage;

import static pl.com.cs.config.Heights.y;

public class SapOutput extends Drawable {
	private static float ySymbolMargin = 33f;

	public SapOutput (Page page, pl.com.cs.fps.SapOutput out, float x) {
		this.name       = "SapOut";
		this.visibility = true;
		this.image      = getImage(name, page);

		this.x = x;
		this.y = y("receiver");

		this.symbol = out.symbol();

		this.symbolX = x;
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
