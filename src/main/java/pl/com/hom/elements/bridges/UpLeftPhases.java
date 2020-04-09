package pl.com.hom.elements.bridges;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.configuration.Roles;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.ColumnRow;
import pl.com.hom.elements.Terminals;
import pl.com.hom.scheme.Column;

public class UpLeftPhases extends ColumnRow {
	public UpLeftPhases(Column parent, String roleName) {
		this.name       = roleName;
		this.visibility = false;
		this.image      = null;
		this.role       = Roles.getRole(name);

		this.x = parent.getWidthPos();
		this.y = Measures.COL_LEV_HEIGHT * role.getLevel();

		this.points = new ArrayList<Point>();

		points.add(Point.newUpLeftJetPoint(this, "L1____"));
		points.add(Point.newUpLeftJetPoint(this, "L2____"));
		points.add(Point.newUpLeftJetPoint(this, "L3____"));

		int ROLE_REVEIVER_LEVEL = 7;
		if (this.role.getLevel() == ROLE_REVEIVER_LEVEL)
			new Terminals(parent,this);
			
		parent.addElement(this);
	}
}
