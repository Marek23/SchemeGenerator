package pl.com.hom.elements.graphics;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Direction;
import pl.com.hom.connections.Point;
import pl.com.hom.elements.Element;
import pl.com.hom.elements.bridges.UpLeftPhases;
import pl.com.hom.scheme.Page;

import static pl.com.hom.configuration.Resource.getImage;

public class JetBridgeContactor extends Element {
	public static float L1WIDTH = 100f * Measures.SCALE;
	public static float L2WIDTH = 200f * Measures.SCALE;
	public static float L3WIDTH = 300f * Measures.SCALE;

	public JetBridgeContactor (Page parent, Element element) {
		this.name       = "JetBridgeContactor";
		this.visibility = true;
		this.image      = getImage(name, parent.getDocument());

		this.x = Measures.JET_ENGINE_COL;
		this.y = Measures.CONTACTOR_HEIGHT;

		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;
		
		this.symbol = element.symbol();

		this.symbolX = this.widthPos() - 22f;
		this.symbolY = 595.0f - this.heightPos() - this.height()/1.5f;

		points = new ArrayList<Point>();

		points.add(Point.upOrDownPotential(parent, this, "L1________INHORLINE", Direction.Up));
		points.add(Point.upOrDownPotential(parent, this, "L2________INHORLINE", Direction.Up));
		points.add(Point.upOrDownPotential(parent, this, "L3________INHORLINE", Direction.Up));

		points.add(Point.upOrDownPotential(parent, this, "L1________INHORLINE", Direction.Down));
		points.add(Point.upOrDownPotential(parent, this, "L2________INHORLINE", Direction.Down));
		points.add(Point.upOrDownPotential(parent, this, "L3________INHORLINE", Direction.Down));

		new AboveContactor(parent, this.x, Measures.ABOVE_CONTACTOR_BRIDGE);
		new UpLeftPhases(parent, this.x, Measures.UNDER_CONTACTOR_BRIDGE_HEIGHT);

		parent.addElement(this);
	}
}
