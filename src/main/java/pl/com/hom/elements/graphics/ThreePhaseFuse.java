package pl.com.hom.elements.graphics;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.configuration.Roles;
import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.ColumnRow;
import pl.com.hom.scheme.Column;

import static pl.com.hom.configuration.Resource.getImage;

public class ThreePhaseFuse extends ColumnRow {
	public static float L1WIDTH = 100f * Measures.SCALE;
	public static float L2WIDTH = 200f * Measures.SCALE;
	public static float L3WIDTH = 300f * Measures.SCALE;

	public ThreePhaseFuse(Column parent) {
		this.parent = parent;

		this.name       = "ThreePhaseFuse";
		this.visibility = true;
		this.image      = getImage(name);
		this.role       = Roles.getRole(name);
		this.x = parent.getWidthPos();
		this.y = Measures.COL_LEV_HEIGHT * role.getLevel();

		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;

		points = new ArrayList<Point>();

		points.add(new Point(this, "L1____", Direction.Down, L1WIDTH));
		points.add(new Point(this, "L2____", Direction.Down, L2WIDTH));
		points.add(new Point(this, "L3____", Direction.Down, L3WIDTH));

		points.add(new Point(this, "L1____", Direction.Up, L1WIDTH));
		points.add(new Point(this, "L2____", Direction.Up, L2WIDTH));
		points.add(new Point(this, "L3____", Direction.Up, L3WIDTH));

		parent.addElement(this);
	}
}
