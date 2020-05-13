package pl.com.hom.element.main;

import java.util.ArrayList;

import pl.com.hom.connections.Point;
import pl.com.hom.element.Element;
import pl.com.hom.element.pointer.Pointer;
import pl.com.hom.element.secondary.MksContact;
import pl.com.hom.elements.bridges.UpDownRightPhases;
import pl.com.hom.page.Page;

import static pl.com.hom.configuration.Widths.x;
import static pl.com.hom.configuration.Heights.y;
import static pl.com.hom.configuration.Resource.getImage;

public class Mks extends Element {
	private static float xSymbolMargin = 20f;

	public Mks(Page page) {
		this.name       = "Mks";
		this.visibility = true;
		this.image      = getImage(name, page);

		this.x = x("mks");
		this.y = y("mks");
		this.page = page;
		
		this.width  = image.getWidth();
		this.height = image.getHeight();
		
		this.symbol = symbol(page, "MKS");

		this.symbolX = x - xSymbolMargin;
		this.symbolY = y;

		points = new ArrayList<Point>();
		pointers = new ArrayList<Pointer>();

		points.add(Point.left(page, this, 100f, false, "L1"));
		points.add(Point.left(page, this, 200f, false, "L2"));
		points.add(Point.left(page, this, 300f, false, "L3"));

		points.add(Point.up(page, this, 100f, false, "MAINDC24"));

		points.add(Point.down(page, this, 100f, false, "GROUNDDC"));
		points.add(Point.down(page, this, 200f, false, "GROUNDPE"));

		page.add(this);
	}

	public void control(Page page) {
		new UpDownRightPhases(page, x("3"), this.y, false);
	}

	public void secondary(Page page, float x, float y) {
		new MksContact(page, this, x, y, false);	
	}

	public void secondaryMain(Page page, float x, float y) {
		new MksContact(page, this, x, y, true);
	}
}
