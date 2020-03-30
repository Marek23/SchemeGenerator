package pl.com.hom.elements.graphics;

import java.util.ArrayList;

import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.electric.Potential;
import pl.com.hom.electric.Role;
import pl.com.hom.elements.ColumnRow;
import pl.com.hom.utils.Measures;

import static pl.com.hom.utils.Resource.Contactor;
import static pl.com.hom.utils.Resource.getImage;

public class Contactor extends ColumnRow {
	
	public Contactor () {
		visibility = true;
		image      = getImage(Contactor);
		role       = Role.Launcher;

		points = new ArrayList<Point>();

		points.add(new Point(Potential.L1, Direction.Down));
		points.add(new Point(Potential.L2, Direction.Down));
		points.add(new Point(Potential.L3, Direction.Down));

		points.add(new Point(Potential.L1, Direction.Up));
		points.add(new Point(Potential.L2, Direction.Up));
		points.add(new Point(Potential.L3, Direction.Up));
	}

	@Override
	protected void countCoordinates() {
		x = Measures.countColumnWidth(0);
		y = Measures.countColumnRowHeight(this);

		ArrayList<Point> u = getPointsTargetingUp();
		ArrayList<Point> d = getPointsTargetingDown();

		for (Point p : u) {
			p.setHeight(this.getHeight() + image.getHeight());
			p.setWidth(this.columnIndex);
		}

		for (Point p : d) {
			p.setHeight(this.getHeight());
			p.setWidth(this.columnIndex);
		}
	}
}
