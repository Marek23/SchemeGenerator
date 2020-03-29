package pl.com.hom.elements;

import java.util.EnumMap;
import java.util.ArrayList;

import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.electric.Potential;
import pl.com.hom.electric.Role;

public class UpRightPhasesBridge extends ColumnRow {
	public UpRightPhasesBridge () {
		visibility = false;
		image      = null;

		this.points = new ArrayList<Point>();

		EnumMap<Direction, Boolean> directions = new EnumMap<Direction, Boolean>(Direction.class);
		directions.put(Direction.Up, false);
		directions.put(Direction.Right, false);

		points.add(new Point(Potential.L1, directions));
		points.add(new Point(Potential.L2, directions));
		points.add(new Point(Potential.L3, directions));

		role = Role.Connection;
	}

	@Override
	protected void countCoordinates() {
		// TODO Auto-generated method stub
		
	}
}
