package pl.com.hom.element.secondary;

import java.util.ArrayList;

import pl.com.hom.connections.Point;
import pl.com.hom.page.Page;
import pl.com.hom.element.Element;
import pl.com.hom.element.pointer.ContactCkfPointer;
import pl.com.hom.element.pointer.Pointer;

import static pl.com.hom.configuration.Resource.getImage;

public class CkfContact extends Element {
	private static float xSymbolMargin = 20f;
	private static float ySymbolMargin = 20f;

	private static float xParentMargin = 12f;
	private static float yParentMargin = 20f;

	public CkfContact (Page page, Element parent, float x, float y) {
		this.name       = "CkfContact";
		this.visibility = true;
		this.image      = getImage(name, page);
		this.parent     = parent;

		this.x = x;
		this.y = y;
		this.page = page;

		this.width  = image.getWidth();
		this.height = image.getHeight();
		
		this.symbol = parent.symbol();

		ySymbolMargin = this.height()/1.5f;
		this.symbolX = x - xSymbolMargin;
		this.symbolY = y + ySymbolMargin;

		yParentMargin = this.height()/1.5f + 10f;
		this.parentX = this.widthPos() - xParentMargin;
		this.parentY = this.heightPos() + yParentMargin;
		this.parentPageNr = parent.page().nr();

		points = new ArrayList<Point>();
		pointers = new ArrayList<Pointer>();

		points.add(Point.up(page, this, 100f, false,   "CONTROL"));
		points.add(Point.down(page, this, 100f, false, "CONTROL"));

		this.parent = parent;
		new ContactCkfPointer(this, parent);
	}
}
