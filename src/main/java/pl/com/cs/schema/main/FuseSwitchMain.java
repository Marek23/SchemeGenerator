package pl.com.cs.schema.main;

import java.util.ArrayList;

import pl.com.cs.schema.Fuse;
import pl.com.cs.schema.Point;
import pl.com.cs.schema.page.Page;

import static pl.com.cs.config.Images.getImage;

public class FuseSwitchMain extends Fuse {
	private static final float yFuseMargin = 6f;

	public FuseSwitchMain(Page page, float x, float y, String fuse) {
		this.name       = "ThreePhaseFuse";
		this.visibility = true;
		this.image      = getImage(name, page);

		this.x = x;
		this.y = y;
		this.page = page;
		
		this.width  = image.getWidth();
		this.height = image.getHeight();
		
		this.symbol = symbol(page, "F");
		this.fuse   = fuse;

		this.symbolX = x;
		this.symbolY = y  + this.height/2;

		this.fuseX   = this.x;
		this.fuseY   = this.symbolY + yFuseMargin;

		points = new ArrayList<Point>();

		points.add(Point.up(page, this, 100f, false, "MAINL1"));
		points.add(Point.up(page, this, 200f, false, "MAINL2"));
		points.add(Point.up(page, this, 300f, false, "MAINL3"));

		points.add(Point.down(page, this, 100f, false, "L1"));
		points.add(Point.down(page, this, 200f, false, "L2"));
		points.add(Point.down(page, this, 300f, false, "L3"));
		
		page.add(this);
	}

	public FuseSwitchMain(Page page, float x, float y, String fuse, String ...potential) {
		this.name       = "ThreePhaseFuse";
		this.visibility = true;
		this.image      = getImage(name, page);

		this.x = x;
		this.y = y;
		this.page = page;
		
		this.width  = image.getWidth();
		this.height = image.getHeight();
		
		this.symbol = symbol(page, "F");
		this.fuse   = fuse;

		this.symbolX = x;
		this.symbolY = y  + this.height/2;

		this.fuseX   = this.x;
		this.fuseY   = this.symbolY + yFuseMargin;

		points = new ArrayList<Point>();

		points.add(Point.up(page, this, 100f, false, potential[0]));
		points.add(Point.up(page, this, 200f, false, potential[1]));
		points.add(Point.up(page, this, 300f, false, potential[2]));

		points.add(Point.down(page, this, 100f, false, "L1"));
		points.add(Point.down(page, this, 200f, false, "L2"));
		points.add(Point.down(page, this, 300f, false, "L3"));
		
		page.add(this);
	}
}
