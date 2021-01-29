package pl.com.cs.schema.main;

import java.util.ArrayList;

import pl.com.cs.schema.pointer.FusePointer;
import pl.com.cs.schema.FuseMain;
import pl.com.cs.schema.Point;
import pl.com.cs.schema.page.Page;

import static pl.com.cs.config.Images.getImage;

public class FuseThermal1 extends FuseMain {
	private static final float yFuseMargin = 6f;

	public FuseThermal1(Page page, float x, float y, String potentialUp, String fuse) {
		this.name       = "ThermalFuse1";
		this.visibility = true;
		this.image      = getImage(name, page);

		this.x = x;
		this.y = y;
		this.page = page;
		
		this.width  = image.getWidth();
		this.height = image.getHeight();
		
		this.symbol = symbol("F");
		this.fuse   = fuse;

		this.symbolX = x;
		this.symbolY = y + this.height/2;

		this.fuseX  = x;
		this.fuseY  = this.symbolY + yFuseMargin;

		this.fusePointers = new ArrayList<FusePointer>();

		points = new ArrayList<Point>();

		points.add(Point.up(page, this, 100f, false, potentialUp));

		points.add(Point.down(page, this, 100f, false, "L10"));
		
		page.add(this);
	}
}
