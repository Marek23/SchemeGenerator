package pl.com.hom.elements.graphics;

import static pl.com.hom.configuration.Resource.getImage;

import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;

import pl.com.hom.electric.Potential;

public class Terminal {
	private Potential potental;

	private float x;
	private int   y;

	private String  group;
	private boolean groupVisibility;

	protected PdfFormXObject image;
	
    public Potential getPotental() {
		return potental;
	}

	public Terminal() {
		image = getImage("Terminal.wmf");
    }
}
