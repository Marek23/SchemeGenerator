package pl.com.hom.elements.graphics;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.Element;
import pl.com.hom.scheme.Page;

import static pl.com.hom.configuration.Resource.getImage;

public class SecGearContactor extends Element {
	public SecGearContactor (Page parent, Element element) {
		this.name       = "FirstGearContactor";
		this.visibility = true;
		this.image      = getImage(name);

		this.x = Measures.THIRD_JET_COL;
		this.y = Measures.CONTACTOR_HEIGHT;

		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;

		this.techName = element.techName();

		this.nameXPos = this.widthPos() - 22f;
		this.nameYPos = 595.0f - this.heightPos() - this.height()/1.5f;

		points = new ArrayList<Point>();

		points.add(Point.upOrDownPotential(this, "L1________INHORLINE", Direction.Up));
		points.add(Point.upOrDownPotential(this, "L2________INHORLINE", Direction.Up));
		points.add(Point.upOrDownPotential(this, "L3________INHORLINE", Direction.Up));

		points.add(Point.upOrDownPotential(this, "L1________INHORLINE", Direction.Down));
		points.add(Point.upOrDownPotential(this, "L2________INHORLINE", Direction.Down));
		points.add(Point.upOrDownPotential(this, "L3________INHORLINE", Direction.Down));

		parent.addElement(this);
	}
}
