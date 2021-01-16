package pl.com.cs.schema.child;

import pl.com.cs.schema.Drawable;
import pl.com.cs.schema.main.CoilMain;
import pl.com.cs.schema.main.PlcMain;
import pl.com.cs.schema.page.Page;
import pl.com.cs.schema.Point;

import java.util.ArrayList;

import static pl.com.cs.config.Measures.scaled;
import static pl.com.cs.config.Images.getImage;

public class PlcChild extends Drawable {
	private static final float yParentMargin = 6f;
	private static final float yNumberMargin = 6f;

	private String signal;
	private float connectionPointWidth;

	public PlcChild(Page page, PlcMain main, String type, String signal, float x, float y, boolean up) {
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
		
		this.symbol = main.symbol();

		this.symbolX = this.widthPos();
		this.symbolY = this.heightPos() + this.height/2;

		this.mainX = this.widthPos();
		this.mainY = this.symbolY + yParentMargin;
		this.mainPageNr = main.page().nr();

		this.numberX = this.widthPos();
		this.numberY = this.mainY + yNumberMargin;

		this.signal = signal;

		points = new ArrayList<Point>();

		this.connectionPointWidth = 200f;
		if (up)
			points.add(Point.up(page, this, connectionPointWidth, false, "DC24"));
		else
			points.add(Point.down(page, this, connectionPointWidth, false, "DC24"));

		this.main = main;
		main.add(this, type);

		page.add(this);
	}

	public String signal() {
		return signal;
	}

	public void number(String nr) {
		this.number = nr;
	}

	public void addCoilMotorSteering() {
		CoilMain.coilMotorSteeringChild(this.page, this);
	}

	public void addCoilSapOutput(float contactPos) {
		CoilMain.coilSapOutputChild(this.page, this, contactPos);
	}

	public float coilWidthPos() {
		return this.x + scaled(connectionPointWidth) - scaled(100f);
	}

	public float contactWidthPos() {
		return this.x + scaled(connectionPointWidth) - scaled(100f);
	}
}
