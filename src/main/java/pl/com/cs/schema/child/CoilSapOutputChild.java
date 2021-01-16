package pl.com.cs.schema.child;

import java.util.ArrayList;

import pl.com.cs.schema.Drawable;
import pl.com.cs.schema.Point;
import pl.com.cs.schema.main.CoilMain;
import pl.com.cs.schema.page.Page;

import static pl.com.cs.config.Measures.scaled;
import static pl.com.cs.config.Heights.y;
import static pl.com.cs.config.Images.getImage;

public class CoilSapOutputChild extends Drawable {
	private static final float yParentMargin = 6f;

	public CoilSapOutputChild(Page page, CoilMain main, float x) {
		this.name       = "SteeringContact";
		this.visibility = true;
		this.image      = getImage(name, page);
		this.page       = page;

		this.x = x - scaled(100f);
		this.y = y("sapOutContact");
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

		points.add(Point.up(page, this, 100f, false, "DC24"));
		points.add(Point.down(page, this, 100f, false, "DC24"));

		page.add(this);

		this.main = main;

		float ySter = y("sapOutPot");

		Point.downRight(page, this.x + scaled(100f), ySter, false, "DC24");
		Point.downLeft(page, this.x + scaled(300f), ySter, false, "DC24");

//		TODO
//		new ContactMksPointer(this, main);
	}
}
