package pl.com.hom.elements.graphics;

import static pl.com.hom.configuration.Resource.ThreePhaseEngine;
import static pl.com.hom.configuration.Resource.getImage;

import java.util.ArrayList;

import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.electric.Potential;
import pl.com.hom.electric.Role;
import pl.com.hom.elements.ColumnRow;

public class ThreePhaseEngine extends ColumnRow {
	
	public ThreePhaseEngine () {
		this.visibility = true;
		image           = getImage(ThreePhaseEngine);

		points = new ArrayList<Point>();

		points.add(new Point(Potential.L1, Direction.Up));
		points.add(new Point(Potential.L2, Direction.Up));
		points.add(new Point(Potential.L3, Direction.Up));

		role = Role.Receiver;
	}
}
