package pl.com.cs.schema.page;

import pl.com.cs.fps.Fps;
import pl.com.cs.schema.FuseMain;

import static pl.com.cs.config.Heights.y;
import static pl.com.cs.config.Widths.x;

public class FuseChildsPage extends ErrorsPage {
	private static final long serialVersionUID = 1L;
	private boolean isEmpty;

	public FuseChildsPage(Fps fps) {
		super(fps);
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public void addFuseChild(FuseMain f) {
		this.isEmpty = false;

		float x = x("errorsStart") + col * colDist;
		float y = y("errorsStart") + row * rowDist;

		if (first) {
			f.createFuseChildLinkToMainDc24(this, x, y);
			first = false;
		}
		else
			f.createFuseChild(this, x, y);

		next();
	}
}
