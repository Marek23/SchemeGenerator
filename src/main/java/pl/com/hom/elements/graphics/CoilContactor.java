package pl.com.hom.elements.graphics;

import static pl.com.hom.configuration.Resource.getImage;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.configuration.Roles;
import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.ColumnRow;
import pl.com.hom.elements.Terminals;
import pl.com.hom.scheme.Column;

public class CoilContactor extends ColumnRow {
	public static String techSymbol = "Q";

	public CoilContactor (Column parent, int pageNr, int number, String STEERPOT) {
		this.name       = "CoilContactor";
		this.visibility = true;
		this.image      = getImage(name);
		this.role       = Roles.role(name);

		this.x = parent.widthPos();
		this.y = Measures.COL_LEV_HEIGHT * role.level();
		
		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;
		
		this.techName = String.valueOf(pageNr) + techSymbol + String.valueOf(number);

		this.nameXPos = this.widthPos() - 14f;
		this.nameYPos = 595.0f - this.heightPos() - this.height()/1.5f;

		points = new ArrayList<Point>();
		childs = new ArrayList<ColumnRow>();

		points.add(Point.newCoilPoint(this, "LSTER_____", Direction.Up));
		points.add(Point.newCoilPoint(this, "N_________", Direction.Down));

		parent.addElement(this);
	}

	public void addJetBridgeContactor(Column parent) {
		childs.add(new JetBridgeContactor(parent, this.techName));
	}

	public void addFirstGearContactor(Column parent) {
		childs.add(new FirstGearContactor(parent, this.techName));
	}

	public void addSecGearContactor(Column parent) {
		childs.add(new FirstGearContactor(parent, this.techName));
	}
		
	
}
