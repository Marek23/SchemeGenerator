package pl.com.hom.elements;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.configuration.Roles;
import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.connections.Terminal;
import pl.com.hom.scheme.Column;

public class Terminals extends ColumnRow {
	public Terminals(Column parent, ColumnRow receiver) {
		this.name       = "Terminals";
		this.visibility = false;
		this.image      = null;
		this.role       = Roles.getRole(name);

		this.x = parent.getWidthPos();
		this.y = Measures.COL_LEV_HEIGHT * role.getLevel() + Measures.COL_LEV_HEIGHT/2f;

		this.nameXPos = this.getWidthPos() - 22f;
		this.nameYPos = 595.0f - this.getHeightPos() - this.getHeight()/1.5f;

		points    = new ArrayList<Point>();
		terminals = new ArrayList<Terminal>();

		for (Point p: receiver.getPoints())
			if(p.getDirections().containsKey(Direction.Up))
			{
				Terminal t = new Terminal(this, p.getPotential());
				terminals.add(t);
				points.add(Point.newTerminalPoint(this, t, Direction.Up));
				points.add(Point.newTerminalPoint(this, t, Direction.Down));
			}

		parent.addElement(this);
	}
}
