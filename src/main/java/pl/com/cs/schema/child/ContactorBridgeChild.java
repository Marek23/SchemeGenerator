package pl.com.cs.schema.child;

import java.util.ArrayList;

import pl.com.cs.schema.Drawable;
import pl.com.cs.schema.Point;
import pl.com.cs.schema.bridge.AboveContactor;
import pl.com.cs.schema.bridge.UpLeftPhases;
import pl.com.cs.schema.main.ContactorMain;
import pl.com.cs.schema.page.Page;
import pl.com.cs.schema.pointer.ContactorPointer;

import static pl.com.cs.config.Images.getImage;
import static pl.com.cs.config.Heights.y;
import static pl.com.cs.config.Measures.scaled;

public class ContactorBridgeChild extends Drawable {
	private static final float yParentMargin = 6f;

	public ContactorBridgeChild(Page page, ContactorMain main, float x, float y) {
		this.name       = "BridgeContactor";
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

		points.add(Point.up(page, this, 100f, false, "L1"));
		points.add(Point.up(page, this, 200f, false, "L2"));
		points.add(Point.up(page, this, 300f, false, "L3"));

		points.add(Point.down(page, this, 100f, false, "L1"));
		points.add(Point.down(page, this, 200f, false, "L2"));
		points.add(Point.down(page, this, 300f, false, "L3"));

		new AboveContactor(page, x, y - y("spaceUp") - scaled(100f));

		new UpLeftPhases(page, x, y + this.height() + y("spaceDown"));

		page.add(this);

		this.main = main;
		new ContactorPointer(main, this);
	}
}
