package pl.com.hom.page;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Point;
import pl.com.hom.data.Board;
import pl.com.hom.element.main.Mks;

public class Errors extends Page {
	private enum Direction {Up, Down};
	private Direction dir = Direction.Down;

	private static final long serialVersionUID = 1L;

	private int row = 0;
	private int col = 0;

	private int rowMax = 5;
	
	private float rowMin  = 1000f * Measures.SCALE;
	private float colMin  = 2000f * Measures.SCALE;
	private float rowDist = 300f * Measures.SCALE;
	private float colDist = 500f * Measures.SCALE;

	private boolean first = true;

	public Errors(Board parent) {
		super(parent);
	}

	public void addMks(Mks mks) {
		if (mks == null)
			return;

		float x = colMin + col * colDist;
		float y = rowMin + row * rowDist;

		if (first) {
			mks.secondaryMain(this, x, y);
			first = false;
		}
		else
			mks.secondary(this, x, y);

		next();
	}
	
	private void next() {
		if (dir == Direction.Down) {
			if (row < rowMax ) {
				row++;
			}
			else {
				float y = rowMin + (row + 1) * rowDist;

				Point.upRight(this, colMin + col * colDist + 100f * Measures.SCALE, y, "DC24");
				Point.upLeft(this, colMin + (col + 1) * colDist + 100f * Measures.SCALE, y, "DC24");

				col++;
				dir = Direction.Up;
			}
		}
		else if (dir == Direction.Up) {
			if (row > 0) {
				row--;
			}
			else {
				float y = rowMin + (row - 1) * rowDist;

				Point.downRight(this, colMin + col * colDist + 100f * Measures.SCALE, y, "DC24");
				Point.downLeft(this, colMin + (col + 1) * colDist + 100f * Measures.SCALE, y, "DC24");

				col++;
				dir = Direction.Down;
			}
		}
	}
}
