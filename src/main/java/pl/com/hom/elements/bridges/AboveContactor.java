package pl.com.hom.elements.bridges;

import static pl.com.hom.configuration.Resource.getImage;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.configuration.Roles;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.ColumnRow;
import pl.com.hom.scheme.Column;

public class AboveContactor extends ColumnRow {
	public AboveContactor (Column parent) {
		this.name       = "AboveContactorBridge";
		this.visibility = true;
		this.image      = getImage(name);
		this.role       = Roles.role(name);

		this.x = parent.widthPos();
		this.y = Measures.COL_LEV_HEIGHT * role.level();

		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;

		points = new ArrayList<Point>();

		points.add(Point.newAboveContactorBridge(this, "L1________INHORLINE"));
		points.add(Point.newAboveContactorBridge(this, "L2________INHORLINE"));
		points.add(Point.newAboveContactorBridge(this, "L3________INHORLINE"));

		parent.addElement(this);
	}
}