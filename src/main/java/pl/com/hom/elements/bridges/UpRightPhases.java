package pl.com.hom.elements.bridges;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.configuration.Roles;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.ColumnRow;
import pl.com.hom.elements.Terminals;
import pl.com.hom.scheme.Column;

public class UpRightPhases extends ColumnRow {
	public UpRightPhases (Column parent, String roleName) {
		this.name       = roleName;
		this.visibility = false;
		this.image      = null;
		this.role       = Roles.role(name);

		this.x = parent.widthPos();
		this.y = Measures.COL_LEV_HEIGHT * role.level();

		this.points = new ArrayList<Point>();

		points.add(Point.newUpRightPoint(this, "L1________LEFT______"));
		points.add(Point.newUpRightPoint(this, "L2________LEFT______"));
		points.add(Point.newUpRightPoint(this, "L3________LEFT______"));

		int ROLE_REVEIVER_LEVEL = 7;
		if (this.role.level() == ROLE_REVEIVER_LEVEL) {
			this.terminalGroup = "X2";
			new Terminals(parent,this);
		}

		parent.addElement(this);
	}
}
