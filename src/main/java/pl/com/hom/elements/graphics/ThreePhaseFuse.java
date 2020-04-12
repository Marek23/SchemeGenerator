package pl.com.hom.elements.graphics;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.Element;
import pl.com.hom.scheme.Page;

import static pl.com.hom.configuration.Resource.getImage;

public class ThreePhaseFuse extends Element {
	public static String techSymbol = "F";

	public ThreePhaseFuse(Page parent, float x, float y, int number) {
		this.name       = "ThreePhaseFuse";
		this.visibility = true;
		this.image      = getImage(name);

		this.x = x;
		this.y = y;
		
		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;
		
		this.techName = String.valueOf(parent.getNr()) + techSymbol + String.valueOf(number);

		this.nameXPos = x - 22f;
		this.nameYPos = 595.0f - y - this.height()/1.5f;

		points = new ArrayList<Point>();

		points.add(Point.upOrDownPotential(this, "MAINL1____INHORLINE", Direction.Up));
		points.add(Point.upOrDownPotential(this, "MAINL2____INHORLINE", Direction.Up));
		points.add(Point.upOrDownPotential(this, "MAINL3____INHORLINE", Direction.Up));

		points.add(Point.upOrDownPotential(this, "L1________INHORLINE", Direction.Down));
		points.add(Point.upOrDownPotential(this, "L2________INHORLINE", Direction.Down));
		points.add(Point.upOrDownPotential(this, "L3________INHORLINE", Direction.Down));
		
		parent.addElement(this);
	}
}
