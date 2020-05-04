package pl.com.hom.element.secondary;

import static pl.com.hom.configuration.Resource.getImage;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Point;
import pl.com.hom.element.pointer.ContactCkfPointer;
import pl.com.hom.element.pointer.Pointer;
import pl.com.hom.element.Element;
import pl.com.hom.scheme.Page;

public class CkfContact extends Element {
	public CkfContact (Page page, Element parent, float x, float y) {
		this.name       = "CkfContact";
		this.visibility = true;
		this.image      = getImage(name, page);
		this.parent     = parent;

		this.x = x;
		this.y = y;

		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;
		
		this.symbol = parent.symbol();

		this.symbolX = x - 18f;
		this.symbolY = y + this.height()/1.5f;

		points = new ArrayList<Point>();
		pointers = new ArrayList<Pointer>();

		points.add(Point.up(page, this, 100f, false,   "CONTROL"));
		points.add(Point.down(page, this, 100f, false, "CONTROL"));

		this.parent = parent;
		parent.add(new ContactCkfPointer(this, parent.widthPos(), parent.pointerHeightPos()));
	}
}
