package pl.com.cs.schema;

public class VerticalLine extends Line{
	public VerticalLine(Point from, Point to) {
		super(from,to);

		from.connect(Direction.Down);
		to.connect(Direction.Up);
	}
}
