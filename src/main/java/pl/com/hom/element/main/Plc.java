package pl.com.hom.element.main;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Point;
import pl.com.hom.connections.Terminal;
import pl.com.hom.element.Element;
import pl.com.hom.element.secondary.PlcSignal;
import pl.com.hom.page.Page;

import static pl.com.hom.configuration.Resource.getImage;

public class Plc extends Element {
	private ArrayList<PlcSignal>  inputs;
	private ArrayList<PlcSignal> outputs;

	public Plc(Page page, String type, float x, float y) {
		this.name = "Plc" + type;
		this.visibility = true;
		this.image      = getImage(name, page);

		this.x = x;
		this.y = y;
		this.page = page;
		
		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;
		
		this.symbol = symbol(page, "S");

		this.symbolX = x + 20f;
		this.symbolY = y - 10f;

		points = new ArrayList<Point>();

		points.add(Point.up(page, this, 100f, false, "MAINDCPLC"));
		points.add(Point.down(page, this, 100f, false, "GROUNDDC"));
		points.add(Point.down(page, this, 400f, false, "GROUNDPE"));

		if (type == "Cpu") {
			Point a = Point.down(page, this, 500f, false, "A+");
			Point b = Point.down(page, this, 600f, false, "B-");

			points.add(a);
			points.add(b);

			page.add(new Terminal(page, a, "XT"));
			page.add(new Terminal(page, b, "XT"));
		}

		page.add(this);
	}
}
