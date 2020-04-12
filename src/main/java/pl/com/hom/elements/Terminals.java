package pl.com.hom.elements;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.configuration.TerminalGroups;
import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.connections.Terminal;
import pl.com.hom.scheme.Page;

public class Terminals extends Element {
	public Terminals(Page parent, float x, float y, Element receiver) {
		this.name       = "Terminals";
		this.visibility = false;
		this.image      = null;

		this.x = x;
		this.y = y;

		this.nameXPos = x - 80f * Measures.SCALE;
		this.nameYPos = 595.0f - y - this.height();

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
