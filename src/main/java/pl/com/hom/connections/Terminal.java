package pl.com.hom.connections;

import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;

import pl.com.hom.configuration.Measures;
import pl.com.hom.elements.Element;

import static pl.com.hom.configuration.Resource.getImage;

public class Terminal {
	PdfFormXObject image;
	String name;

	int    number;
	String group;

	Potential potential;

	int id;

	float x;
	float y;
	float nameXPos;
	float nameYPos;

	float width;
	float height;

	public Terminal(Element parent, Potential potential, int id) {
		this.name  = "Terminal";
		this.image = getImage(name);

		this.potential = potential;

		this.x = parent.widthPos() + potential.width();
		this.y = parent.heightPos();

		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;

		this.id = id;
		this.nameXPos = this.widthPos() + 60f * Measures.SCALE;
		this.nameYPos = this.heightPos();

		this.group = parent.terminalGroup();
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
		return nameXPos;
	}

	public float heightNamePos() {
		return nameYPos;
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
