package pl.com.hom.elements.graphics;

import static pl.com.hom.configuration.Resource.UVWBridge;
import static pl.com.hom.configuration.Resource.getImage;

import java.util.ArrayList;

import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.electric.Potential;
import pl.com.hom.electric.Role;
import pl.com.hom.elements.ColumnRow;

public class UVWJetBridge extends ColumnRow {
	public UVWJetBridge () {
		visibility = true;
		image      = getImage(UVWBridge);

		points = new ArrayList<Point>();

		points.add(new Point(Potential.L1, Direction.Down));
		points.add(new Point(Potential.L2, Direction.Down));
		points.add(new Point(Potential.L3, Direction.Down));

		role = Role.JetBridge;
	}
}
