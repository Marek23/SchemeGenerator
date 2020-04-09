package pl.com.hom.elements.graphics;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.configuration.Roles;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.ColumnRow;
import pl.com.hom.scheme.Column;

import static pl.com.hom.configuration.Resource.getImage;

public class Mks extends ColumnRow {
	public static String techSymbol = "MKS";

	public Mks(Column parent, int pageNr, int id) {
		this.name       = "Mks";
		this.visibility = true;
		this.image      = getImage(name);
		this.role       = Roles.getRole(name);

		this.x = parent.getWidthPos();
		this.y = Measures.COL_LEV_HEIGHT * role.getLevel();
		
		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;
		
		this.techName = String.valueOf(pageNr) + techSymbol + String.valueOf(id);

		this.nameXPos = this.getWidthPos() - 30f;
		this.nameYPos = 595.0f - this.getHeightPos();

		points = new ArrayList<Point>();

		points.add(Point.newMksPoint(this, "L1____MKS_______"));
		points.add(Point.newMksPoint(this, "L2____MKS_______"));
		points.add(Point.newMksPoint(this, "L3____MKS_______"));

		parent.addElement(this);
	}
}
