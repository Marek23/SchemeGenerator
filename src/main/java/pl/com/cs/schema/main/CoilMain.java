package pl.com.cs.schema.main;

import java.util.ArrayList;

import pl.com.cs.schema.Main;
import pl.com.cs.schema.child.CoilMotorLaunchChild;
import pl.com.cs.schema.child.CoilSapOutputChild;
import pl.com.cs.schema.child.PlcChild;
import pl.com.cs.schema.page.Page;
import pl.com.cs.schema.Point;
import pl.com.cs.schema.pointer.Pointer;

import static pl.com.cs.config.Heights.y;
import static pl.com.cs.config.Images.getImage;

public class CoilMain extends Main {
	public static CoilMain coilMotorSteeringChild(Page page, PlcChild signal) {
		CoilMain c = new CoilMain(page, signal);
		new CoilMotorLaunchChild(page, c, signal);
		return c;
	}

	public static CoilMain coilSapOutputChild(Page page, PlcChild signal, float contactPos) {
		CoilMain c = new CoilMain(page, signal);
		new CoilSapOutputChild(page, c, contactPos);
		return c;
	}

	private CoilMain (Page page, PlcChild signal) {
		this.name       = "Coil";
		this.visibility = true;
		this.image      = getImage(name, page);

		this.x = signal.coilWidthPos();
		this.y = y("coil");
		this.page = page;

		this.width  = image.getWidth();
		this.height = image.getHeight();
		
		this.symbol = symbol("K");

		this.symbolX = x;
		this.symbolY = y + this.height/2;

		points   = new ArrayList<Point>();
		pointers = new ArrayList<Pointer>();

		points.add(Point.up(page, this, 100f, false, "DC24"));
		points.add(Point.down(page, this, 100f, false, "GROUNDDC"));

		page.add(this);
	}
}
