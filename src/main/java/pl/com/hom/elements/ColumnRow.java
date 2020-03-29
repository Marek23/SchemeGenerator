package pl.com.hom.elements;

import java.util.ArrayList;

import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;

import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.electric.Role;
import pl.com.hom.utils.Measures;

public abstract class ColumnRow {
	protected ArrayList<Point>  points;

	protected String id;
	protected String desc;

	protected PdfFormXObject image;
	protected boolean      visibility;

	protected int   columnIndex;
	protected float x;
	protected float y;
	protected Role role;

	public Role getRole() {
		return this.role;
	}

	public ArrayList<Point> getPoints() {
		return this.points;
	}

	public void unlinkColumnPoints() {
		for (Point p : points)
			p.unlinkColumnDirections();
	}

	public ArrayList<Point> getPointsTargetingDown() {
		ArrayList<Point> out = new ArrayList<Point>(); 
		for (Point p : points)
			if (p.getDirections().containsKey(Direction.Down))
				out.add(p);

		return out;
	}
	public void showPoints() {
		for (Point p : points) {
			System.out.println(p.toString());
		}
	}

	public ArrayList<Point> getPointsTargetingUp() {
		ArrayList<Point> out = new ArrayList<Point>(); 
		for (Point p : points)
			if (p.getDirections().containsKey(Direction.Up))
				out.add(p);

		return out;
	}

	public void setColumnIndex(int index) {
		this.columnIndex = index;
		countCoordinates();
	}

	public float getWidth() {
		return x;
	}

	public float getHeight() {
		return y;
	}
	public void setColumnWidth(int index) {
		this.x = Measures.countColumnWidth(index);
	}

	protected abstract void countCoordinates();
}
