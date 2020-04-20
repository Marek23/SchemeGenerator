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

		points.add(Point.leftPoint(parent, this, "L1________LEFT"));
		points.add(Point.leftPoint(parent, this, "L2________LEFT"));
		points.add(Point.leftPoint(parent, this, "L3________LEFT"));

		points.add(Point.rightPoint(parent, this, "L1________"));
		points.add(Point.rightPoint(parent, this, "L2________"));
		points.add(Point.rightPoint(parent, this, "L3________"));

		Point pe = Point.pe(parent, this, 200f * Measures.SCALE, 0f);
		points.add(pe);
		
		parent.addElement(this);

		Element left  = new UpRightPhases(parent, Measures.SECOND_JET_COL, y);
		Element right = new UpLeftPhases(parent, Measures.THIRD_JET_COL, y);

		for (Point p: left.points())
			parent.terminal(new Terminal(parent, p, "X2"));

		for (Point p: right.points())
			parent.terminal(new Terminal(parent, p, "X2"));

		parent.terminal(new Terminal(parent, pe, "X2"));
	}
}
