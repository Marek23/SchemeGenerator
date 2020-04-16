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
	private static ArrayList<String> imageNames = new ArrayList<String>(Arrays.asList(
		"FirstGearContactor.wmf",
		"SecGearContactor.wmf",
		"JetBridgeContactor.wmf",
		"Contactor.wmf",
		"Terminal.wmf",
		"ThreePhaseFuse.wmf",
		"ThreePhaseEngine.wmf",
		"JetEngine.wmf",
		"Point.wmf",
		"AboveContactorBridge.wmf",
		"CoilContactor.wmf",
		"Point.wmf",
		"Mks.wmf",
		"MotorFuse3.wmf",
		"ThermalFuse3.wmf",
		"PlcCpu.wmf",
		"PlcModule.wmf"
	));

	public static HashMap<String,WmfImageData> graphics;
	
	public static void initializeImages() {
		graphics = new HashMap<String, WmfImageData>();
		for (String img : imageNames) {
			try { try {
				WmfImageData wmf = new WmfImageData("src/main/resources/" + img);
				graphics.put(img.replaceAll("\\.wmf", ""), wmf);
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
