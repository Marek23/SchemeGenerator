package pl.com.hom.connections;

import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;

import pl.com.hom.configuration.Measures;
import pl.com.hom.configuration.TerminalGroups;
import pl.com.hom.elements.Element;
import pl.com.hom.scheme.Page;

import static pl.com.hom.configuration.Resource.getImage;

public class Terminal {
	PdfFormXObject image;
	String name;

	int    number;
	String group;

	Potential potential;

	String id;

	float x;
	float y;
	float symbolX;
	float symbolY;

	float width;
	float height;

	public Terminal(Page parent, Point point, String group) {
		this.name  = "Terminal";
		this.image = getImage(name);

		this.potential = point.potential();

		this.x = point.widthPos();
		this.y = Measures.TERMINAL_HEIGHT;

		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;

		if (potential.name().equals("GROUNDPE__"))
			this.id = "PE";
		else
			this.id = String.valueOf(TerminalGroups.sequence(group));

		this.symbolX = this.x + 60f * Measures.SCALE;
		this.symbolY = this.y;

		this.group = group;
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
