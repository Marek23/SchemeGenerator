package pl.com.cs.schema.bridge;

import java.util.ArrayList;

import pl.com.cs.schema.Point;
import pl.com.cs.schema.Drawable;
import pl.com.cs.schema.page.Page;

public class UpLeftPhases extends Drawable {
	public UpLeftPhases(Page page, float x, float y) {
		this.visibility = false;

		this.x = x;
		this.y = y;

		this.points = new ArrayList<Point>();

		points.add(Point.upLeft(page, this, 100f, 100f, false, "L1"));
		points.add(Point.upLeft(page, this, 200f, 200f, false, "L2"));
		points.add(Point.upLeft(page, this, 300f, 300f, false, "L3"));

		page.add(this);
	}
}
