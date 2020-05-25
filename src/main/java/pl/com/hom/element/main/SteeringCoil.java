package pl.com.hom.element.main;


import java.util.ArrayList;

import pl.com.hom.connections.Point;
import pl.com.hom.element.pointer.Pointer;
import pl.com.hom.element.secondary.PlcSignal;
import pl.com.hom.element.secondary.SteeringContact;
import pl.com.hom.element.secondary.SapOutContact;
import pl.com.hom.page.Page;
import pl.com.hom.element.Element;

import static pl.com.hom.configuration.Heights.y;
import static pl.com.hom.configuration.Resource.getImage;

public class SteeringCoil extends Element {
	private static float xSymbolMargin = 20f;
	private static float ySymbolMargin;

	public static SteeringCoil steering(Page page, PlcSignal signal) {
		SteeringCoil c = new SteeringCoil(page, signal);
		new SteeringContact(page, c, signal);
		return c;
	}

	public static SteeringCoil sapOut(Page page, PlcSignal signal, float contactPos) {
		SteeringCoil c = new SteeringCoil(page, signal);
		new SapOutContact(page, c, contactPos);
		return c;
	}

	private SteeringCoil (Page page, PlcSignal signal) {
		this.name       = "Coil";
		this.visibility = true;
		this.image      = getImage(name, page);

		this.x = signal.coilWidthPos();
		this.y = y("coil");
		this.page = page;

		this.width  = image.getWidth();
		this.height = image.getHeight();
		
		this.symbol = symbol(page, "K");

		ySymbolMargin = this.height()/1.5f;
		this.symbolX = x - xSymbolMargin;
		this.symbolY = y + ySymbolMargin;

		points   = new ArrayList<Point>();
		pointers = new ArrayList<Pointer>();

		points.add(Point.up(page, this, 100f, false, "DC24"));
		points.add(Point.down(page, this, 100f, false, "GROUNDDC"));

		page.add(this);
	}
}
