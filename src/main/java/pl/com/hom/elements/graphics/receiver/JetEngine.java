package pl.com.hom.elements.graphics.receiver;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Point;
import pl.com.hom.connections.Terminal;
import pl.com.hom.elements.Element;
import pl.com.hom.elements.bridges.UpLeftPhases;
import pl.com.hom.elements.bridges.UpRightPhases;
import pl.com.hom.scheme.Page;

import static pl.com.hom.configuration.Resource.getImage;

public class JetEngine extends Element {
	public JetEngine(Page parent, float x, float y) {
		this.name       = "JetEngine";
		this.visibility = true;
		this.image      = getImage(name, parent.getDocument());

		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;

		this.x = x;
		this.y = y;

		points = new ArrayList<Point>();

		points.add(Point.left(parent, this, 300f, false, "L1________"));
		points.add(Point.left(parent, this, 200f, false, "L2________"));
		points.add(Point.left(parent, this, 100f, false, "L3________"));

		points.add(Point.right(parent, this, 100f, false, "L1________"));
		points.add(Point.right(parent, this, 200f, false, "L2________"));
		points.add(Point.right(parent, this, 300f, false, "L3________"));

		Point pe = Point.pe(parent, this, 200f, 0f, false);
		points.add(pe);
		
		parent.add(this);

		Element left  = new UpRightPhases(parent, Measures.SECOND_JET_COL, y);
		Element right = new UpLeftPhases(parent, Measures.THIRD_JET_COL, y);

		for (Point p: left.points())
			parent.add(new Terminal(parent, p, "X2"));

		for (Point p: right.points())
			parent.add(new Terminal(parent, p, "X2"));

		parent.add(new Terminal(parent, pe, "X2"));
	}
}
