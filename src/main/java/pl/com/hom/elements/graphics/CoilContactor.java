package pl.com.hom.elements.graphics;

import static pl.com.hom.configuration.Resource.getImage;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.Element;
import pl.com.hom.scheme.Page;

public class CoilContactor extends Element {
	public static String techSymbol = "Q";

	public CoilContactor (Page parent, float x, float y, int number, String STEERPOT) {
		this.name       = "CoilContactor";
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
		childs = new ArrayList<Element>();

		points.add(Point.upOrDownPotential(this, "LSTER_____", Direction.Up));
		points.add(Point.upOrDownPotential(this, "GROUNDN___ELEM", Direction.Down));

		parent.addElement(this);
	}

	public void secondGear(Page parent) {
		childs.add(new JetBridgeContactor(parent, this));
		childs.add(new SecGearContactor(parent, this));
	}

	public void firstGear(Page parent) {
		childs.add(new FirstGearContactor(parent, this));
	}
}
