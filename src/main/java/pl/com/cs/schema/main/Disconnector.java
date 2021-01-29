package pl.com.cs.schema.main;

import java.util.ArrayList;

import pl.com.cs.schema.Main;
import pl.com.cs.schema.Point;
import pl.com.cs.schema.page.Page;

import static pl.com.cs.config.Widths.x;
import static pl.com.cs.config.Heights.y;
import static pl.com.cs.config.Images.getImage;

public class Disconnector extends Main {
	public Disconnector(Page page, float current) {
		if (current > 160f)
			this.name = "todo";
		else
			this.name = "Disconnector4P";

		this.visibility = true;
		this.image      = getImage(name, page);

//		fix bo L1 przesunięte do 400f
		this.x = x("pageBegin");
		this.y = y("receiver");
		this.page = page;

		this.width  = image.getWidth();
		this.height = image.getHeight();
		
		this.symbol = symbol("Q");

		this.symbolX = x;
		this.symbolY = y + this.height/2;

		points = new ArrayList<Point>();

		points.add(Point.up(page, this, 100f, false, "MAINL1"));
		points.add(Point.up(page, this, 200f, false, "MAINL2"));
		points.add(Point.up(page, this, 300f, false, "MAINL3"));
		points.add(Point.up(page, this, 400f, false, "GROUNDN"));
		points.add(Point.up(page, this, 500f, false, "GROUNDPE"));

		page.add(this);
	}
}
