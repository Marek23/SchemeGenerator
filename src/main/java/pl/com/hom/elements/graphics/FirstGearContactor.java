package pl.com.hom.elements.graphics;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.configuration.Roles;
import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.ColumnRow;
import pl.com.hom.elements.Terminals;
import pl.com.hom.scheme.Column;

import static pl.com.hom.configuration.Resource.getImage;

public class FirstGearContactor extends ColumnRow {
	public FirstGearContactor (Column parent, String techName) {
		this.name       = "FirstGearContactor";
		this.visibility = true;
		this.image      = getImage(name);
		this.role       = Roles.role(name);

		this.x = parent.widthPos();
		this.y = Measures.COL_LEV_HEIGHT * role.level();
		
		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;

		this.techName = techName;

		this.nameXPos = this.widthPos() - 14f;
		this.nameYPos = 595.0f - this.heightPos() - this.height()/1.5f;


		points = new ArrayList<Point>();

		points.add(Point.newStandardThreePhase(this, "L1____STANDARD__", Direction.Up));
		points.add(Point.newStandardThreePhase(this, "L2____STANDARD__", Direction.Up));
		points.add(Point.newStandardThreePhase(this, "L3____STANDARD__", Direction.Up));

		points.add(Point.newStandardThreePhase(this, "L1____STANDARD__", Direction.Down));
		points.add(Point.newStandardThreePhase(this, "L2____STANDARD__", Direction.Down));
		points.add(Point.newStandardThreePhase(this, "L3____STANDARD__", Direction.Down));

		parent.addElement(this);
	}
}
