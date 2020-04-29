package pl.com.hom.element.main;

import static pl.com.hom.configuration.Resource.getImage;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Point;
import pl.com.hom.element.pointer.Pointer;
import pl.com.hom.element.secondary.BridgeContactor;
import pl.com.hom.element.secondary.Contactor;
import pl.com.hom.elements.bridges.UpDownRightPhases;
import pl.com.hom.element.Element;
import pl.com.hom.scheme.Page;

public class CurrentCoil extends Element {
	public CurrentCoil (Page page, float x, float y, String steerPotential) {
		this.name       = "CurrentCoil";
		this.visibility = true;
		this.image      = getImage(name, page);

		this.x = x;
		this.y = y;
		this.page = page;

		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;
		
		this.symbol = symbol(page, "Q");

		this.symbolX = x - 18f;
		this.symbolY = y + this.height()/1.5f;

		points   = new ArrayList<Point>();
		pointers = new ArrayList<Pointer>();

		points.add(Point.up(page, this, 100f, false, steerPotential));
		points.add(Point.down(page, this, 100f, false, "GROUNDN"));

		page.add(this);
	}

	public void secondGear(Page page) {
		new BridgeContactor(page, this, Measures.JET_ENGINE_COL, Measures.CONTACTOR_HEIGHT);
		new Contactor(page, this, Measures.SEC_JET_COL, Measures.CONTACTOR_HEIGHT);
	}

	public void firstGear(Page page) {
		new UpDownRightPhases(page, Measures.FIRST_JET_COL, Measures.UNDER_CONTACTOR_BRIDGE_HEIGHT);
		new Contactor(page, this, Measures.FIRST_JET_COL, Measures.CONTACTOR_HEIGHT);
	}
}
