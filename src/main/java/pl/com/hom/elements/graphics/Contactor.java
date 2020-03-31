package pl.com.hom.elements.graphics;

import static pl.com.hom.configuration.Resource.Contactor;
import static pl.com.hom.configuration.Resource.getImage;

import java.util.ArrayList;

import pl.com.hom.configuration.Level;
import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.electric.Potential;
import pl.com.hom.electric.Role;
import pl.com.hom.elements.ColumnRow;
import pl.com.hom.scheme.Column;

public class Contactor extends ColumnRow {
	
	public Contactor (Column parent) {
		this.parent = parent;

		visibility = true;
		image      = getImage(Contactor);
		role       = Role.Launcher;

		this.x = parent.getWidth();
		this.y = parent.getHeight() -
			(Measures.COL_LEV_HEIGHT * Level.getRoleLevel(this.role))
			- this.image.getHeight()*Measures.SCALE;

		points = new ArrayList<Point>();

		points.add(new Point(this, Potential.L1, Direction.Down));
		points.add(new Point(this, Potential.L2, Direction.Down));
		points.add(new Point(this, Potential.L3, Direction.Down));

		points.add(new Point(this, Potential.L1, Direction.Up));
		points.add(new Point(this, Potential.L2, Direction.Up));
		points.add(new Point(this, Potential.L3, Direction.Up));
	}
}
