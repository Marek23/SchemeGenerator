package pl.com.hom.page;

import pl.com.hom.board.Board;
import pl.com.hom.connections.Point;

import static pl.com.hom.configuration.Widths.x;
import static pl.com.hom.configuration.Heights.y;
import static pl.com.hom.configuration.Measures.scaled;

public class Errors extends Page {
	protected enum Direction {Up, Down};
	protected Direction dir = Direction.Down;

	protected static final long serialVersionUID = 1L;

	protected int row = 0;
	protected int col = 0;

	protected int rowMax = 3;

	protected float rowDist = scaled(300f);
	protected float colDist = scaled(500f);

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
				float y = y("errorsStart") + (row + 1) * rowDist;

				Point.upRight(this, scaled(100f) + x("errorsStart") + col * colDist, y, false, "DC24");
				Point.upLeft(this, scaled(100f) + x("errorsStart") + (col + 1) * colDist, y, false, "DC24");

				col++;
				dir = Direction.Up;
			}
		}
		else if (dir == Direction.Up) {
			if (row > 0) {
				row--;
			}
			else {
				Point.downRight(this, scaled(100f) + x("errorsStart") + col * colDist, y("errorsStart"), false, "DC24");
				Point.downLeft(this, scaled(100f) + x("errorsStart") +  (col + 1) * colDist, y("errorsStart"), false, "DC24");

				col++;
				dir = Direction.Down;
			}
		}
	}
}
