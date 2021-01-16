package pl.com.cs.schema;

public class HorizontalLine extends Line{
	public HorizontalLine(Point from, Point to) {
		super(from,to);

		from.connect(Direction.Right);
		to.connect(Direction.Left);
	}
}
