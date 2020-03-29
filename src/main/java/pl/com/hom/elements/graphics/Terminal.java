package pl.com.hom.elements.graphics;

import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;

import pl.com.hom.electric.Potential;

import static pl.com.hom.utils.Resource.getImage;

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
