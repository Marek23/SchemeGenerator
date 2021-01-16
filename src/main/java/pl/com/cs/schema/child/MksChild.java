package pl.com.cs.schema.child;

import java.util.ArrayList;

import pl.com.cs.schema.Drawable;
import pl.com.cs.schema.page.Page;
import pl.com.cs.schema.pointer.MksChildPointer;
import pl.com.cs.schema.Point;
import pl.com.cs.schema.main.MksMain;

import static pl.com.cs.config.Images.getImage;

public class MksChild extends Drawable {
	private static final float yParentMargin = 6f;

	public MksChild(Page page, MksMain main, float x, float y, boolean linkToMainDc24) {
		this.name       = "MksContact";
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

		if (linkToMainDc24)
			points.add(Point.up(page, this, 100f, false, "MAINDC24"));
		else
			points.add(Point.up(page, this, 100f, false, "DC24"));

		points.add(Point.down(page, this, 100f, false, "DC24"));

		page.add(this);

		this.main = main;
		new MksChildPointer(main, this);
	}
}
