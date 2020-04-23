package pl.com.hom.elements.contacts;

import static pl.com.hom.configuration.Resource.getImage;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.Element;
import pl.com.hom.scheme.Page;

public class Contact extends Element {
	public static String techSymbol = "Q";

	public Contact (Page parent, float x, float y, int number, String steerPotential) {
		this.name       = "Contact";
		this.visibility = true;
		this.image      = getImage(name, parent.getDocument());

		this.x = x;
		this.y = y;
		
		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;
		
		this.symbol = symbol(parent, "Q");

		this.symbolX = x - 22f;
		this.symbolY = 595.0f - y - this.height()/1.5f;

		points = new ArrayList<Point>();
		childs = new ArrayList<Element>();

		points.add(Point.upOrDownPotential(parent, this, steerPotential, Direction.Up));
		points.add(Point.upOrDownPotential(parent, this, "GROUNDN___ELEM", Direction.Down));

		parent.addElement(this);
	}
}
