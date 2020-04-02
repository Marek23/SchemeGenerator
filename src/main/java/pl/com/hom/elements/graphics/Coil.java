package pl.com.hom.elements.graphics;

import static pl.com.hom.configuration.Resource.getImage;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.configuration.Roles;
import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.connections.Potential;
import pl.com.hom.elements.ColumnRow;
import pl.com.hom.scheme.Column;

import static pl.com.hom.configuration.Potentials.getPotential;
public class Coil extends ColumnRow {
	
	public Coil (Column parent, Potential potential) {
		this.parent = parent;

		visibility = true;
		image      = getImage("Coil");
		role       = Roles.getRole("Coil");

		this.x = parent.getWidth();
		this.y = Measures.COL_LEV_HEIGHT * role.getLevel();

		points  = new ArrayList<Point>();

		points.add(new Point(this, getPotential("N"), Direction.Down));
	}
}
