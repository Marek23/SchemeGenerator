package pl.com.hom.element.secondary;

import java.util.ArrayList;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Point;
import pl.com.hom.element.Element;
import pl.com.hom.element.main.CurrentCoil;
import pl.com.hom.element.pointer.SingleContactorPointer;
import pl.com.hom.page.Page;

import static pl.com.hom.configuration.Resource.getImage;

public class SingleContactor extends Element {
	public SingleContactor(Page page, CurrentCoil parent, CurrentCoil paired) {
		this.name       = "SingleContactor";
		this.visibility = true;
		this.image      = getImage(name, page);
		this.page       = page;

		this.x = paired.widthPos();
		this.y = Measures.CURRENT_COIL_SAVETY;
		this.page = page;

		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;

		this.symbol = parent.symbol();

		this.symbolX = this.widthPos() - 22f;
		this.symbolY = this.heightPos() + this.height()/1.5f;

		this.parentX = this.widthPos() - 12f;
		this.parentY = this.heightPos() + this.height()/1.5f + 10f;
		this.parentPageNr = parent.page().nr();

		points = new ArrayList<Point>();

		points.add(Point.up(page, this, 100f, false, paired.steering()));
		points.add(Point.down(page, this, 100f, false, paired.steering()));

		page.add(this);

		this.parent = parent;
		new SingleContactorPointer(this, parent);
	}
}
