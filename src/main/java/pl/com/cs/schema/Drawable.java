package pl.com.cs.schema;

import java.util.ArrayList;

import pl.com.cs.schema.pointer.Pointer;
import pl.com.cs.schema.page.Page;
import pl.com.cs.util.Image;

import static pl.com.cs.config.Heights.y;

public abstract class Drawable {
	protected String id;
	protected String desc;
	protected String name;
	protected String symbol;
	protected Page   page;

	protected float  numberX;
	protected float  numberY;
	protected String number;

	protected String steering;

	protected Image   image;
	protected boolean visibility;

	protected Drawable main;
	protected Drawable child;

	protected ArrayList<Point> points;

	protected ArrayList<Pointer> pointers;

	protected float x;
	protected float y;

	protected float symbolX;
	protected float symbolY;

	protected float mainX;
	protected float mainY;
	protected int mainPageNr;

	protected String fuse;
	protected float  fuseX;
	protected float  fuseY;

	protected float width;
	protected float height;

	protected float pointerY;

	public boolean visible() {
		return visibility;
	}

	public ArrayList<Point> points() {
		return this.points;
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

	public float symbolWidthPos() {
		return symbolX;
	}

	public float symbolHeightPos() {
		return symbolY;
	}

	public Image image() {
		return this.image;
	}

	public String symbol() {
		return this.symbol;
	}

	public Page page() {
		return page;
	}

	protected String symbol(Page page, String type) {
		String pageNr = String.valueOf(page.nr());

		return  pageNr + type + String.valueOf(page.fps().nextValueOf(page + type));
	}

	public float pointerHeightPos() {
		float height = y("beginPointer");
		for (Pointer p: pointers)
			height += (p.height() + y("pointerSpace"));

		return height;
	}

	public void add(Pointer p) {
		this.pointers.add(p);
	}

	public float mainWidthPos() {
		return mainX;
	}

	public float mainHeightPos() {
		return mainY;
	}

	public int mainPageNr() {
		return mainPageNr;
	}

	public float numberWidthPos() {
		return numberX;
	}

	public float numberHeightPos() {
		return numberY;
	}

	public float fuseWidthPos() {
		return fuseX;
	}

	public float fuseHeightPos() {
		return fuseY;
	}

	public String number() {
		return number;
	}

	public Drawable main() {
		return main;
	}

	public Drawable child() {
		return child;
	}

	public String steering() {
		return steering;
	}

	public String fuse() {
		return fuse;
	}
}
