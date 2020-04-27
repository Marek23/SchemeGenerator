package pl.com.hom.elements.graphics;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.Element;
import pl.com.hom.elements.bridges.UpDownRightPhases;
import pl.com.hom.scheme.Page;

import static pl.com.hom.configuration.Resource.getImage;

public class FirstGearContactor extends Element {
	public FirstGearContactor (Page parent, Element element) {
		this.name       = "FirstGearContactor";
		this.visibility = true;
		this.image      = getImage(name, parent.getDocument());

		this.x = Measures.SECOND_JET_COL;
		this.y = Measures.CONTACTOR_HEIGHT;

		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;

		this.symbol = element.symbol();

		this.symbolX = this.widthPos() - 22f;
		this.symbolY = 595.0f - this.heightPos() - this.height()/1.5f;


		points = new ArrayList<Point>();

		points.add(Point.up(parent, this, 100f, false, "L1________"));
		points.add(Point.up(parent, this, 200f, false, "L2________"));
		points.add(Point.up(parent, this, 300f, false, "L3________"));

		points.add(Point.down(parent, this, 100f, false, "L1________"));
		points.add(Point.down(parent, this, 200f, false, "L2________"));
		points.add(Point.down(parent, this, 300f, false, "L3________"));

		new UpDownRightPhases(parent, this.x, Measures.UNDER_CONTACTOR_BRIDGE_HEIGHT);

		parent.add(this);
	}
}
