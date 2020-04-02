package pl.com.hom.elements.bridges;

import static pl.com.hom.configuration.Resource.getImage;
import static pl.com.hom.configuration.Potentials.getPotential;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.configuration.Roles;
import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.ColumnRow;
import pl.com.hom.scheme.Column;

public class AboveContactorBridge extends ColumnRow {
	public AboveContactorBridge (Column parent) {
		this.parent = parent;

		this.visibility = true;
		this.image      = getImage("AboveContactorBridge");
		this.role       = Roles.getRole("AboveContactorBridge");

		this.x = parent.getWidthPos();
		this.y = Measures.COL_LEV_HEIGHT * this.role.getLevel();

		this.width  = image.getWidth();
		this.height = image.getHeight();

		points = new ArrayList<Point>();

		points.add(new Point(this, getPotential("L1"), Direction.Down));
		points.add(new Point(this, getPotential("L2"), Direction.Down));
		points.add(new Point(this, getPotential("L3"), Direction.Down));

		parent.addElement(this);
	}
}
