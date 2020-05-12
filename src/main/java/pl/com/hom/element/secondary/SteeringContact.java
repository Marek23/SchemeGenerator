package pl.com.hom.element.secondary;

import static pl.com.hom.configuration.Resource.getImage;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Point;
import pl.com.hom.page.Page;
import pl.com.hom.element.Element;
import pl.com.hom.element.main.SteeringCoil;

public class SteeringContact extends Element {
	public SteeringContact(Page page, SteeringCoil parent, PlcSignal signal) {
		this.name       = "SteeringContact";
		this.visibility = true;
		this.image      = getImage(name, page);
		this.page       = page;

		this.x = signal.widthPos() + 100f * Measures.SCALE;
		this.y = Measures.STEERING_HEIGHT;
		this.page = page;

		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;
		
		this.symbol = parent.symbol();

		this.symbolX = this.widthPos() - 22f;
		this.symbolY = this.heightPos() + this.height()/1.5f;

		this.parentX = this.widthPos() - 12f;
		this.parentY = this.heightPos() + this.height()/1.5f + 10f;
		this.parentPageNr = parent.page().nr();

		points = new ArrayList<Point>();

		points.add(Point.up(page, this, 100f, false, "MAINL10"));
		points.add(Point.down(page, this, 100f, false, signal.steeringName()));

		page.add(this);

		this.parent = parent;

		float xSter = this.x + 400f * Measures.SCALE;
		float ySter = Measures.STEERING_PAGE_AFTER_CONTACT_HEIGHT;
		Point.upRight(page, this.x + 100f * Measures.SCALE, ySter, signal.steeringName());
		page.end(Point.leftRight(page, xSter, ySter, signal.steeringName()));

//		TODO
//		new ContactMksPointer(this, parent);
	}
}
