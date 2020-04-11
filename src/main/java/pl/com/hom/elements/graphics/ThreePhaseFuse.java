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
	public static String techSymbol = "F";

	public ThreePhaseFuse(Column parent, int pageNr, int number) {
		this.name       = "ThreePhaseFuse";
		this.visibility = true;
		this.image      = getImage(name);
		this.role       = Roles.role(name);

		this.x = parent.widthPos();
		this.y = Measures.COL_LEV_HEIGHT * role.level();
		
		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;
		
		this.techName = String.valueOf(pageNr) + techSymbol + String.valueOf(number);

		this.nameXPos = this.widthPos() - 22f;
		this.nameYPos = 595.0f - this.heightPos() - this.height()/1.5f;

		points = new ArrayList<Point>();

		points.add(Point.newStandardThreePhase(this, "MAINL1____INHORLINE", Direction.Up));
		points.add(Point.newStandardThreePhase(this, "MAINL2____INHORLINE", Direction.Up));
		points.add(Point.newStandardThreePhase(this, "MAINL3____INHORLINE", Direction.Up));

		points.add(Point.newStandardThreePhase(this, "L1________INHORLINE", Direction.Down));
		points.add(Point.newStandardThreePhase(this, "L2________INHORLINE", Direction.Down));
		points.add(Point.newStandardThreePhase(this, "L3________INHORLINE", Direction.Down));

		parent.addElement(this);
	}
}
