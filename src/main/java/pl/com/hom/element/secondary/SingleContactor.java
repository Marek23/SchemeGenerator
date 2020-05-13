package pl.com.hom.element.secondary;

import java.util.ArrayList;

import pl.com.hom.connections.Point;
import pl.com.hom.element.Element;
import pl.com.hom.element.main.CurrentCoil;
import pl.com.hom.element.pointer.SingleContactorPointer;
import pl.com.hom.page.Page;

import static pl.com.hom.configuration.Resource.getImage;
import static pl.com.hom.configuration.Heights.y;

public class SingleContactor extends Element {
	private static float xSymbolMargin = 20f;
	private static float ySymbolMargin = 20f;

	private static float xParentMargin = -12f;
	private static float yParentMargin = 20f;

	public SingleContactor(Page page, CurrentCoil parent, CurrentCoil paired) {
		this.name       = "SingleContactor";
		this.visibility = true;
		this.image      = getImage(name, page);
		this.page       = page;

		this.x = paired.widthPos();
		this.y = y("coilSeparator");
		this.page = page;

		this.width  = image.getWidth();
		this.height = image.getHeight();

		this.symbol = parent.symbol();

		ySymbolMargin = this.height()/1.5f;
		this.symbolX = this.widthPos() - xSymbolMargin;
		this.symbolY = this.heightPos() + ySymbolMargin;

		yParentMargin = this.height()/1.5f + 10f;
		this.parentX = this.widthPos() - xParentMargin;
		this.parentY = this.heightPos() + yParentMargin;
		this.parentPageNr = parent.page().nr();

		points = new ArrayList<Point>();

		points.add(Point.up(page, this, 100f, false, paired.steering()));
		points.add(Point.down(page, this, 100f, false, paired.steering()));

		page.add(this);

		this.parent = parent;
		new SingleContactorPointer(this, parent);
	}
}
