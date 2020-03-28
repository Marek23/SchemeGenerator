package pl.com.hom.elements.graphics;

import java.util.HashSet;

import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.electric.Potential;
import pl.com.hom.elements.ColumnRow;
import pl.com.hom.utils.Role;

import static pl.com.hom.utils.Resource.getImage;
import static pl.com.hom.utils.Resource.UVWBridge;

public class UVWJetBridge extends ColumnRow {
	public UVWJetBridge () {
		visibility = true;
		image      = getImage(UVWBridge);

		points = new HashSet<Point>();

		points.add(new Point(Potential.L1, Direction.Down));
		points.add(new Point(Potential.L2, Direction.Down));
		points.add(new Point(Potential.L3, Direction.Down));

		role = Role.JetBridge;
	}
}
