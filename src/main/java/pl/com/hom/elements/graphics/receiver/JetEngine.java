package pl.com.hom.elements.graphics.receiver;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.configuration.Roles;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.ColumnRow;
import pl.com.hom.scheme.Column;

import static pl.com.hom.configuration.Resource.getImage;

public class JetEngine extends ColumnRow {
	public static String techSymbol = "JET";

	public JetEngine(Column parent, int pageNr) {
		this.name       = "JetEngine";
		this.visibility = true;
		this.image      = getImage(name);
		this.role       = Roles.role(name);

		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;

		this.x = parent.widthPos();
		this.y = Measures.COL_LEV_HEIGHT * role.level();

		points = new ArrayList<Point>();

		points.add(Point.leftPoint(this, "L1________LEFT"));
		points.add(Point.leftPoint(this, "L2________LEFT"));
		points.add(Point.leftPoint(this, "L3________LEFT"));

		points.add(Point.rightPoint(this, "L1________"));
		points.add(Point.rightPoint(this, "L2________"));
		points.add(Point.rightPoint(this, "L3________"));

		parent.addElement(this);
	}
}
