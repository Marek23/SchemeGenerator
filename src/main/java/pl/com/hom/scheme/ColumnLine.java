package pl.com.hom.scheme;

import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;

public class ColumnLine extends Line{
	public ColumnLine(Point from, Point to) {
		super(from,to);

		from.setDirectionLinked(Direction.Down);
		to.setDirectionLinked(Direction.Up);
	}
}
