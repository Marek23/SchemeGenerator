package pl.com.hom.page;

import pl.com.hom.board.Board;
import pl.com.hom.element.main.Mks;

import static pl.com.hom.configuration.Widths.x;
import static pl.com.hom.configuration.Heights.y;

public class MksErrors extends Errors {
	private static final long serialVersionUID = 1L;

	public MksErrors(Board parent) {
		super(parent);
	}

	public void add(Mks mks) {
		float x = x("errorsStart") + col * colDist;
		float y = y("errorsStart") + row * rowDist;

		if (first) {
			mks.secondaryMain(this, x, y);
			first = false;
		}
		else
			mks.secondary(this, x, y);

		next();
	}
}
