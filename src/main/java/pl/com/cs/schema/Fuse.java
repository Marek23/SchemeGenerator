package pl.com.cs.schema;

import pl.com.cs.schema.child.FuseChild;
import pl.com.cs.schema.page.Page;

public abstract class Fuse extends Drawable {
	protected String fuse;
	protected float  fuseX;
	protected float  fuseY;

	public float fuseWidthPos() {
		return fuseX;
	}

	public float fuseHeightPos() {
		return fuseY;
	}

	public String fuse() {
		return fuse;
	}

	public void createChild(Page page, float x, float y) {
		new FuseChild(page, this, x, y, false);
	}

	public void createChildLinkToMainDc24(Page page, float x, float y) {
		new FuseChild(page, this, x, y, true);
	}
}
