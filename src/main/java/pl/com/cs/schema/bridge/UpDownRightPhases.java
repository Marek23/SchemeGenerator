package pl.com.cs.schema.bridge;

import java.util.ArrayList;

import pl.com.cs.schema.Point;
import pl.com.cs.schema.Drawable;
import pl.com.cs.schema.page.Page;

public class UpDownRightPhases extends Drawable {
	public UpDownRightPhases(Page page, float x, float y, boolean revert) {
		this.visibility = false;

		this.x = x;
		this.y = y;

		points = new ArrayList<Point>();

		if (revert) {
			points.add(Point.upDownRight(page, this, 300f, 100f, true, "L1"));
			points.add(Point.upDownRight(page, this, 200f, 200f, true, "L2"));
			points.add(Point.upDownRight(page, this, 100f, 300f, true, "L3"));
		}
		else {
			points.add(Point.upDownRight(page, this, 100f, 100f, true, "L1"));
			points.add(Point.upDownRight(page, this, 200f, 200f, true, "L2"));
			points.add(Point.upDownRight(page, this, 300f, 300f, true, "L3"));
		}

		page.add(this);
	}
}
