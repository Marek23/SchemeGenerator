package pl.com.cs.schema.page;

import pl.com.cs.schema.Point;

import static pl.com.cs.config.Widths.x;

import pl.com.cs.fps.Fps;

import static pl.com.cs.config.Heights.y;
import static pl.com.cs.config.Measures.scaled;

public abstract class ErrorsPage extends Page {
	protected boolean isEmpty;
	protected enum Direction {Up, Down};
	protected Direction dir = Direction.Down;

	protected static final long serialVersionUID = 1L;

	protected int row = 0;
	protected int col = 0;

	protected int numberOfContactsInOneLine = 10;

	protected float rowDist = scaled(300f);
	protected float colDist = scaled(500f);

	protected boolean first = true;

	public ErrorsPage(Fps parent) {
		super(parent);
		isEmpty = true;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	protected void next() {
		if (dir == Direction.Down) {
			if (row < numberOfContactsInOneLine ) {
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
