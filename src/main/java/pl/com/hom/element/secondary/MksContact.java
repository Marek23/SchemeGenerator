package pl.com.hom.element.secondary;

import java.util.ArrayList;

import pl.com.hom.connections.Point;
import pl.com.hom.element.pointer.ContactMksPointer;
import pl.com.hom.page.Page;
import pl.com.hom.element.Element;
import pl.com.hom.element.main.Mks;

import static pl.com.hom.configuration.Resource.getImage;

public class MksContact extends Element {
	private static float xSymbolMargin = 20f;
	private static float ySymbolMargin = 20f;

	private static float xParentMargin = -12f;
	private static float yParentMargin = 20f;

	public MksContact(Page page, Mks parent, float x, float y, boolean toMain) {
		this.name       = "MksContact";
		this.visibility = true;
		this.image      = getImage(name, page);
		this.page       = page;

		this.x = x;
		this.y = y;
		this.page = page;

		this.width  = image.getWidth();
		this.height = image.getHeight();
		
		this.symbol = parent.symbol();

		ySymbolMargin = this.height()/1.5f;
		this.symbolX = this.widthPos() - xSymbolMargin;
		this.symbolY = this.heightPos() + ySymbolMargin;

		yParentMargin = this.height()/1.5f + 10f;
		this.parentX = this.widthPos() - xParentMargin;
		this.parentY = this.heightPos() + yParentMargin;
		this.parentPageNr = parent.page().nr();

		points = new ArrayList<Point>();

		if (toMain)
			points.add(Point.up(page, this, 100f, false, "MAINDC24"));
		else
			points.add(Point.up(page, this, 100f, false, "DC24"));

		points.add(Point.down(page, this, 100f, false, "DC24"));

		page.add(this);

		this.parent = parent;
		new ContactMksPointer(this, parent);
	}
}
