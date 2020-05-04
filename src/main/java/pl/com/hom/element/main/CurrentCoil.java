package pl.com.hom.element.main;

import static pl.com.hom.configuration.Resource.getImage;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Point;
import pl.com.hom.element.pointer.Pointer;
import pl.com.hom.element.secondary.BridgeContactor;
import pl.com.hom.element.secondary.Contactor;
import pl.com.hom.elements.bridges.DownLeftPhases;
import pl.com.hom.elements.bridges.UpDownRightPhases;
import pl.com.hom.elements.bridges.UpLeftPhases;
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

	public void firstGear(Page page) {
		Contactor c = new Contactor(page, this, Measures.FIRST_WIDTH, Measures.CONTACTOR_GEAR_HEIGHT, false);

		new UpDownRightPhases(page, Measures.FIRST_WIDTH, Measures.CONTACTOR_GEAR_HEIGHT + c.height() + Measures.CONTACTOR_HEIGHT_DIST, false);
	}

	public void secondGear(Page page) {
		new Contactor(page, this, Measures.THIRD_WIDTH, Measures.CONTACTOR_GEAR_HEIGHT, true);

		new BridgeContactor(page, this, Measures.SEC_WIDTH, Measures.CONTACTOR_GEAR_HEIGHT);
	}

	public void softstartLeft(Page page) {
		Contactor c = new Contactor(page, this, Measures.THIRD_WIDTH, Measures.UNDER_SOFTSTART_HEIGHT, false);

		new UpDownRightPhases(page, Measures.THIRD_WIDTH, Measures.UNDER_SOFTSTART_HEIGHT - Measures.CONTACTOR_HEIGHT_DIST - 300f * Measures.SCALE, false);
		new UpDownRightPhases(page, Measures.THIRD_WIDTH, Measures.UNDER_SOFTSTART_HEIGHT + c.height() + Measures.CONTACTOR_HEIGHT_DIST, false);
	}

	public void softstartRight(Page page) {
		Contactor c = new Contactor(page, this, Measures.FOURTH_WIDTH, Measures.UNDER_SOFTSTART_HEIGHT, true);

		new DownLeftPhases(page, Measures.FOURTH_WIDTH, Measures.UNDER_SOFTSTART_HEIGHT - Measures.CONTACTOR_HEIGHT_DIST - 300f * Measures.SCALE);
		new UpLeftPhases(page,   Measures.FOURTH_WIDTH, Measures.UNDER_SOFTSTART_HEIGHT + c.height() + Measures.CONTACTOR_HEIGHT_DIST);
	}

	public void biGearLeft(Page page) {
		Contactor c = new Contactor(page, this, Measures.FIRST_WIDTH, Measures.CONTACTOR_DIR_HEIGHT, false);

		new UpDownRightPhases(page, Measures.FIRST_WIDTH, Measures.CONTACTOR_DIR_HEIGHT - Measures.CONTACTOR_HEIGHT_DIST - Measures.SCALE * 300f, false);
		new UpDownRightPhases(page, Measures.FIRST_WIDTH, Measures.CONTACTOR_DIR_HEIGHT + c.height() + Measures.CONTACTOR_HEIGHT_DIST, false);
	}

	public void biGearRight(Page page) {
		Contactor c = new Contactor(page, this, Measures.SEC_WIDTH, Measures.CONTACTOR_DIR_HEIGHT, true);

		new DownLeftPhases(page, Measures.SEC_WIDTH, Measures.CONTACTOR_DIR_HEIGHT - Measures.CONTACTOR_HEIGHT_DIST - Measures.SCALE * 300f);
		new UpLeftPhases(page,   Measures.SEC_WIDTH, Measures.CONTACTOR_DIR_HEIGHT + c.height() + Measures.CONTACTOR_HEIGHT_DIST);
	}
}
