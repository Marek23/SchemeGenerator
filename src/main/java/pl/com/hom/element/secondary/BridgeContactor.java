package pl.com.hom.element.secondary;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Point;
import pl.com.hom.element.Element;
import pl.com.hom.element.main.CurrentCoil;
import pl.com.hom.element.pointer.ContactorPointer;
import pl.com.hom.elements.bridges.UpLeftPhases;
import pl.com.hom.elements.graphics.AboveContactor;
import pl.com.hom.scheme.Page;

import static pl.com.hom.configuration.Resource.getImage;

public class BridgeContactor extends Element {
	public BridgeContactor (Page page, CurrentCoil parent, float x, float y) {
		this.name       = "BridgeContactor";
		this.visibility = true;
		this.image      = getImage(name, page);
		this.page       = page;

		this.x = x;
		this.y = y;

		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;
		
		this.symbol = parent.symbol();

		this.symbolX = this.widthPos() - 22f;
		this.symbolY = this.heightPos() + this.height()/1.5f;

		this.parentX = this.widthPos() - 12f;
		this.parentY = this.heightPos() + this.height()/1.5f + 10f;
		this.parentPageNr = parent.page().nr();

		points = new ArrayList<Point>();

		points.add(Point.up(page, this, 100f, false, "L1"));
		points.add(Point.up(page, this, 200f, false, "L2"));
		points.add(Point.up(page, this, 300f, false, "L3"));

		points.add(Point.down(page, this, 100f, false, "L1"));
		points.add(Point.down(page, this, 200f, false, "L2"));
		points.add(Point.down(page, this, 300f, false, "L3"));

		new AboveContactor(page, x, y - Measures.CONTACTOR_HEIGHT_DIST);

		new UpLeftPhases(page, x, y + this.height() + Measures.CONTACTOR_HEIGHT_DIST);

		page.add(this);

		this.parent = parent;
		parent.add(new ContactorPointer(this, parent.widthPos(), parent.pointerHeightPos()));
	}
}
