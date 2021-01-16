package pl.com.cs.schema.child;

import java.util.ArrayList;

import pl.com.cs.schema.Drawable;
import pl.com.cs.schema.Point;
import pl.com.cs.schema.main.ContactorMain;
import pl.com.cs.schema.page.Page;

import static pl.com.cs.config.Images.getImage;
import static pl.com.cs.config.Heights.y;

public class ContactorSingleChild extends Drawable {
	private static final float yParentMargin = 6f;

	public ContactorSingleChild(Page page, ContactorMain main, ContactorMain paired) {
		this.name       = "SingleContactor";
		this.visibility = true;
		this.image      = getImage(name, page);
		this.page       = page;

		this.x = paired.widthPos();
		this.y = y("coilSeparator");
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

		points.add(Point.up(page, this, 100f, false, paired.steering()));
		points.add(Point.down(page, this, 100f, false, paired.steering()));

		page.add(this);

		this.main = main;
	}
}
