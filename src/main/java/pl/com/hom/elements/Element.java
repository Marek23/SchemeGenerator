package pl.com.hom.elements;

import java.util.ArrayList;

import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;

import pl.com.hom.connections.Point;
import pl.com.hom.scheme.Page;

import static pl.com.hom.configuration.Symbols.symbols;

public abstract class Element {
	protected String id;
	protected String desc;
	protected String name;
	protected String symbol;

	protected PdfFormXObject image;
	protected boolean        visibility;
	
	protected ArrayList<Point> points;

	protected ArrayList<Element> childs;

	protected float x;
	protected float y;

	protected float symbolX;
	protected float symbolY;

	protected float width;
	protected float height;

	public boolean visible() {
		return visibility;
	}

	public ArrayList<Point> points() {
		return this.points;
	}

	public void unlinkColumnPoints() {
		for (Point p : points)
			p.unlinkVertical();
	}

	public void showPoints() {
		for (Point p : points) {
			System.out.println(p.toString());
		}
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

	protected String symbol(Page parent, String type) {
		String page = String.valueOf(parent.getNr());

		if (symbols.containsKey(page + type))
			symbols.put(page + type, symbols.get(page + type)+1);
		else
			symbols.put(page + type, 1);

		return  page + type + String.valueOf(symbols.get(page + type));
	}

}
