package pl.com.hom.elements.bridges;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.configuration.Roles;
import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.ColumnRow;
import pl.com.hom.scheme.Column;

import static pl.com.hom.configuration.Potentials.getPotential;
import static pl.com.hom.configuration.Resource.getImage;

public class ToMKSBridge extends ColumnRow {
	public ToMKSBridge (Column parent) {
		this.parent = parent;

		this.visibility = true;
		this.image      = getImage("ToMKSBridge");
		this.role       = Roles.getRole("ToMKSBridge");

		this.x = parent.getWidthPos();
		this.y = Measures.COL_LEV_HEIGHT * this.role.getLevel();

		points = new ArrayList<Point>();

		points.add(new Point(this, getPotential("L1"), Direction.Down));
		points.add(new Point(this, getPotential("L2"), Direction.Down));
		points.add(new Point(this, getPotential("L3"), Direction.Down));
	}
}
