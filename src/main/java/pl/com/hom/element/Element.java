package pl.com.hom.element;

import java.util.ArrayList;

import pl.com.hom.connections.Point;
import pl.com.hom.element.pointer.Pointer;
import pl.com.hom.page.Page;
import pl.com.hom.util.Image;

import static pl.com.hom.configuration.Sequences.sequence;
import static pl.com.hom.configuration.Heights.y;
public abstract class Element {
	protected String id;
	protected String desc;
	protected String name;
	protected String symbol;
	protected Page   page;

	protected String steering;

	protected Image   image;
	protected boolean visibility;

	protected Element parent;

	protected ArrayList<Point> points;

	protected ArrayList<Pointer> pointers;

	protected float x;
	protected float y;

	protected float symbolX;
	protected float symbolY;

	protected float parentX;
	protected float parentY;
	protected int parentPageNr;

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
		String board  = page.board().name();

		return  pageNr + type + String.valueOf(sequence(board + page + type));
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

	public float parentWidthPos() {
		return parentX;
	}

	public float parentHeightPos() {
		return parentY;
	}

	public int parentPageNr() {
		return parentPageNr;
	}

	public Element parent() {
		return parent;
	}

	public String steering() {
		return steering;
	}
}
