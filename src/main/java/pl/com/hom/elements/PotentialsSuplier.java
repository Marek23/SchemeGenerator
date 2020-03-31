package pl.com.hom.elements;

import java.util.ArrayList;
import java.util.Iterator;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.electric.Potential;
import pl.com.hom.electric.Role;
import pl.com.hom.scheme.Column;

public class PotentialsSuplier extends ColumnRow {
	private Column parent;

	public PotentialsSuplier (Column parent) {
		this.parent = parent;
		this.points = new ArrayList<Point>();
		this.role   = Role.UpLines;

		this.x = parent.getWidth(); 
		this.y = Measures.PAGE_HEIGHT;
	}

	public void fetchPointsToSupply(ColumnRow element) {
		for (Point p : element.getPoints())
			if (!hasPotential(p.getPotential()) && p.hasDirections(Direction.Up))
				this.points.add(Point.newSupplierPoint(this, p));
	}

	private boolean hasPotential(Potential pot) {
		Iterator<Point> i = points.iterator();
		while (i.hasNext())
			if (i.next().getPotential() == pot)
				return true;

		return false;
	}
}
