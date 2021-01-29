package pl.com.cs.schema.main;

import java.util.ArrayList;

import pl.com.cs.schema.Main;
import pl.com.cs.schema.Point;
import pl.com.cs.schema.page.Page;

import static pl.com.cs.config.Widths.x;
import static pl.com.cs.config.Heights.y;
import static pl.com.cs.config.Images.getImage;

public class OvervoltageMain extends Main {
	public OvervoltageMain(Page page) {
		this.name       = "Overvoltage";
		this.visibility = true;
		this.image      = getImage(name, page);

//		fix bo L1 przesunięte do 400f
		this.x = x("2");
		this.y = y("coil");
		this.page = page;

		this.width  = image.getWidth();
		this.height = image.getHeight();
		
		this.symbol = symbol("FQ");

		this.symbolX = x;
		this.symbolY = y + this.height/2;

		points = new ArrayList<Point>();

		points.add(Point.up(page, this, 200f, false, "MAINL1"));
		points.add(Point.up(page, this, 300f, false, "MAINL2"));
		points.add(Point.up(page, this, 400f, false, "MAINL3"));

		points.add(Point.down(page, this, 300f, false, "GROUNDN"));
		points.add(Point.down(page, this, 500f, false, "GROUNDPE"));

		page.add(this);
	}
}
