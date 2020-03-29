package pl.com.hom.elements;

import java.util.ArrayList;
import java.util.Iterator;

import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.electric.Potential;
import pl.com.hom.electric.Role;
import pl.com.hom.utils.Measures;

public class PotentialsSuplier extends ColumnRow {
	public PotentialsSuplier () {
		this.points = new ArrayList<Point>();
		this.role   = Role.UpLines;
		this.y      = Measures.countColumnRowHeight(this);
	}

	public void fetchPointsToSupply(ColumnRow element) {
		for (Point p : element.getPoints())
			if (!hasPotential(p.getPotential()) && p.hasDirections(Direction.Up))
				this.points.add(Point.newSupplierPoint(p));
	}

	private boolean hasPotential(Potential pot) {
		Iterator<Point> i = points.iterator();
		while (i.hasNext())
			if (i.next().getPotential() == pot)
				return true;

		return false;
	}

	@Override
	protected void countCoordinates() {
		for (Point p : this.points) {
			p.setHeight(
				Measures.countUpHeight(p.getPotential()) +
				this.getHeight()
			);
			p.setWidth(this.columnIndex);
		}
	}
}
