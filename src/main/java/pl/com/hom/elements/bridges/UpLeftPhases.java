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
		this.role       = Roles.role(name);

		this.x = parent.widthPos();
		this.y = Measures.COL_LEV_HEIGHT * role.level();

		this.points = new ArrayList<Point>();

		points.add(Point.newUpLeftJetPoint(this, "L1____"));
		points.add(Point.newUpLeftJetPoint(this, "L2____"));
		points.add(Point.newUpLeftJetPoint(this, "L3____"));

		int ROLE_REVEIVER_LEVEL = 7;
		if (this.role.level() == ROLE_REVEIVER_LEVEL) {
			this.terminalGroup = "X2";
			new Terminals(parent,this);
		}
			
		parent.addElement(this);
	}
}
