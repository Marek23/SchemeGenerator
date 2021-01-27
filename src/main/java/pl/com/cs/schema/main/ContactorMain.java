package pl.com.cs.schema.main;

import java.util.ArrayList;

import pl.com.cs.schema.Drawable;
import pl.com.cs.schema.page.Page;

import pl.com.cs.schema.Point;
import pl.com.cs.schema.bridge.AboveContactor;
import pl.com.cs.schema.bridge.DownLeftPhases;
import pl.com.cs.schema.bridge.UpDownRightPhases;
import pl.com.cs.schema.bridge.UpLeftPhases;
import pl.com.cs.schema.pointer.Pointer;
import pl.com.cs.schema.child.ContactorBridgeChild;
import pl.com.cs.schema.child.ContactorChild;

import static pl.com.cs.config.Images.getImage;
import static pl.com.cs.config.Widths.x;
import static pl.com.cs.config.Heights.y;
import static pl.com.cs.config.Measures.scaled;

public class ContactorMain extends Drawable {
	public ContactorMain (Page page, float x, String steering) {
		this.name       = "Coil";
		this.visibility = true;
		this.image      = getImage(name, page);

		this.steering = steering;
		this.x = x;
		this.y = y("coil");
		this.page = page;

		this.width  = image.getWidth();
		this.height = image.getHeight();
		
		this.symbol = symbol(page, "Q");

		this.symbolX  = x;
		this.symbolY  = y  + this.height/2;

		points   = new ArrayList<Point>();
		pointers = new ArrayList<Pointer>();

		points.add(Point.up(page, this, 100f, false, steering));
		points.add(Point.down(page, this, 100f, false, "GROUNDN"));

		page.add(this);
	}

	public void firstGear(Page page) {
		ContactorChild c = new ContactorChild(page, this, x("1"), y("gear"), false);

		new UpDownRightPhases(page, x("1"), y("gear") + c.height() + y("spaceDown"), false);
	}

	public void secondJetGear(Page page) {
		new ContactorChild(page, this, x("3"), y("gear"), false);

		new ContactorBridgeChild(page, this, x("2"), y("gear"));
	}

	public void secondDirJetGear(Page page) {
		new ContactorChild(page, this, x("3"), y("gear"), true);

		new ContactorBridgeChild(page, this, x("2"), y("gear"));
	}

	public void secondGear(Page page) {
		new ContactorChild(page, this, x("3"), y("gear"), false);
	}

	public void secondDirGear(Page page) {
		new ContactorChild(page, this, x("3"), y("gear"), true);
	}

	public void secondGearBridge(Page page) {
		ContactorChild c = new ContactorChild(page, this, x("2"), y("gear"), false);

		new AboveContactor(page, x("2"), c.heightPos() - y("spaceUp") - scaled(100f));
		new UpLeftPhases(page, x("2"), y("gear") + c.height() + y("spaceDown"));
	}

	public void secondBiDirectionGear(Page page) {
		new ContactorChild(page, this, x("3"), y("gear"), true);

		new ContactorBridgeChild(page, this, x("2"), y("gear"));
	}
	
	public void mainOrLeft(Page page, float x, String y) {
		ContactorChild c = new ContactorChild(page, this, x, y(y), false);
		
		new UpDownRightPhases(page, x, y(y) - y("spaceUp") - scaled(300f), false);
		new UpDownRightPhases(page, x, y(y) + c.height() + y("spaceDown"), false);
	}

	public void right(Page page, float x, String y) {
		ContactorChild c = new ContactorChild(page, this, x, y(y), true);

		new DownLeftPhases(page, x, y(y) - y("spaceUp") - scaled(300f));
		new UpLeftPhases(page,   x, y(y) + c.height() + y("spaceDown"));
	}

	public void biGearRight(Page page) {
		ContactorChild c = new ContactorChild(page, this, x("2"), y("gear"), true);

		new DownLeftPhases(page, x("2"), y("gear") - y("spaceUp") - scaled(300f));
		new UpLeftPhases(page,   x("2"), y("gear") + c.height() + y("spaceDown"));
	}
}
