package pl.com.hom.connections;

import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;

import pl.com.hom.configuration.Measures;
import pl.com.hom.configuration.Sequences;
import pl.com.hom.scheme.Page;

import static pl.com.hom.configuration.Resource.getImage;

import java.util.ArrayList;

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

		if (potential.name().equals("GROUNDPE__"))
			this.id = "PE";
		else
			this.id = String.valueOf(Sequences.sequence(group));

		this.symbolX = this.x + 60f * Measures.SCALE;
		this.symbolY = this.y;

		this.group = group;

		ArrayList<Point> points = new ArrayList<Point>();

		points.add(Point.upOrDownTerminal(this,  Direction.Up));
		points.add(Point.upOrDownTerminal(this, Direction.Down));

		parent.addAll(points);
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
