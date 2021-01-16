package pl.com.cs.schema.out;

import pl.com.cs.schema.page.Page;
import pl.com.cs.schema.Potential;
import pl.com.cs.schema.Drawable;
import pl.com.cs.schema.Point;

import static pl.com.cs.config.Images.getImage;
import static pl.com.cs.config.Heights.y;

public class Terminal  extends Drawable{
	private String name;

	private Potential potential;

	private String id;

	private float  symbolX;
	private float  symbolY;
	private String group;
	private static float symbolMargin = 22f;

	public Terminal(Page page, Point point, String group) {
		this.name  = "Terminal";
		this.image = getImage(name, page);

		this.potential = point.potential();

		this.x = point.widthPos();
		this.y = y("terminal");

		this.width  = image.getWidth();
		this.height = image.getHeight();

		if (potential.name().equals("GROUNDPE"))
			this.id = "PE";
		else
			this.id = String.valueOf(page.fps().nextValueOf(group));

		this.symbolX = this.x + symbolMargin;
		this.symbolY = this.y;

		this.group = group;

		Point.up(page, this, false, point.potential().name());
		Point.down(page, this, false, point.potential().name());

		page.add(this);
	}

	public float widthNamePos() {
		return symbolX;
	}

	public float heightNamePos() {
		return symbolY;
	}

	public String fullName() {
		return String.valueOf(id);
	}
	
	public String group() {
		return group;
	}

	public Potential potential() {
		return potential;
	}
}
