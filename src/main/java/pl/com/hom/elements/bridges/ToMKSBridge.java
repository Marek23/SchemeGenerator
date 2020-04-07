package pl.com.hom.elements.bridges;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.configuration.Roles;
import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.ColumnRow;
import pl.com.hom.scheme.Column;

import static pl.com.hom.configuration.Resource.getImage;

public class ToMKSBridge extends ColumnRow {
	public static float L1WIDTH = 100f * Measures.SCALE;
	public static float L2WIDTH = 200f * Measures.SCALE;
	public static float L3WIDTH = 300f * Measures.SCALE;

	public ToMKSBridge (Column parent) {
		this.name       = "ToMKSBridge";
		this.visibility = true;
		this.image      = getImage(name);
		this.role       = Roles.getRole(name);

		this.x = parent.getWidthPos();
		this.y = Measures.COL_LEV_HEIGHT * this.role.getLevel();

		points = new ArrayList<Point>();

		points.add(new Point(this, "L1____", Direction.Right, L1WIDTH));
		points.add(new Point(this, "L2____", Direction.Right, L2WIDTH));
		points.add(new Point(this, "L3____", Direction.Right, L3WIDTH));

		parent.addElement(this);
	}
}
