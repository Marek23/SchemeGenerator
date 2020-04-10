package pl.com.hom.elements.bridges;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.configuration.Roles;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.ColumnRow;
import pl.com.hom.scheme.Column;

public class ToMksBridge extends ColumnRow {
	public ToMksBridge (Column parent) {
		this.name       = "ToMksBridge";
		this.visibility = false;
		this.image      = null;
		this.role       = Roles.role(name);

		this.x = parent.widthPos();
		this.y = Measures.COL_LEV_HEIGHT * this.role.level();

		points = new ArrayList<Point>();

		points.add(Point.newToMksBridge(this, "L1____"));
		points.add(Point.newToMksBridge(this, "L2____"));
		points.add(Point.newToMksBridge(this, "L3____"));

		parent.addElement(this);
	}
}
