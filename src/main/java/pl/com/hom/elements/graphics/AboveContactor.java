package pl.com.hom.elements.graphics;

import static pl.com.hom.configuration.Resource.getImage;

import java.util.ArrayList;

import pl.com.hom.connections.Point;
import pl.com.hom.element.Element;
import pl.com.hom.page.Page;

public class AboveContactor extends Element {
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
