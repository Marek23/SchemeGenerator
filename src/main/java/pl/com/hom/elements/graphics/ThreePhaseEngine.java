package pl.com.hom.elements.graphics;

import java.util.ArrayList;

import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.electric.Potential;
import pl.com.hom.electric.Role;
import pl.com.hom.elements.ColumnRow;

import static pl.com.hom.utils.Resource.getImage;
import static pl.com.hom.utils.Resource.ThreePhaseEngine;

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

	@Override
	protected void countCoordinates() {
		// TODO Auto-generated method stub
		
	}
}
