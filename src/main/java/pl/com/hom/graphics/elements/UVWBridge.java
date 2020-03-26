package pl.com.hom.graphics.elements;

import java.util.HashSet;

import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.electric.Potential;
import pl.com.hom.elements.ElectricElement;
import pl.com.hom.utils.ColLevel;

import static pl.com.hom.utils.Resource.getImage;
import static pl.com.hom.utils.Resource.UVWBridge;

public class UVWBridge extends ElectricElement {
	public UVWBridge () {
		visibility = true;
		image      = getImage(UVWBridge);

		points = new HashSet<Point>();

		points.add(new Point(Potential.L1, Direction.Down));
		points.add(new Point(Potential.L2, Direction.Down));
		points.add(new Point(Potential.L3, Direction.Down));

		columnLevel = ColLevel.Fuse;
	}
}
