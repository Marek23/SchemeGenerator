package pl.com.hom.element.main;

import static pl.com.hom.configuration.Resource.getImage;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Point;
import pl.com.hom.element.pointer.Pointer;
import pl.com.hom.element.secondary.PlcSignal;
import pl.com.hom.element.secondary.SteeringContact;
import pl.com.hom.page.Page;
import pl.com.hom.element.Element;

public class SteeringCoil extends Element {
	public SteeringCoil (Page page, PlcSignal signal) {
		this.name       = "SteeringCoil";
		this.visibility = true;
		this.image      = getImage(name, page);

		this.x = signal.widthPos() + 100f * Measures.SCALE;
		this.y = Measures.STEERING_COIL_HEIGHT;
		this.page = page;

		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;
		
		this.symbol = symbol(page, "K");

		this.symbolX = x - 18f;
		this.symbolY = y + this.height()/1.5f;

		points   = new ArrayList<Point>();
		pointers = new ArrayList<Pointer>();

		points.add(Point.up(page, this, 100f, false, "DC24"));
		points.add(Point.down(page, this, 100f, false, "GROUNDDC"));

		page.add(this);

		new SteeringContact(page, this, signal);
	}
}
