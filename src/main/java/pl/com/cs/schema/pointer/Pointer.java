package pl.com.cs.schema.pointer;

import pl.com.cs.schema.Drawable;
import pl.com.cs.schema.page.Page;
import pl.com.cs.util.Image;

import static pl.com.cs.config.Images.getImage;

public abstract class Pointer {
	protected Page page;
	protected Drawable main;
	protected Drawable child;
	protected String name;

	protected Image image;

	protected float x;
	protected float y;

	protected float mainX;
	protected float mainY;
	protected Page   mainPage;
	protected Page   childPage;

	protected float width;
	protected float height;

	public Pointer() {};
	public Pointer(Drawable main, Drawable child, String name) {
		this.name  = name;
		this.main  = main;
		this.child = child;
		this.page  = main.page();
		this.image = getImage(name, page);

		this.x = main.widthPos();
		this.y = main.pointerHeightPos();

		this.width  = image.getWidth();
		this.height = image.getHeight();

		this.mainX = x;
		this.mainY = this.y + this.height/2;

		this.mainPage  = main.page();
		this.childPage = child.page();

		page.add(this);
		main.add(this);
	}

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

	public float mainWidthPos() {
		return mainX;
	}

	public float mainHeightPos() {
		return mainY;
	}

	public Image image() {
		return this.image;
	}

	public int mainPage() {
		return this.mainPage.nr();
	}

	public int childPage() {
		return this.childPage.nr();
	}
}
