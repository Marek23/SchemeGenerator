package pl.com.hom.elements.graphics;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.Element;
import pl.com.hom.elements.bridges.UpDownRightPhases;
import pl.com.hom.scheme.Page;

import static pl.com.hom.configuration.Resource.getImage;

public class Mks extends Element {
	public static String techSymbol = "MKS";

	public Mks(Page parent, float x, float y) {
		this.name       = "Mks";
		this.visibility = true;
		this.image      = getImage(name);

		this.x = x;
		this.y = y;
		
		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;
		
		this.symbol = symbol(parent, "MKS");

		this.symbolX = x - 20f;
		this.symbolY = 595.0f - y;

		points = new ArrayList<Point>();

		points.add(Point.leftPoint(this, "L1________INVERLINE"));
		points.add(Point.leftPoint(this, "L2________INVERLINE"));
		points.add(Point.leftPoint(this, "L3________INVERLINE"));

		points.add(Point.upOrDownPotential(this, "MAINDC24__", Direction.Up));
		points.add(Point.upOrDownPotential(this, "GROUNDDC__", Direction.Down));
		points.add(Point.upOrDownPotential(this, "GROUNDPE__", Direction.Down));

		parent.addElement(this);
	}

	public void jetControl(Page parent) {
		float x = Measures.THIRD_JET_COL;
		new UpDownRightPhases(parent, x, this.y);
	}
}
