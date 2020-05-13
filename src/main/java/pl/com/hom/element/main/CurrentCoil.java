package pl.com.hom.element.main;

import java.util.ArrayList;

import pl.com.hom.connections.Point;
import pl.com.hom.element.pointer.Pointer;
import pl.com.hom.element.secondary.BridgeContactor;
import pl.com.hom.element.secondary.Contactor;
import pl.com.hom.elements.bridges.DownLeftPhases;
import pl.com.hom.elements.bridges.UpDownRightPhases;
import pl.com.hom.elements.bridges.UpLeftPhases;
import pl.com.hom.elements.graphics.AboveContactor;
import pl.com.hom.page.Page;
import pl.com.hom.element.Element;

import static pl.com.hom.configuration.Resource.getImage;
import static pl.com.hom.configuration.Widths.x;
import static pl.com.hom.configuration.Heights.y;
import static pl.com.hom.configuration.Measures.scaled;

public class CurrentCoil extends Element {
	private static float xSymbolMargin = 20f;
	private static float ySymbolMargin;

	public CurrentCoil (Page page, float x, String steering) {
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

		ySymbolMargin = this.height()/1.5f;
		this.symbolX  = x - xSymbolMargin;
		this.symbolY  = y + ySymbolMargin;

		points   = new ArrayList<Point>();
		pointers = new ArrayList<Pointer>();

		points.add(Point.up(page, this, 100f, false, steering));
		points.add(Point.down(page, this, 100f, false, "GROUNDN"));

		page.add(this);
	}

	public void firstGear(Page page) {
		Contactor c = new Contactor(page, this, x("1"), y("gear"), false);

		new UpDownRightPhases(page, x("1"), y("gear") + c.height() + y("spaceDown"), false);
	}

	public void secondJetGear(Page page) {
		new Contactor(page, this, x("3"), y("gear"), false);

		new BridgeContactor(page, this, x("2"), y("gear"));
	}

	public void secondDirJetGear(Page page) {
		new Contactor(page, this, x("3"), y("gear"), true);

		new BridgeContactor(page, this, x("2"), y("gear"));
	}

	public void secondGear(Page page) {
		new Contactor(page, this, x("3"), y("gear"), false);
	}

	public void secondDirGear(Page page) {
		new Contactor(page, this, x("3"), y("gear"), true);
	}

	public void secondGearBridge(Page page) {
		Contactor c = new Contactor(page, this, x("2"), y("gear"), false);

		new AboveContactor(page, x("2"), c.heightPos() - y("spaceUp") - scaled(100f));
		new UpLeftPhases(page, x("2"), y("gear") + c.height() + y("spaceDown"));
	}

	public void secondBiDirectionGear(Page page) {
		new Contactor(page, this, x("3"), y("gear"), true);

		new BridgeContactor(page, this, x("2"), y("gear"));
	}
	
	public void left(Page page, float x, String y) {
		Contactor c = new Contactor(page, this, x, y(y), false);
		
		new UpDownRightPhases(page, x, y(y) - y("spaceUp") - scaled(300f), false);
		new UpDownRightPhases(page, x, y(y) + c.height() + y("spaceDown"), false);
	}

	public void right(Page page, float x, String y) {
		Contactor c = new Contactor(page, this, x, y(y), true);

		new DownLeftPhases(page, x, y(y) - y("spaceUp") - scaled(300f));
		new UpLeftPhases(page,   x, y(y) + c.height() + y("spaceDown"));
	}

	public void biGearRight(Page page) {
		Contactor c = new Contactor(page, this, x("2"), y("gear"), true);

		new DownLeftPhases(page, x("2"), y("gear") - y("spaceUp") - scaled(300f));
		new UpLeftPhases(page,   x("2"), y("gear") + c.height() + y("spaceDown"));
	}
}
