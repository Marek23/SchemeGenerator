package pl.com.hom.elements.bridges;

import java.util.EnumMap;
import java.util.ArrayList;

import pl.com.hom.configuration.Roles;
import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.ColumnRow;

import static pl.com.hom.configuration.Potentials.getPotential;

public class UpRightPhasesBridge extends ColumnRow {
	public UpRightPhasesBridge () {
		visibility = false;
		image      = null;

		this.points = new ArrayList<Point>();

		EnumMap<Direction, Boolean> directions = new EnumMap<Direction, Boolean>(Direction.class);
		directions.put(Direction.Up, false);
		directions.put(Direction.Right, false);

		points.add(new Point(getPotential("L1"), directions));
		points.add(new Point(getPotential("L2"), directions));
		points.add(new Point(getPotential("L3"), directions));

		role = Roles.getRole("UnderContactorBridge");
	}
}
