package pl.com.hom.element.secondary;

import java.util.ArrayList;

import pl.com.hom.connections.Point;
import pl.com.hom.page.Page;
import pl.com.hom.element.Element;
import pl.com.hom.element.main.SteeringCoil;

import static pl.com.hom.configuration.Measures.scaled;
import static pl.com.hom.configuration.Heights.y;
import static pl.com.hom.configuration.Resource.getImage;

public class SteeringContact extends Element {
	private static float xSymbolMargin = 20f;
	private static float ySymbolMargin = 20f;

	private static float xParentMargin = -12f;
	private static float yParentMargin = 20f;

	public SteeringContact(Page page, SteeringCoil parent, PlcSignal signal) {
		this.name       = "SteeringContact";
		this.visibility = true;
		this.image      = getImage(name, page);
		this.page       = page;

		this.x = signal.contactWidthPos();
		this.y = y("steeringContact");
		this.page = page;

		this.width  = image.getWidth();
		this.height = image.getHeight();
		
		this.symbol = parent.symbol();

		ySymbolMargin = this.height()/1.5f;
		this.symbolX = this.widthPos() - xSymbolMargin;
		this.symbolY = this.heightPos() + ySymbolMargin;

		yParentMargin = this.height()/1.5f + 10f;
		this.parentX = this.widthPos() - xParentMargin;
		this.parentY = this.heightPos() + yParentMargin;
		this.parentPageNr = parent.page().nr();

		points = new ArrayList<Point>();

		points.add(Point.up(page, this, 100f, false, "MAINL10"));
		points.add(Point.down(page, this, 100f, false, signal.signal()));

		page.add(this);

		this.parent = parent;

		float xSter = this.x + scaled(200f);
		float ySter = y("steeringPot");;

		Point.upRight(page, this.x + scaled(100f), ySter, false, signal.signal());

		page.end(Point.leftRight(page, xSter, ySter, signal.signal()));

//		TODO
//		new ContactMksPointer(this, parent);
	}
}
