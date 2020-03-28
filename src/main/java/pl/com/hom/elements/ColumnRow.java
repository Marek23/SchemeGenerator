package pl.com.hom.elements;

import java.util.HashSet;

import com.itextpdf.kernel.pdf.canvas.wmf.WmfImageData;

import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.utils.Role;

public abstract class ColumnRow {
	protected HashSet<Point>  points;

	protected String id;
	protected String desc;

	protected WmfImageData image;
	protected boolean      visibility;

	protected Role role;

	public Role getRole() {
		return this.role;
	}

	public HashSet<Point> getPoints() {
		return this.points;
	}

	public void unlinkColumnPoints() {
		for (Point p : points)
			p.unlinkColumnDirections();
	}

	public HashSet<Point> getPointsTargetingDown() {
		HashSet<Point> out = new HashSet<Point>(); 
		for (Point p : points)
			if (p.getDirections().containsKey(Direction.Down))
				out.add(p);

		return out;
	}

	public HashSet<Point> getPointsTargetingUp() {
		HashSet<Point> out = new HashSet<Point>(); 
		for (Point p : points)
			if (p.getDirections().containsKey(Direction.Up))
				out.add(p);

		return out;
	}
}
