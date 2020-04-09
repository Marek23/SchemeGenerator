package pl.com.hom.elements.bridges;

import static pl.com.hom.configuration.Resource.getImage;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.configuration.Roles;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.ColumnRow;
import pl.com.hom.scheme.Column;

public class AboveContactorBridge extends ColumnRow {
	public AboveContactorBridge (Column parent) {
		this.name       = "AboveContactorBridge";
		this.visibility = true;
		this.image      = getImage(name);
		this.role       = Roles.getRole(name);

		this.x = parent.getWidthPos();
		this.y = Measures.COL_LEV_HEIGHT * role.getLevel();

		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;

		points = new ArrayList<Point>();

		points.add(Point.newAboveContactorBridge(this, "L1____ABVCONBRGE"));
		points.add(Point.newAboveContactorBridge(this, "L2____ABVCONBRGE"));
		points.add(Point.newAboveContactorBridge(this, "L3____ABVCONBRGE"));

		parent.addElement(this);
	}
}
