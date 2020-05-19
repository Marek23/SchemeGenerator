package pl.com.hom.element.secondary;

import java.util.ArrayList;

import pl.com.hom.connections.Point;
import pl.com.hom.page.Page;
import pl.com.hom.element.Element;
import pl.com.hom.element.main.Plc;
import pl.com.hom.element.main.SteeringCoil;

import static pl.com.hom.configuration.Measures.scaled;
import static pl.com.hom.configuration.Resource.getImage;

public class PlcSignal extends Element {
	private static float xSymbolMargin = 8f;
	private static float ySymbolMargin = 20f;

	private static float xParentMargin = 3f;
	private static float yParentMargin = 20f;

	private static float xNumberMargin = 30f;
	private static float yNumberMargin = 20f;

	private String signal;

	private float connectionPointWidth;

	public PlcSignal(Page page, Plc parent, String type, String signal, float x, float y, boolean up) {
		if (up)
			this.name = "PlcSignalUp";
		else
			this.name = "PlcSignalDown";

		this.visibility = true;
		this.image      = getImage(name, page);
		this.page       = page;

		this.x = x;
		this.y = y;
		this.page = page;

		this.width  = image.getWidth();
		this.height = image.getHeight();
		
		this.symbol = parent.symbol();

		ySymbolMargin = this.height()/1.5f;
		this.symbolX = this.widthPos() + xSymbolMargin;
		this.symbolY = this.heightPos() + ySymbolMargin;

		yParentMargin = this.height()/1.5f + 10f;
		this.parentX = this.widthPos() + xParentMargin;
		this.parentY = this.heightPos() + yParentMargin;
		this.parentPageNr = parent.page().nr();

		yNumberMargin = this.height()/1.5f + 10f;
		this.numberX = this.widthPos() + xNumberMargin;
		this.numberY = this.heightPos() + yNumberMargin;

		this.signal = signal;

		points = new ArrayList<Point>();

		this.connectionPointWidth = 200f;
		if (up)
			points.add(Point.up(page, this, connectionPointWidth, false, "DC24"));
		else
			points.add(Point.down(page, this, connectionPointWidth, false, "DC24"));

		this.parent = parent;
		parent.add(this, type);

		page.add(this);
	}

	public String signal() {
		return signal;
	}

	public void number(String nr) {
		this.number = nr;
	}

	public void addSteeringCoil() {
		new SteeringCoil(this.page, this);
	}

	public float coilWidthPos() {
		return this.x + scaled(connectionPointWidth) - scaled(100f);
	}

	public float contactWidthPos() {
		return this.x + scaled(connectionPointWidth) - scaled(100f);
	}
}
