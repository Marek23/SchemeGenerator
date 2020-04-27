package pl.com.hom.elements.graphics;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.Element;
import pl.com.hom.elements.bridges.UpDownRightPhases;
import pl.com.hom.scheme.Page;

import static pl.com.hom.configuration.Resource.getImage;

public class Mks extends Element {
	public Mks(Page parent, float x, float y) {
		this.name       = "Mks";
		this.visibility = true;
		this.image      = getImage(name, parent.getDocument());

		this.x = x;
		this.y = y;
		
		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;
		
		this.symbol = symbol(parent, "MKS");

		this.symbolX = x - 20f;
		this.symbolY = 595.0f - y;

		points = new ArrayList<Point>();

		points.add(Point.left(parent, this, 100f, false, "L1________"));
		points.add(Point.left(parent, this, 200f, false, "L2________"));
		points.add(Point.left(parent, this, 300f, false, "L3________"));

		points.add(Point.up(parent, this, 100f, false, "MAINDC24__"));

		points.add(Point.down(parent, this, 100f, false, "GROUNDDC__"));
		points.add(Point.down(parent, this, 200f, false, "GROUNDPE__"));

		parent.add(this);
	}

	public void jetControl(Page parent) {
		float x = Measures.THIRD_JET_COL;
		new UpDownRightPhases(parent, x, this.y);
	}
}
