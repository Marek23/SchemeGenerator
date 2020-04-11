package pl.com.hom.elements.graphics.receiver;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.configuration.Roles;
import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.ColumnRow;
import pl.com.hom.elements.Terminals;
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

		points.add(Point.newJetEngine(this, "L1________LEFT", Direction.Left));
		points.add(Point.newJetEngine(this, "L2________LEFT", Direction.Left));
		points.add(Point.newJetEngine(this, "L3________LEFT", Direction.Left));

		points.add(Point.newJetEngine(this, "L1________", Direction.Right));
		points.add(Point.newJetEngine(this, "L2________", Direction.Right));
		points.add(Point.newJetEngine(this, "L3________", Direction.Right));

		parent.addElement(this);
	}
}
