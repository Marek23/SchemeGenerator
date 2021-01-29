package pl.com.cs.schema.main;

import java.util.ArrayList;

import pl.com.cs.schema.Main;
import pl.com.cs.schema.Point;
import pl.com.cs.schema.page.Page;
import pl.com.cs.schema.pointer.Pointer;

import static pl.com.cs.config.Images.getImage;

public class CkfMain extends Main {
	private static final float yFuseMargin = 50f;
	private static final String ckfFuse    = "GG6A";

	public CkfMain(Page page, float x, float y) {
		this.name       = "Ckf";
		this.visibility = true;
		this.image      = getImage(name, page);

		this.x = x;
		this.y = y;
		this.page = page;
		
		this.width  = image.getWidth();
		this.height = image.getHeight();
		
		this.symbol = symbol("KF");

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

	public void addCkfFuse(String ...potential) {
		new FuseSwitchMain(this.page, this.x, this.y - yFuseMargin, ckfFuse, potential[0], potential[1], potential[2]);
	}

	public void addCkfFuse() {
		new FuseSwitchMain(this.page, this.x, this.y - yFuseMargin, ckfFuse);
	}
}
