package pl.com.hom.configuration;

import java.io.FileNotFoundException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

public final class Document {
	private static PdfWriter  writer; 
	private static PdfDocument document;

	public static void initialize (String name) {
		try {
			writer   = new PdfWriter(name);
			document = new PdfDocument(writer);
		} catch (FileNotFoundException e) {e.printStackTrace();}
	}

	public static PdfDocument getPdfDocument() {
		return document;
	}
}
