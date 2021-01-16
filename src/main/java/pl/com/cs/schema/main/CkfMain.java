package pl.com.cs.schema.main;

import java.util.ArrayList;

import pl.com.cs.schema.Drawable;
import pl.com.cs.schema.Point;
import pl.com.cs.schema.page.Page;
import pl.com.cs.schema.pointer.Pointer;

import static pl.com.cs.config.Images.getImage;

public class CkfMain extends Drawable {
	public CkfMain(Page page, float x, float y) {
		this.name       = "Ckf";
		this.visibility = true;
		this.image      = getImage(name, page);

		this.x = x;
		this.y = y;
		this.page = page;
		
		this.width  = image.getWidth();
		this.height = image.getHeight();
		
		this.symbol = symbol(page, "CKF");

		this.symbolX = x;
		this.symbolY = y + this.height/2;

		points   = new ArrayList<Point>();
		pointers = new ArrayList<Pointer>();

		points.add(Point.up(page, this, 100f, false, "L1"));
		points.add(Point.up(page, this, 200f, false, "L2"));
		points.add(Point.up(page, this, 300f, false, "L3"));

		points.add(Point.down(page, this, 200f, false, "GROUNDN"));

		page.add(this);
	}
}
