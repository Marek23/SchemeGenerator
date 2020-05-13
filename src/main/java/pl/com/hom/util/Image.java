package pl.com.hom.util;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.canvas.wmf.WmfImageData;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;

import static pl.com.hom.configuration.Measures.scaled;

public class Image extends PdfFormXObject {
	private static final long serialVersionUID = 1L;

	public Image(WmfImageData wmfImage, PdfDocument document) {
		super(wmfImage, document);
	}

	public float getWidth() {
		return scaled(super.getWidth());
	}

	public float getHeight() {
		return scaled(super.getHeight());
	}
}
