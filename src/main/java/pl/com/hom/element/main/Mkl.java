package pl.com.hom.element.main;

import java.util.ArrayList;

import pl.com.hom.connections.Point;
import pl.com.hom.data.SapIn;
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
	private float input;

	public Mkl(Page page, ArrayList<SapIn> inputs) {
		this.name       = "Mkl";
		this.visibility = true;
		this.image      = getImage(name, page);

		this.x = x("mkl");
		this.y = y("mkl");
		this.page = page;

		this.output = x("mkl") + scaled(200f);
		this.input  = x("mkl");

		this.width  = image.getWidth();
		this.height = image.getHeight();
		
		this.symbol = symbol(page, "MKL");

		this.symbolX = x - xSymbolMargin;
		this.symbolY = y;

		page.addSapIn(inputs);
		points = new ArrayList<Point>();
		pointers = new ArrayList<Pointer>();

		for (int i = 0; i < 8; i++)
			points.add(Point.down(page, this, 400f + 200f*i, false, "DC24"));

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

	public float nextInputX() {
		input += scaled(400f);

		return input;
	}
}
