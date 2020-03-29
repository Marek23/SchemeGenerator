package pl.com.hom.elements.graphics;
import java.util.EnumSet;
import java.util.ArrayList;

import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.electric.Potential;
import pl.com.hom.electric.Role;
import pl.com.hom.elements.ColumnRow;

import static pl.com.hom.utils.Resource.getImage;
import static pl.com.hom.utils.Resource.ThreePhaseFuse;

public class ThreePhaseFuse extends ColumnRow {
	public ThreePhaseFuse () {
		visibility = true; 
		image      = getImage(ThreePhaseFuse);

		points = new ArrayList<Point>();

		EnumSet<Direction> downDirection = EnumSet.noneOf(Direction.class);
		downDirection.add(Direction.Down);

		EnumSet<Direction> upDirection = EnumSet.noneOf(Direction.class);
		upDirection.add(Direction.Up);

		points.add(new Point(Potential.L1, Direction.Down));
		points.add(new Point(Potential.L2, Direction.Down));
		points.add(new Point(Potential.L3, Direction.Down));

		points.add(new Point(Potential.L1, Direction.Up));
		points.add(new Point(Potential.L2, Direction.Up));
		points.add(new Point(Potential.L3, Direction.Up));

		role = Role.Fuse;
	}

	@Override
	protected void countCoordinates() {
		// TODO Auto-generated method stub
		
	}
}
