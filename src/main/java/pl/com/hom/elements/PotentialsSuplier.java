package pl.com.hom.elements;

import java.util.HashSet;

import pl.com.hom.connections.Point;
import pl.com.hom.utils.Role;

public class PotentialsSuplier extends ColumnRow {
	public PotentialsSuplier () {
		this.points = new HashSet<Point>();
		this.role   = Role.UpLines;
	}

	public void fetchPointsToSupply(ColumnRow element) {
		for (Point p : element.getPoints())
			this.points.add(Point.newSupplierPoint(p));
	}
}
