package pl.com.hom.elements;

import java.util.ArrayList;

import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;

import pl.com.hom.configuration.Role;
import pl.com.hom.connections.Point;

public abstract class ColumnRow {
	protected String id;
	protected String desc;
	protected String name;
	protected String techName;

	protected PdfFormXObject image;
	protected boolean        visibility;
	
	protected ArrayList<Point> points;

	protected ArrayList<ColumnRow> childs;

	protected float x;
	protected float y;

	protected float nameXPos;
	protected float nameYPos;

	protected float width;
	protected float height;

	protected Role role;

	public Role getRole() {
		return this.role;
	}

	public boolean visible() {
		return visibility;
	}

	public ArrayList<Point> getPoints() {
		return this.points;
	}

	public void unlinkColumnPoints() {
		for (Point p : points)
			p.unlinkVerticalDirections();
	}

	public void showPoints() {
		for (Point p : points) {
			System.out.println(p.toString());
		}
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public float getWidthPos() {
		return x;
	}

	public float getHeightPos() {
		return y;
	}

	public float getWidthNamePos() {
		return nameXPos;
	}

	public float getHeightNamePos() {
		return nameYPos;
	}

	public PdfFormXObject image() {
		return this.image;
	}

	public String getTechName() {
		return this.techName;
	}
}
