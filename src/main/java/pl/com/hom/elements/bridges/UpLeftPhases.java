package pl.com.hom.elements.bridges;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.configuration.Roles;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.ColumnRow;
import pl.com.hom.scheme.Column;

public class UpLeftPhases extends ColumnRow {
	public UpLeftPhases(Column parent, String name) {
		this.name       = name;
		this.visibility = false;
		this.image      = null;
		this.role       = Roles.getRole(name);
		this.x = parent.getWidthPos();
		this.y = Measures.COL_LEV_HEIGHT * role.getLevel();

		this.points = new ArrayList<Point>();

		points.add(Point.newUpLeftJetPoint(this, "L1____"));
		points.add(Point.newUpLeftJetPoint(this, "L2____"));
		points.add(Point.newUpLeftJetPoint(this, "L3____"));

		parent.addElement(this);
	}
}
