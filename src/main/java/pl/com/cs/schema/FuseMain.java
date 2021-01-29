package pl.com.cs.schema;

import java.util.ArrayList;

import pl.com.cs.schema.child.FuseChild;
import pl.com.cs.schema.page.Page;
import pl.com.cs.schema.pointer.FusePointer;

import static pl.com.cs.config.Widths.x;

public abstract class FuseMain extends Main {
	protected String fuse;
	protected float  fuseX;
	protected float  fuseY;

	protected ArrayList<FusePointer>  fusePointers;

	public float fuseWidthPos() {
		return fuseX;
	}

	public float fuseHeightPos() {
		return fuseY;
	}

	public float fusePointerWidthPos() {
		float width = main.widthPos() + main.width();

		for (var p: this.fusePointers)
			width += (p.width() + x("pointerSpace"));

		return width;
	}

	public String fuse() {
		return fuse;
	}

	public void createFuseChild(Page page, float x, float y) {
		new FuseChild(page, this, x, y, false);
	}

	public void createFuseChildLinkToMainDc24(Page page, float x, float y) {
		new FuseChild(page, this, x, y, true);
	}

	public void addFusePointer(FusePointer p) {
		this.fusePointers.add(p);
	}
}
