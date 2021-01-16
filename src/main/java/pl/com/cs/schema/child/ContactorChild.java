package pl.com.cs.schema.child;

import java.util.ArrayList;

import pl.com.cs.schema.Drawable;
import pl.com.cs.schema.Point;
import pl.com.cs.schema.main.ContactorMain;
import pl.com.cs.schema.page.Page;
import pl.com.cs.schema.pointer.ContactorPointer;

import static pl.com.cs.config.Images.getImage;

public class ContactorChild extends Drawable {
	private static final float yParentMargin = 6f;

	public ContactorChild (Page page, ContactorMain main, float x, float y, boolean reverse) {
		this.name       = "FirstGearContactor";
		this.visibility = true;
		this.image      = getImage(name, page);
		this.page       = page;

		this.x = x;
		this.y = y;
		this.page = page;

		this.width  = image.getWidth();
		this.height = image.getHeight();

		this.symbol = main.symbol();

		this.symbolX = this.widthPos();
		this.symbolY = this.heightPos() + this.height/2;

		this.mainX = this.widthPos();
		this.mainY = this.symbolY + yParentMargin;
		this.mainPageNr = main.page().nr();

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

		this.main = main;
		new ContactorPointer(main, this);
	}
}
