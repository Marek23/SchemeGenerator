package pl.com.hom.elements;

import java.util.HashMap;
import java.util.HashSet;

import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.electric.Potential;
import pl.com.hom.utils.Role;

public class UpRightPhasesBridge extends ColumnRow {
	public UpRightPhasesBridge () {
		visibility = false;
		image      = null;

		this.points = new HashSet<Point>();

		HashMap<Direction, Boolean> directions = new HashMap<Direction, Boolean>();
		directions.put(Direction.Up, false);
		directions.put(Direction.Right, false);

		points.add(new Point(Potential.L1, directions));
		points.add(new Point(Potential.L2, directions));
		points.add(new Point(Potential.L3, directions));

		role = Role.Connection;
	}
}
