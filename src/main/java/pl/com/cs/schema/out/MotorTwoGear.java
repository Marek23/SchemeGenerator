package pl.com.cs.schema.out;

import java.util.ArrayList;

import pl.com.cs.schema.Drawable;
import pl.com.cs.schema.Point;
import pl.com.cs.schema.bridge.UpLeftPhases;
import pl.com.cs.schema.bridge.UpRightPhases;
import pl.com.cs.schema.page.Page;

import static pl.com.cs.config.Widths.x;
import static pl.com.cs.config.Heights.y;
import static pl.com.cs.config.Images.getImage;

public class MotorTwoGear extends Drawable {
	public MotorTwoGear(Page page) {
		this.name       = "TwoGearEngine";
		this.visibility = true;
		this.image      = getImage(name, page);

		this.width  = image.getWidth();
		this.height = image.getHeight();

		this.x = x("2");
		this.y = y("receiver");

		points = new ArrayList<Point>();

		points.add(Point.left(page, this, 300f, false, "L1"));
		points.add(Point.left(page, this, 200f, false, "L2"));
		points.add(Point.left(page, this, 100f, false, "L3"));

		points.add(Point.right(page, this, 100f, false, "L1"));
		points.add(Point.right(page, this, 200f, false, "L2"));
		points.add(Point.right(page, this, 300f, false, "L3"));

		Point pe = Point.pe(page, this, 200f, 0f, false);
		points.add(pe);
		
		page.add(this);

		Drawable left  = new UpRightPhases(page, x("1"), y);
		Drawable right = new UpLeftPhases(page, x("3"), y);

		for (Point p: left.points())
			new Terminal(page, p, "X2");

		for (Point p: right.points())
			new Terminal(page, p, "X2");

		new Terminal(page, pe, "X2");
	}
}
