package pl.com.hom.elements;

import java.util.HashSet;

import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.electric.Potential;
import pl.com.hom.utils.ColLevel;

import static pl.com.hom.utils.Resource.getImage;

public class UpRightPhasesBridge extends ElectricElement {
	public UpRightPhasesBridge () {
		visibility = false;
		image      = null;

		this.points = new HashSet<Point>();

		HashSet<Direction> directions = new HashSet<Direction>();
		directions.add(Direction.Up);
		directions.add(Direction.Right);

		points.add(new Point(Potential.L1, directions));
		points.add(new Point(Potential.L2, directions));
		points.add(new Point(Potential.L3, directions));

		columnLevel = ColLevel.Bridges;
	}
}
