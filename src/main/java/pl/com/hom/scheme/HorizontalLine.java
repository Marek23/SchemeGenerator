package pl.com.hom.scheme;

import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;

public class HorizontalLine extends Line{
	public HorizontalLine(Point from, Point to) {
		super(from,to);

		from.setDirectionLinked(Direction.Right);
		to.setDirectionLinked(Direction.Left);
	}
}
