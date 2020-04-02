package pl.com.hom.elements.graphics;
import java.util.EnumSet;

import static pl.com.hom.configuration.Resource.getImage;

import java.util.ArrayList;

import pl.com.hom.configuration.Roles;
import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.ColumnRow;

import static pl.com.hom.configuration.Potentials.getPotential;

public class ThreePhaseFuse extends ColumnRow {
	public ThreePhaseFuse () {
		visibility = true; 
		image      = getImage("ThreePhaseFuse");

		points = new ArrayList<Point>();

		EnumSet<Direction> downDirection = EnumSet.noneOf(Direction.class);
		downDirection.add(Direction.Down);

		EnumSet<Direction> upDirection = EnumSet.noneOf(Direction.class);
		upDirection.add(Direction.Up);

		points.add(new Point(getPotential("L1"), Direction.Down));
		points.add(new Point(getPotential("L2"), Direction.Down));
		points.add(new Point(getPotential("L3"), Direction.Down));

		points.add(new Point(getPotential("L1"), Direction.Up));
		points.add(new Point(getPotential("L2"), Direction.Up));
		points.add(new Point(getPotential("L3"), Direction.Up));

		role = Roles.getRole("Fuse");
	}
}
