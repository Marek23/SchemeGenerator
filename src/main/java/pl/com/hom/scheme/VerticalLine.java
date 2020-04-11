package pl.com.hom.scheme;

import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;

public class VerticalLine extends Line{
	public VerticalLine(Point from, Point to) {
		super(from,to);

		from.link(Direction.Down);
		to.link(Direction.Up);
	}
}
