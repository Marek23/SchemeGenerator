package pl.com.cs.schema.child;

import java.util.ArrayList;

import pl.com.cs.schema.Drawable;
import pl.com.cs.schema.main.CoilMain;
import pl.com.cs.schema.page.Page;
import pl.com.cs.schema.Point;

import static pl.com.cs.config.Measures.scaled;
import static pl.com.cs.config.Heights.y;
import static pl.com.cs.config.Images.getImage;

public class CoilMotorLaunchChild extends Drawable {
	private static final float yParentMargin = 6f;

	public CoilMotorLaunchChild(Page page, CoilMain main, PlcChild signal) {
		this.name       = "SteeringContact";
		this.visibility = true;
		this.image      = getImage(name, page);
		this.page       = page;

		this.x = signal.contactWidthPos();
		this.y = y("steeringContact");
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

		points.add(Point.up(page, this, 100f, false, "MAINL10"));
		points.add(Point.down(page, this, 100f, false, signal.signal()));

		page.add(this);

		this.main = main;

		float xSter = this.x + scaled(200f);
		float ySter = y("steeringPot");;

		Point.upRight(page, this.x + scaled(100f), ySter, false, signal.signal());

		page.end(Point.leftRight(page, xSter, ySter, signal.signal()));

//		TODO
//		new ContactMksPointer(this, main);
	}
}
