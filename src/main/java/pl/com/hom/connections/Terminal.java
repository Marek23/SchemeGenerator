package pl.com.hom.connections;

import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;

import pl.com.hom.page.Page;

import static pl.com.hom.configuration.Sequences.sequence;
import static pl.com.hom.configuration.Resource.getImage;
import static pl.com.hom.configuration.Heights.y;

public class Terminal {
	private PdfFormXObject image;
	private String name;

	private Potential potential;

	private String id;

	private float x;
	private float y;
	private float symbolX;
	private float symbolY;

	private float width;
	private float height;

	private static float xSymbolMargin = 22f;

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
			this.id = String.valueOf(sequence(page.board().name() + group));

		this.symbolX = this.x + xSymbolMargin;
		this.symbolY = this.y;

		Point.up(page, this, false, point.potential().name());
		Point.down(page, this, false, point.potential().name());

		page.add(this);
	}

	public float widthPos() {
		return x;
	}

	public float heightPos() {
		return y;
	}

	public float width() {
		return width;
	}

	public float height() {
		return height;
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

	
	public Potential potential() {
		return potential;
	}

	public PdfFormXObject image() {
		return image;
	}
}
