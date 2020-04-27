package pl.com.hom.elements.graphics;

import static pl.com.hom.configuration.Resource.getImage;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.Element;
import pl.com.hom.scheme.Page;

public class AboveContactor extends Element {
	public AboveContactor (Page parent, float x, float y) {
		this.name       = "AboveContactorBridge";
		this.visibility = true;
		this.image      = getImage(name, parent.getDocument());

		this.x = x;
		this.y = y;

		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;

		points = new ArrayList<Point>();

		points.add(Point.down(parent, this, 100f, false, "L1________"));
		points.add(Point.down(parent, this, 200f, false, "L2________"));
		points.add(Point.down(parent, this, 300f, false, "L3________"));

		parent.add(this);
	}
}
