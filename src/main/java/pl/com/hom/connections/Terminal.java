package pl.com.hom.connections;

import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;

import pl.com.hom.configuration.Measures;
import pl.com.hom.scheme.Page;

import static pl.com.hom.configuration.Sequences.sequence;
import static pl.com.hom.configuration.Resource.getImage;

public class Terminal {
	private PdfFormXObject image;
	private String name;

	private String group;

	private Potential potential;

	private String id;

	private float x;
	private float y;
	private float symbolX;
	private float symbolY;

	private float width;
	private float height;

	public Terminal(Page parent, Point point, String group) {
		this.name  = "Terminal";
		this.image = getImage(name, parent.getDocument());

		this.potential = point.potential();

		this.x = point.widthPos();
		this.y = Measures.TERMINAL_HEIGHT;

		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;

		if (potential.shortName().equals("GROUNDPE__"))
			this.id = "PE";
		else
			this.id = String.valueOf(sequence(group));

		this.symbolX = this.x + 60f * Measures.SCALE;
		this.symbolY = this.y;

		this.group = group;

		Point.up(parent, this, point.widthPos(), false, point.potential().fullName());
		Point.down(parent, this, point.widthPos(), false, point.potential().fullName());

		parent.add(this);
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
