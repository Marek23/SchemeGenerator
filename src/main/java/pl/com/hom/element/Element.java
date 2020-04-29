package pl.com.hom.element;

import java.util.ArrayList;

import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Point;
import pl.com.hom.element.pointer.Pointer;
import pl.com.hom.scheme.Page;

import static pl.com.hom.configuration.Sequences.sequence;

public abstract class Element {
	protected String id;
	protected String desc;
	protected String name;
	protected String symbol;
	protected Page   page;

	protected PdfFormXObject image;
	protected boolean        visibility;

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

	public PdfFormXObject image() {
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

		return  pageNr + type + String.valueOf(sequence(page + type));
	}

	public float pointerHeightPos() {
		float height = Measures.POINTERS_BEGIN_HEIGHT;
		for (Pointer p: pointers)
			height += (p.height() + Measures.POINTERS_SPACE);

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
}