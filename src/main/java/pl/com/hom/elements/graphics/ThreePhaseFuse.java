package pl.com.hom.elements.graphics;
import java.util.HashSet;

import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.electric.Potential;
import pl.com.hom.elements.ColumnRow;
import pl.com.hom.utils.Role;

import static pl.com.hom.utils.Resource.getImage;
import static pl.com.hom.utils.Resource.ThreePhaseFuse;

public class ThreePhaseFuse extends ColumnRow {
	public ThreePhaseFuse () {
		visibility = true; 
		image      = getImage(ThreePhaseFuse);

		points = new HashSet<Point>();

		HashSet<Direction> downDirection = new HashSet<Direction>();
		downDirection.add(Direction.Down);

		HashSet<Direction> upDirection = new HashSet<Direction>();
		upDirection.add(Direction.Up);

		points.add(new Point(Potential.L1, Direction.Down));
		points.add(new Point(Potential.L2, Direction.Down));
		points.add(new Point(Potential.L3, Direction.Down));

		points.add(new Point(Potential.L1, Direction.Up));
		points.add(new Point(Potential.L2, Direction.Up));
		points.add(new Point(Potential.L3, Direction.Up));

		role = Role.Fuse;
	}

}
