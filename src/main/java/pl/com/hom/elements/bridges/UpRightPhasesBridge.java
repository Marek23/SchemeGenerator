package pl.com.hom.elements.bridges;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.configuration.Roles;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.ColumnRow;
import pl.com.hom.scheme.Column;

public class UpRightPhasesBridge extends ColumnRow {
	public static float L1WIDTH = 100f * Measures.SCALE;
	public static float L2WIDTH = 200f * Measures.SCALE;
	public static float L3WIDTH = 300f * Measures.SCALE;

	public static float L1HEIGHT = 100f * Measures.SCALE;
	public static float L2HEIGHT = 200f * Measures.SCALE;
	public static float L3HEIGHT = 300f * Measures.SCALE;

	public UpRightPhasesBridge (Column parent) {
		this.name       = "UpRightPhasesBridge";
		this.visibility = false;
		this.image      = null;
		this.role       = Roles.getRole(name);
		this.x = parent.getWidthPos();
		this.y = Measures.COL_LEV_HEIGHT * role.getLevel();

		this.width  = L1WIDTH  * 3;
		this.height = L1HEIGHT * 3;

		this.points = new ArrayList<Point>();

		points.add(Point.newUpRightPoint(this, "L1____", L1WIDTH, L1HEIGHT));
		points.add(Point.newUpRightPoint(this, "L2____", L2WIDTH, L2HEIGHT));
		points.add(Point.newUpRightPoint(this, "L3____", L3WIDTH, L3HEIGHT));

		parent.addElement(this);
	}
}
