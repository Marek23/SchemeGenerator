package pl.com.hom.elements.graphics.receiver;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.Element;
import pl.com.hom.scheme.Page;

import static pl.com.hom.configuration.Resource.getImage;

public class JetEngine extends Element {
	public static String techSymbol = "JET";

	public JetEngine(Page parent, float x, float y) {
		this.name       = "JetEngine";
		this.visibility = true;
		this.image      = getImage(name);

		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;

		this.x = x;
		this.y = y;

		points = new ArrayList<Point>();

		points.add(Point.leftPoint(this, "L1________LEFT"));
		points.add(Point.leftPoint(this, "L2________LEFT"));
		points.add(Point.leftPoint(this, "L3________LEFT"));

		points.add(Point.rightPoint(this, "L1________"));
		points.add(Point.rightPoint(this, "L2________"));
		points.add(Point.rightPoint(this, "L3________"));

		parent.addElement(this);
	}
}
