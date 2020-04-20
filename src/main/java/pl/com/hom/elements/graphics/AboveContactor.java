package pl.com.hom.elements.graphics;

import static pl.com.hom.configuration.Resource.getImage;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Direction;
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

		points.add(Point.upOrDownPotential(parent, this, "L1________INHORLINE", Direction.Down));
		points.add(Point.upOrDownPotential(parent, this, "L2________INHORLINE", Direction.Down));
		points.add(Point.upOrDownPotential(parent, this, "L3________INHORLINE", Direction.Down));

		parent.addElement(this);
	}
}
