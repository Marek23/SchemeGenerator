package pl.com.hom.element.main;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Point;
import pl.com.hom.element.Element;
import pl.com.hom.element.pointer.Pointer;
import pl.com.hom.elements.bridges.UpDownRightPhases;
import pl.com.hom.scheme.Page;

import static pl.com.hom.configuration.Resource.getImage;

public class Mks extends Element {
	public Mks(Page page, float x, float y) {
		this.name       = "Mks";
		this.visibility = true;
		this.image      = getImage(name, page);

		this.x = x;
		this.y = y;
		
		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;
		
		this.symbol = symbol(page, "MKS");

		this.symbolX = x - 20f;
		this.symbolY = 595.0f - y;

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

	public void jetControl(Page page) {
		float x = Measures.SEC_JET_COL;
		new UpDownRightPhases(page, x, this.y);
	}
}
