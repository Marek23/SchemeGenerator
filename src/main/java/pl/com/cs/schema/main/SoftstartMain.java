package pl.com.cs.schema.main;

import java.util.ArrayList;

import pl.com.cs.schema.Main;
import pl.com.cs.schema.Point;
import pl.com.cs.schema.page.Page;

import static pl.com.cs.config.Widths.x;
import static pl.com.cs.config.Heights.y;
import static pl.com.cs.config.Measures.scaled;
import static pl.com.cs.config.Images.getImage;

public class SoftstartMain extends Main {
	public SoftstartMain(Page page, String steerPotential) {
		this.name       = "Softstart";
		this.visibility = true;
		this.image      = getImage(name, page);

//		fix bo L1 przesunięte do 400f
		this.x = x("softstart") - scaled(300f);
		this.y = y("softstart");
		this.page = page;

		this.width  = image.getWidth();
		this.height = image.getHeight();
		
		this.symbol = symbol("TR");

		this.symbolX = x;
		this.symbolY = y + this.height/2;

		points = new ArrayList<Point>();

		points.add(Point.up(page, this, 400f, false, "L1"));
		points.add(Point.up(page, this, 500f, false, "L2"));
		points.add(Point.up(page, this, 600f, false, "L3"));
		points.add(Point.up(page, this, 100f, false, "MAINL10"));

		points.add(Point.down(page, this, 400f, false, "L1"));
		points.add(Point.down(page, this, 500f, false, "L2"));
		points.add(Point.down(page, this, 600f, false, "L3"));
		points.add(Point.down(page, this, 100f, false, "GROUNDN"));

		points.add(Point.left(page, this, 200f, false, steerPotential));

		page.add(this);
	}
}
