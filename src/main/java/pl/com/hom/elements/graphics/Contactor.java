package pl.com.hom.elements.graphics;

import static pl.com.hom.configuration.Resource.Contactor;
import static pl.com.hom.configuration.Resource.getImage;

import java.util.ArrayList;

import pl.com.hom.configuration.Level;
import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.electric.Role;
import pl.com.hom.elements.ColumnRow;
import pl.com.hom.scheme.Column;

import static pl.com.hom.configuration.Potentials.getPotential;
public class Contactor extends ColumnRow {
	
	public Contactor (Column parent) {
		this.parent = parent;

		visibility = true;
		image      = getImage(Contactor);
		role       = Role.Launcher;

		this.x = parent.getWidthPos();
		this.y = Measures.COL_LEV_HEIGHT * Level.getRoleLevel(this.role);

		points = new ArrayList<Point>();

		points.add(new Point(this, getPotential("L1"), Direction.Down));
		points.add(new Point(this, getPotential("L2"), Direction.Down));
		points.add(new Point(this, getPotential("L3"), Direction.Down));

		points.add(new Point(this, getPotential("L1"), Direction.Up));
		points.add(new Point(this, getPotential("L2"), Direction.Up));
		points.add(new Point(this, getPotential("L3"), Direction.Up));
	}
}
