package pl.com.hom.element.secondary;

import static pl.com.hom.configuration.Resource.getImage;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Point;
import pl.com.hom.element.pointer.ContactMksPointer;
import pl.com.hom.page.Page;
import pl.com.hom.element.Element;
import pl.com.hom.element.main.Plc;

public class PlcSignal extends Element {
	public PlcSignal(Page page, Plc parent, float x, float y, boolean up) {
		if (up == true)
			this.name = "PlcSignalUp";
		else
			this.name = "PlcSignalDown";

		this.visibility = true;
		this.image      = getImage(name, page);
		this.page       = page;

		this.x = x;
		this.y = y;
		this.page = page;

		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;
		
		this.symbol = parent.symbol();

		this.symbolX = this.widthPos() - 22f;
		this.symbolY = this.heightPos() + this.height()/1.5f;

		this.parentX = this.widthPos() - 12f;
		this.parentY = this.heightPos() + this.height()/1.5f + 10f;
		this.parentPageNr = parent.page().nr();

		points = new ArrayList<Point>();

		points.add(Point.up(page, this, 100f, false, "MAINDC24"));
		points.add(Point.up(page, this, 100f, false, "DC24"));

		points.add(Point.down(page, this, 100f, false, "DC24"));

		page.add(this);

		this.parent = parent;
		new ContactMksPointer(this, parent);
	}
}
