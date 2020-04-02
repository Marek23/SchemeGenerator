package pl.com.hom.elements.graphics;

import static pl.com.hom.configuration.Resource.getImage;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.configuration.Roles;
import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.ColumnRow;
import pl.com.hom.scheme.Column;

import static pl.com.hom.configuration.Potentials.getPotential;
public class Contactor extends ColumnRow {
	
	public Contactor (Column parent) {
		this.parent = parent;

		this.visibility = true;
		this.image      = getImage("Contactor");
		this.role       = Roles.getRole("Contactor");
		this.x = parent.getWidthPos();
		this.y = Measures.COL_LEV_HEIGHT * role.getLevel();

		this.width  = image.getWidth();
		this.height = image.getHeight();

		points = new ArrayList<Point>();

		points.add(new Point(this, getPotential("L1"), Direction.Down));
		points.add(new Point(this, getPotential("L2"), Direction.Down));
		points.add(new Point(this, getPotential("L3"), Direction.Down));

		points.add(new Point(this, getPotential("L1"), Direction.Up));
		points.add(new Point(this, getPotential("L2"), Direction.Up));
		points.add(new Point(this, getPotential("L3"), Direction.Up));

		parent.addElement(this);
	}
}
