package pl.com.hom.elements.graphics;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.Element;
import pl.com.hom.scheme.Page;

import static pl.com.hom.configuration.Resource.getImage;

public class MotorFuse3 extends Element {
	public MotorFuse3(Page parent, float x, float y, int number) {
		this.name       = "MotorFuse3";
		this.visibility = true;
		this.image      = getImage(name, parent.getDocument());

		this.x = x;
		this.y = y;
		
		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;
		
		this.symbol = symbol(parent, "F");

		this.symbolX = x - 22f;
		this.symbolY = 595.0f - y - this.height()/1.5f;

		points = new ArrayList<Point>();

		points.add(Point.upOrDownPotential(parent, this, "MAINL1____INHORLINE", Direction.Up));
		points.add(Point.upOrDownPotential(parent, this, "MAINL2____INHORLINE", Direction.Up));
		points.add(Point.upOrDownPotential(parent, this, "MAINL3____INHORLINE", Direction.Up));

		points.add(Point.upOrDownPotential(parent, this, "L1________INHORLINE", Direction.Down));
		points.add(Point.upOrDownPotential(parent, this, "L2________INHORLINE", Direction.Down));
		points.add(Point.upOrDownPotential(parent, this, "L3________INHORLINE", Direction.Down));
		
		parent.addElement(this);

//		centrowanie obrazu:
		float margin = 50f * Measures.SCALE;
		this.x = x - margin;
	}
}
