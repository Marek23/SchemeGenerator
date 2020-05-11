package pl.com.hom.element.secondary;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Point;
import pl.com.hom.element.Element;
import pl.com.hom.element.main.CurrentCoil;
import pl.com.hom.element.pointer.ContactorPointer;
import pl.com.hom.page.Page;

import static pl.com.hom.configuration.Resource.getImage;

public class Contactor extends Element {
	public Contactor (Page page, CurrentCoil parent, float x, float y, boolean reverse) {
		this.name       = "FirstGearContactor";
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

		if (reverse) {
			points.add(Point.up(page, this, 100f, false, "L3"));
			points.add(Point.up(page, this, 200f, false, "L2"));
			points.add(Point.up(page, this, 300f, false, "L1"));
		}
		else {
			points.add(Point.up(page, this, 100f, false, "L1"));
			points.add(Point.up(page, this, 200f, false, "L2"));
			points.add(Point.up(page, this, 300f, false, "L3"));			
		}

		points.add(Point.down(page, this, 100f, false, "L1"));
		points.add(Point.down(page, this, 200f, false, "L2"));
		points.add(Point.down(page, this, 300f, false, "L3"));

		page.add(this);

		this.parent = parent;
		new ContactorPointer(this, parent);
	}
}
