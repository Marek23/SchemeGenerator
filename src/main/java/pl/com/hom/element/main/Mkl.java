package pl.com.hom.element.main;

import java.util.ArrayList;

import pl.com.hom.connections.Point;
import pl.com.hom.connections.Terminal;
import pl.com.hom.element.Element;
import pl.com.hom.element.pointer.Pointer;
import pl.com.hom.page.Page;

import static pl.com.hom.configuration.Widths.x;
import static pl.com.hom.configuration.Heights.y;
import static pl.com.hom.configuration.Resource.getImage;
import static pl.com.hom.configuration.Measures.scaled;

public class Mkl extends Element {
	private static float xSymbolMargin = 20f;
	private float output;

	public Mkl(Page page) {
		this.name       = "Mkl";
		this.visibility = true;
		this.image      = getImage(name, page);

		this.x = x("mkl");
		this.y = y("mkl");
		this.page = page;

		this.output = x("mkl") + scaled(200f);

		this.width  = image.getWidth();
		this.height = image.getHeight();
		
		this.symbol = symbol(page, "MKL");

		this.symbolX = x - xSymbolMargin;
		this.symbolY = y;

		points = new ArrayList<Point>();
		pointers = new ArrayList<Pointer>();

		for (int i = 0; i < 8; i++) {
			Point p = Point.down(page, this, 400f + 200f*i, false, "DC24");
			points.add(p);
			page.add(new Terminal(page, p, "XS"));
		}

		points.add(Point.up(page, this, 100f, false, "MAINDC24"));
		points.add(Point.down(page, this, 100f, false, "GROUNDDC"));

		for (int i = 0; i < 8; i++)
			points.add(Point.up(page, this, 400f + 200f*i, false, "DC24"));

		page.add(this);
	}

	public float nextOutputX() {
		output += scaled(200f);

		return output;
	}
}
