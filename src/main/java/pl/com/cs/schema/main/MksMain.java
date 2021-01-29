package pl.com.cs.schema.main;

import java.util.ArrayList;

import pl.com.cs.schema.Main;
import pl.com.cs.schema.Point;
import pl.com.cs.schema.pointer.Pointer;
import pl.com.cs.schema.bridge.UpDownRightPhases;
import pl.com.cs.schema.child.MksChild;
import pl.com.cs.schema.page.Page;

import static pl.com.cs.config.Widths.x;
import static pl.com.cs.config.Heights.y;
import static pl.com.cs.config.Images.getImage;

public class MksMain extends Main {
	public MksMain(Page page) {
		this.name = "Mks";
		this.visibility = true;
		this.image = getImage(name, page);

		this.x = x("mks");
		this.y = y("mks");
		this.page = page;

		this.width = image.getWidth();
		this.height = image.getHeight();

		this.symbol = symbol("MKS");

		this.symbolX = x;
		this.symbolY = y + this.height/2;

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

	public void createChild(Page page, float x, float y) {
		new MksChild(page, this, x, y, false);
	}

	public void createChildLinkToMainDc24(Page page, float x, float y) {
		new MksChild(page, this, x, y, true);
	}
}
