package pl.com.hom.elements.graphics;

import static pl.com.hom.configuration.Resource.getImage;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.configuration.Roles;
import pl.com.hom.connections.Point;
import pl.com.hom.connections.Potential;
import pl.com.hom.elements.ColumnRow;
import pl.com.hom.scheme.Column;

public class Coil extends ColumnRow {
	
	public Coil (Column parent, Potential potential) {
		this.parent = parent;

		visibility = true;
		image      = getImage("Coil");
		role       = Roles.getRole("Coil");

		this.x = parent.getWidthPos();
		this.y = Measures.COL_LEV_HEIGHT * role.getLevel();

		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;

		points  = new ArrayList<Point>();

//		points.add(new Point(this, getPotential("N_____"), Direction.Down));
	}
}
