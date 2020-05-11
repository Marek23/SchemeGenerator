package pl.com.hom.page;

import pl.com.hom.configuration.Measures;
import pl.com.hom.data.Board;
import pl.com.hom.element.main.Mks;

public class MksErrors extends Errors {
	private static final long serialVersionUID = 1L;

	public MksErrors(Board parent) {
		super(parent);
	}

	public void add(Mks mks) {
		float x = Measures.ERROR_WIDTH_MARGIN + col * colDist;
		float y = Measures.ERROR_HEIGHT_MARGIN + row * rowDist;

		if (first) {
			mks.secondaryMain(this, x, y);
			first = false;
		}
		else
			mks.secondary(this, x, y);

		next();
	}
}
