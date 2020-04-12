package pl.com.hom.elements;

import java.util.ArrayList;

import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;

import pl.com.hom.connections.Point;
import pl.com.hom.connections.Terminal;

public abstract class Element {
	protected String id;
	protected String desc;
	protected String name;
	protected String techName;

	protected PdfFormXObject image;
	protected boolean        visibility;
	
	protected ArrayList<Point> points;

	protected String terminalGroup;

	protected ArrayList<Terminal> terminals;
	protected ArrayList<Element> childs;

	protected float x;
	protected float y;

	protected float nameXPos;
	protected float nameYPos;

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

	public float widthNamePos() {
		return nameXPos;
	}

	public float heightNamePos() {
		return nameYPos;
	}

	public PdfFormXObject image() {
		return this.image;
	}

	public String techName() {
		return this.techName;
	}

	public ArrayList<Terminal> terminals() {
		return this.terminals;
	}

	public String terminalGroup() {
		return terminalGroup;
	}
}
