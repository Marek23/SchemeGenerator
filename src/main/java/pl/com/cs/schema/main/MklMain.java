package pl.com.cs.schema.main;

import java.util.ArrayList;

import pl.com.cs.schema.Drawable;
import pl.com.cs.fps.SapInput;
import pl.com.cs.schema.Point;
import pl.com.cs.schema.pointer.Pointer;
import pl.com.cs.schema.page.Page;

import static pl.com.cs.config.Images.getImage;
import static pl.com.cs.config.Measures.scaled;
import static pl.com.cs.config.Widths.x;
import static pl.com.cs.config.Heights.y;

public class MklMain extends Drawable {
	private float output;
	private float input;

	public MklMain(Page page, ArrayList<SapInput> inputs) {
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

		this.symbolX = x;
		this.symbolY = y + this.height/2;

		page.addSapInputs(inputs);
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
