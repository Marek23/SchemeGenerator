package pl.com.hom.elements.bridges;

import static pl.com.hom.configuration.Resource.getImage;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.configuration.Roles;
import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.ColumnRow;
import pl.com.hom.scheme.Column;

public class AboveContactorBridge extends ColumnRow {
	public static float L1WIDTH = 100f * Measures.SCALE;
	public static float L2WIDTH = 200f * Measures.SCALE;
	public static float L3WIDTH = 300f * Measures.SCALE;

	public AboveContactorBridge (Column parent) {
		this.parent = parent;

		this.name       = "AboveContactorBridge";
		this.visibility = true;
		this.image      = getImage(name);
		this.role       = Roles.getRole(name);
		this.x = parent.getWidthPos();
		this.y = Measures.COL_LEV_HEIGHT * role.getLevel();

		this.width  = image.getWidth();
		this.height = image.getHeight();

		points = new ArrayList<Point>();

		points.add(new Point(this, "L1____", Direction.Down, L1WIDTH));
		points.add(new Point(this, "L2____", Direction.Down, L2WIDTH));
		points.add(new Point(this, "L3____", Direction.Down, L3WIDTH));

		parent.addElement(this);
	}
}
