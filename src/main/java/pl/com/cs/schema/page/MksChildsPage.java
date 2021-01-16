package pl.com.cs.schema.page;


import pl.com.cs.fps.Fps;
import pl.com.cs.schema.main.MksMain;

import static pl.com.cs.config.Heights.y;
import static pl.com.cs.config.Widths.x;

public class MksChildsPage extends ErrorsPage {
	private static final long serialVersionUID = 1L;

	public MksChildsPage(Fps fps) {
		super(fps);
	}

	public void addMksChild(MksMain mks) {
		float x = x("errorsStart") + col * colDist;
		float y = y("errorsStart") + row * rowDist;

		if (first) {
			mks.createChildLinkToMainDc24(this, x, y);
			first = false;
		}
		else
			mks.createChild(this, x, y);

		next();
	}
}
