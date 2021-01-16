package pl.com.cs.schema.bridge;

import java.util.ArrayList;

import pl.com.cs.schema.Point;
import pl.com.cs.schema.Drawable;
import pl.com.cs.schema.page.Page;

public class UpDownLeftPhases extends Drawable {
	public UpDownLeftPhases(Page page, float x, float y) {
		this.visibility = false;

		this.x = x;
		this.y = y;

		points = new ArrayList<Point>();

		points.add(Point.upDownLeft(page, this, 100f, 100f, true, "L1"));
		points.add(Point.upDownLeft(page, this, 200f, 200f, true, "L2"));
		points.add(Point.upDownLeft(page, this, 300f, 300f, true, "L3"));

		page.add(this);
	}

}
