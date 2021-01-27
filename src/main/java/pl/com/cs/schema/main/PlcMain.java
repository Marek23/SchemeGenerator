package pl.com.cs.schema.main;

import java.util.ArrayList;

import pl.com.cs.schema.Drawable;
import pl.com.cs.schema.Point;
import pl.com.cs.schema.child.PlcChild;
import pl.com.cs.schema.out.Terminal;
import pl.com.cs.schema.page.Page;

import static pl.com.cs.config.Widths.x;
import static pl.com.cs.config.Heights.y;
import static pl.com.cs.config.Images.getImage;

public class PlcMain extends Drawable {
	private ArrayList<PlcChild>  inputs;
	private ArrayList<PlcChild> outputs;

	private int MAX_INPUTS = 8;
	private int MAX_OUTPUTS;

	public PlcMain(String type) {
		this.name = "Plc" + type;
		this.visibility = true;

		this.x = x("plcBegin");
		this.y = y("plc");

		if (type == "Cpu")
			MAX_OUTPUTS = 4;
		else
			MAX_OUTPUTS = 8;

		this.outputs = new ArrayList<PlcChild>();
		this.inputs  = new ArrayList<PlcChild>();
	}

	public int nextOutputNumber() {
		if (this.outputs.size() < MAX_OUTPUTS)
			return this.outputs.size() + 1;
		else
			return -1;
	}

	public int nextInputNumber() {
		if (this.inputs.size() < MAX_INPUTS)
			return this.inputs.size() + 1;
		else
			return -1;
	}

	public void add(PlcChild child, String type){
		if (type.equals("Y")) {
			this.outputs.add(child);
			child.number("Y" + String.valueOf(outputs.size() - 1));
		}
		if (type.equals("X")) {
			this.inputs.add(child);
			child.number("X" + String.valueOf(inputs.size() - 1));
		}
	}

	public void setPage(Page page) {
		this.image  = getImage(name, page);
		this.width  = image.getWidth();
		this.height = image.getHeight();
		this.symbol = symbol(page, "S");
		this.symbolX = x;
		this.symbolY = y + this.height/2;

		points = new ArrayList<Point>();
		points.add(Point.up(page, this, 100f, false, "MAINDCPLC"));
		points.add(Point.down(page, this, 100f, false, "GROUNDDC"));
		points.add(Point.down(page, this, 400f, false, "GROUNDPE"));

		if (this.name == "PlcCpu") {
			Point a = Point.down(page, this, 500f, false, "A+");
			Point b = Point.down(page, this, 600f, false, "B-");

			points.add(a);
			points.add(b);

			new Terminal(page, a, "XT");
			new Terminal(page, b, "XT");
		}

		this.page = page;
		page.add(this);
	}

	public void updateChildsByMainNr() {
		var iter = 0;
		for (var o: this.outputs) {
			o.updateMainNr();
			this.page.addPlcY("Y" + (iter++) + " " + o.signal());
		}

		iter = 0;
		for (var i: this.inputs) {
			i.updateMainNr();
			this.page.addPlcX("X" + (iter++) + " " + i.signal());
		}
	}
}
