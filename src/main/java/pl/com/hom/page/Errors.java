package pl.com.hom.page;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Point;
import pl.com.hom.data.Board;

public class Errors extends Page {
	protected enum Direction {Up, Down};
	protected Direction dir = Direction.Down;

	protected static final long serialVersionUID = 1L;

	protected int row = 0;
	protected int col = 0;

	protected int rowMax = 9;

	protected float rowDist = 300f * Measures.SCALE;
	protected float colDist = 500f * Measures.SCALE;

	protected boolean first = true;

	public Errors(Board parent) {
		super(parent);
	}
	
	protected void next() {
		if (dir == Direction.Down) {
			if (row < rowMax ) {
				row++;
			}
			else {
				float y = Measures.ERROR_HEIGHT_MARGIN + (row + 1) * rowDist;

				Point.upRight(this, Measures.ERROR_WIDTH_MARGIN + col * colDist + 100f * Measures.SCALE, y, "DC24");
				Point.upLeft(this, Measures.ERROR_WIDTH_MARGIN + (col + 1) * colDist + 100f * Measures.SCALE, y, "DC24");

				col++;
				dir = Direction.Up;
			}
		}
		else if (dir == Direction.Up) {
			if (row > 0) {
				row--;
			}
			else {
				float y = Measures.ERROR_HEIGHT_MARGIN + (row - 1) * rowDist;

				Point.downRight(this, Measures.ERROR_WIDTH_MARGIN + col * colDist + 100f * Measures.SCALE, y, "DC24");
				Point.downLeft(this, Measures.ERROR_WIDTH_MARGIN + (col + 1) * colDist + 100f * Measures.SCALE, y, "DC24");

				col++;
				dir = Direction.Down;
			}
		}
	}
}
