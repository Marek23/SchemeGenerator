package pl.com.hom.element.secondary;

import static pl.com.hom.configuration.Resource.getImage;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Point;
import pl.com.hom.page.Page;
import pl.com.hom.element.Element;
import pl.com.hom.element.main.Plc;
import pl.com.hom.element.main.SteeringCoil;

public class PlcSignal extends Element {
	private String steeringName;

	private float steeringX;
	private float steeringY;

	public PlcSignal(Page page, Plc parent, String type, String steeringName, float x, float y, boolean up) {
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

		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;
		
		this.symbol = parent.symbol();

		this.symbolX = this.widthPos() - 22f;
		this.symbolY = this.heightPos() + this.height()/1.5f;

		this.parentX = this.widthPos() - 12f;
		this.parentY = this.heightPos() + this.height()/1.5f + 10f;
		this.parentPageNr = parent.page().nr();

		this.steeringX = this.widthPos() - 22f;
		this.steeringY = this.heightPos() + this.height()/1.5f;
		this.steeringName = steeringName;

		points = new ArrayList<Point>();

		if (up)
			points.add(Point.up(page, this, 200f, false, "DC24"));
		else
			points.add(Point.down(page, this, 200f, false, "DC24"));

		this.parent = parent;
		parent.add(this, type);

		page.add(this);

//		TODO
//		new ContactMksPointer(this, parent);
	}

	public float steeringX() {
		return steeringX;
	}

	public float steeringY() {
		return steeringY;
	}

	public String steeringName() {
		return steeringName;
	}

	public void addSteeringCoil() {
		new SteeringCoil(this.page, this);
	}
}
