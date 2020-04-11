package pl.com.hom.elements;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.configuration.Roles;
import pl.com.hom.configuration.TerminalGroups;
import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.connections.Terminal;
import pl.com.hom.scheme.Column;

public class Terminals extends ColumnRow {
	public Terminals(Column parent, ColumnRow receiver) {
		this.name       = "Terminals";
		this.visibility = false;
		this.image      = null;
		this.role       = Roles.role(name);

		this.x = parent.widthPos();
		this.y = Measures.COL_LEV_HEIGHT * role.level() + Measures.COL_LEV_HEIGHT/2f;

		this.nameXPos = this.widthPos() - 80f * Measures.SCALE;
		this.nameYPos = 595.0f - this.heightPos() - this.height();

		this.techName = receiver.terminalGroup + ":";

		points    = new ArrayList<Point>();
		terminals = new ArrayList<Terminal>();

		this.terminalGroup = receiver.terminalGroup();

		for (Point p: receiver.points())
			if(p.directions().containsKey(Direction.Up))
			{
				Terminal terminal = new Terminal(this, p.potential(), TerminalGroups.sequence(this.terminalGroup()));
				terminals.add(terminal);

				points.add(Point.upOrDownPotential(this, p.potential().fullName(), Direction.Up));
				points.add(Point.upOrDownPotential(this, p.potential().fullName(), Direction.Down));
			}

		parent.addElement(this);
	}
}
