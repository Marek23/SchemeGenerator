package pl.com.hom.connections;

import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;

import pl.com.hom.configuration.Measures;
import pl.com.hom.elements.ColumnRow;

import static pl.com.hom.configuration.Resource.getImage;

public class Terminal {
	PdfFormXObject image;
	String name;
	String group;

	Potential potential;

	int id;

	float x;
	float y;
	float nameXPos;
	float nameYPos;

	float width;
	float height;

	public Terminal(ColumnRow parent, Potential potential) {
		this.name  = "Terminal";
		this.image = getImage(name);

		this.potential = potential;

		this.x = parent.getWidthPos() + potential.getWidth();
		this.y = parent.getHeightPos();

		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;

		this.nameXPos = this.getWidthPos() - 30f;
		this.nameYPos = 595.0f - this.getHeightPos();
	}

	public float getWidthPos() {
		return x;
	}

	public float getHeightPos() {
		return y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public Potential getPotential() {
		return potential;
	}

	public PdfFormXObject image() {
		return image;
	}
}
