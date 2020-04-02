package pl.com.hom.elements;

import java.util.ArrayList;

import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;

import pl.com.hom.configuration.Measures;
import pl.com.hom.configuration.Role;
import pl.com.hom.connections.Point;
import pl.com.hom.scheme.Column;

public abstract class ColumnRow {
	protected Column parent;
	protected String id;
	protected String desc;

	protected PdfFormXObject image;
	protected boolean        visibility;
	
	protected ArrayList<Point> points;

	protected float x;
	protected float y;

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
			p.unlinkColumnDirections();
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
		return height * Measures.SCALE;
	}

	public float getWidthPos() {
		return x;
	}

	public float getHeightPos() {
		return y;
	}

	public PdfFormXObject image() {
		return this.image;
	}
}
