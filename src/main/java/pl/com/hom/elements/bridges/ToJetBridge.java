package pl.com.hom.elements.bridges;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.configuration.Roles;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.ColumnRow;
import pl.com.hom.scheme.Column;

public class ToJetBridge extends ColumnRow {
	public ToJetBridge (Column parent) {
		this.name       = "ToJetBridge";
		this.visibility = false;
		this.image      = null;
		this.role       = Roles.role(name);

		this.x = parent.widthPos();
		this.y = Measures.COL_LEV_HEIGHT * this.role.level();

		points = new ArrayList<Point>();

		points.add(Point.newToJetBridge(this, "L1________LEFT"));
		points.add(Point.newToJetBridge(this, "L2________LEFT"));
		points.add(Point.newToJetBridge(this, "L3________LEFT"));

		parent.addElement(this);
	}
}
