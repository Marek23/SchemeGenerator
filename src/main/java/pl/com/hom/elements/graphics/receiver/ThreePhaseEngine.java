package pl.com.hom.elements.graphics.receiver;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.configuration.Roles;
import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.ColumnRow;
import pl.com.hom.scheme.Column;

import static pl.com.hom.configuration.Resource.getImage;

public class ThreePhaseEngine extends ColumnRow {
	public static float L1WIDTH = 100f * Measures.SCALE;
	public static float L2WIDTH = 200f * Measures.SCALE;
	public static float L3WIDTH = 300f * Measures.SCALE;

	public ThreePhaseEngine(Column parent) {
		this.name       = "ThreePhaseEngine";
		this.visibility = true;
		this.image      = getImage(name);
		this.role       = Roles.role(name);

		this.x = parent.widthPos();
		this.y = Measures.COL_LEV_HEIGHT * role.level();

		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;

		points = new ArrayList<Point>();

		points.add(Point.upOrDownPotential(this, "L1________", Direction.Up));
		points.add(Point.upOrDownPotential(this, "L2________", Direction.Up));
		points.add(Point.upOrDownPotential(this, "L3________", Direction.Up));

		parent.addElement(this);
	}
}
