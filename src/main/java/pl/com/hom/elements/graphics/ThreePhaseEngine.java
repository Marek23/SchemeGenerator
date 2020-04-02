package pl.com.hom.elements.graphics;

import java.util.ArrayList;

import pl.com.hom.configuration.Roles;
import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.ColumnRow;

import static pl.com.hom.configuration.Resource.getImage;
import static pl.com.hom.configuration.Potentials.getPotential;
public class ThreePhaseEngine extends ColumnRow {
	
	public ThreePhaseEngine () {
		this.visibility = true;
		image           = getImage("ThreePhaseEngine");

		points = new ArrayList<Point>();

		points.add(new Point(getPotential("L1"), Direction.Up));
		points.add(new Point(getPotential("L2"), Direction.Up));
		points.add(new Point(getPotential("L3"), Direction.Up));

		role = Roles.getRole("Receiver");
	}
}
