package pl.com.hom.configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import java.net.MalformedURLException;

import com.itextpdf.kernel.PdfException;
import com.itextpdf.kernel.pdf.canvas.wmf.WmfImageData;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;

import pl.com.hom.page.Page;

public final class Resource {
	private static ArrayList<String> imageNames = new ArrayList<String>(Arrays.asList(
		"FirstGearContactor.wmf",
		"SecGearContactor.wmf",
		"BridgeContactor.wmf",
		"Contactor.wmf",
		"Terminal.wmf",
		"ThreePhaseFuse.wmf",
		"ThreePhaseEngine.wmf",
		"TwoGearEngine.wmf",
		"Point.wmf",
		"AboveContactorBridge.wmf",
		"CurrentCoil.wmf",
		"Point.wmf",
		"Mks.wmf",
		"MksContact.wmf",
		"ContactMksPointer.wmf",
		"MotorFuse3.wmf",
		"ThermalFuse3.wmf",
		"PlcCpu.wmf",
		"PlcModule.wmf",
		"Contact.wmf",
		"ContactorPointer.wmf",
		"Softstart.wmf",
		"Ckf.wmf",
		"SingleContactor.wmf",
		"SingleContactorPointer.wmf"
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

	public static PdfFormXObject getImage(String name, Page parent) {
		 return new PdfFormXObject(graphics.get(name), parent.getDocument());
	}
}
