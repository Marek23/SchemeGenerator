package pl.com.cs.schema.bridge;

import static pl.com.cs.config.Images.getImage;

import java.util.ArrayList;

import pl.com.cs.schema.Drawable;
import pl.com.cs.schema.page.Page;
import pl.com.cs.schema.Point;

public class AboveContactor extends Drawable {
	public AboveContactor (Page page, float x, float y) {
		this.name       = "AboveContactorBridge";
		this.visibility = true;
		this.image      = getImage(name, page);

		this.x = x;
		this.y = y;

		this.width  = image.getWidth();
		this.height = image.getHeight();

		points = new ArrayList<Point>();

		points.add(Point.down(page, this, 100f, false, "L1"));
		points.add(Point.down(page, this, 200f, false, "L2"));
		points.add(Point.down(page, this, 300f, false, "L3"));

		page.add(this);
	}
}