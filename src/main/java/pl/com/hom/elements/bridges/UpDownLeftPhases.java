package pl.com.hom.elements.bridges;

import java.util.ArrayList;
import java.util.EnumMap;

import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.Element;
import pl.com.hom.scheme.Page;

public class UpDownLeftPhases extends Element {
	public UpDownLeftPhases(Page parent, float x, float y) {
		this.visibility = false;
		this.image      = null;

		this.x = x;
		this.y = y;

		points = new ArrayList<Point>();

		EnumMap<Direction, Boolean> dirs = new EnumMap<Direction, Boolean>(Direction.class);
		dirs.put(Direction.Left, false);
		dirs.put(Direction.Up, false);
		dirs.put(Direction.Down, false);

		points.add(Point.upDownLeftBridge(this, "L1________LEFT"));
		points.add(Point.upDownLeftBridge(this, "L2________LEFT"));
		points.add(Point.upDownLeftBridge(this, "L3________LEFT"));

		parent.addElement(this);
	}
}
