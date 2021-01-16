package pl.com.cs.schema.child;

import java.util.ArrayList;

import pl.com.cs.schema.Drawable;
import pl.com.cs.schema.Point;
import pl.com.cs.schema.pointer.CkfChildPointer;
import pl.com.cs.schema.pointer.Pointer;
import pl.com.cs.schema.page.Page;

import static pl.com.cs.config.Images.getImage;

public class CkfChild extends Drawable {
	private static final float yParentMargin = 6f;

	public CkfChild (Page page, Drawable main, float x, float y) {
		this.name       = "CkfContact";
		this.visibility = true;
		this.image      = getImage(name, page);
		this.main       = main;

		this.x = x;
		this.y = y;
		this.page = page;

		this.width  = image.getWidth();
		this.height = image.getHeight();
		this.symbol = main.symbol();

		this.symbolX  = x;
		this.symbolY  = y + this.height/2;

		this.mainX      = this.widthPos();
		this.mainY      = this.symbolY + yParentMargin;
		this.mainPageNr = main.page().nr();

		points   = new ArrayList<Point>();
		pointers = new ArrayList<Pointer>();

		points.add(Point.up(page, this, 100f, false,   "CONTROL"));
		points.add(Point.down(page, this, 100f, false, "CONTROL"));

		this.main = main;
		new CkfChildPointer(main, this);
	}
}
