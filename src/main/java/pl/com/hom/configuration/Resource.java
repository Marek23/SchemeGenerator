package pl.com.hom.configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static pl.com.hom.configuration.Document.getPdfDocument;

import java.net.MalformedURLException;

import com.itextpdf.kernel.PdfException;
import com.itextpdf.kernel.pdf.canvas.wmf.WmfImageData;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;

public final class Resource {
	public final static String Contactor        = "Contactor.wmf";
	public final static String Terminal         = "Terminal.wmf";
	public final static String ThreePhaseFuse   = "ThreePhaseFuse.wmf";
	public final static String ThreePhaseEngine = "ThreePhaseEngine.wmf";
	public final static String Point            = "Point.wmf";
	public final static String UVWBridge        = "UVWBridge.wmf";
	public final static String Coil             = "Coil.wmf";

	private static ArrayList<String> imageNames = new ArrayList<String>(Arrays.asList(
		Contactor,
		Terminal,
		ThreePhaseFuse,
		ThreePhaseEngine,
		Point,
		UVWBridge,
		Coil
	));

	public static HashMap<String,WmfImageData> graphics;
	
	public static void initializeImages() {
		graphics = new HashMap<String, WmfImageData>();
		for (String img : imageNames) {
			try { try {
				WmfImageData wmf = new WmfImageData("src/main/resources/" + img);
				graphics.put(img, wmf);
				} catch (MalformedURLException canHappen) {
					System.out.println("MalformedURLException");
			} } catch (PdfException e) {
				System.out.println("Error while loading image: " + img);
			}
		}
	}

	public static PdfFormXObject getImage(String name) {
		 return new PdfFormXObject(graphics.get(name), getPdfDocument());
	}
}
