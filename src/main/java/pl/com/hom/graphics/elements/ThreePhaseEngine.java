package pl.com.hom.graphics.elements;

import java.util.HashSet;

import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.electric.Potential;
import pl.com.hom.elements.ElectricElement;
import pl.com.hom.utils.ColLevel;

import static pl.com.hom.utils.Resource.getImage;
import static pl.com.hom.utils.Resource.ThreePhaseEngine;

public class ThreePhaseEngine extends ElectricElement {
	
	public ThreePhaseEngine () {
		this.visibility = true;
		image           = getImage(ThreePhaseEngine);

		points = new HashSet<Point>();

		points.add(new Point(Potential.L1, Direction.Up));
		points.add(new Point(Potential.L2, Direction.Up));
		points.add(new Point(Potential.L3, Direction.Up));

		columnLevel = ColLevel.Receiver;
	}
}
