package pl.com.hom.element.pointer;

import pl.com.hom.element.Element;
import pl.com.hom.page.Page;
import pl.com.hom.util.Image;

public abstract class Pointer {
	protected Page page;
	protected Element parent;
	protected String name;

	protected Image image;

	protected float x;
	protected float y;

	protected float parentX;
	protected float parentY;
	protected int   parentPage;

	protected float width;
	protected float height;

	public float width() {
		return width;
	}

	public float height() {
		return height;
	}

	public float widthPos() {
		return x;
	}

	public float heightPos() {
		return y;
	}

	public float parentWidthPos() {
		return parentX;
	}

	public float parentHeightPos() {
		return parentY;
	}

	public Image image() {
		return this.image;
	}

	public int parentPage() {
		return this.parentPage;
	}
}
