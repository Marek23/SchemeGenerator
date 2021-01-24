package pl.com.cs.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import java.net.MalformedURLException;

import com.itextpdf.kernel.PdfException;
import com.itextpdf.kernel.pdf.canvas.wmf.WmfImageData;

import pl.com.cs.schema.page.Page;
import pl.com.cs.util.Image;

public final class Images {
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
		"Coil.wmf",
		"Point.wmf",
		"Mks.wmf",
		"Mkl.wmf",
		"MksContact.wmf",
		"ContactMksPointer.wmf",
		"MotorFuse3.wmf",
		"ThermalFuse3.wmf",
		"PlcCpu.wmf",
		"PlcModule.wmf",
		"PlcSignalUp.wmf",
		"PlcSignalDown.wmf",
		"Contact.wmf",
		"ContactorPointer.wmf",
		"Softstart.wmf",
		"Ckf.wmf",
		"SingleContactor.wmf",
		"SingleContactorPointer.wmf",
		"SteeringContact.wmf",
		"SapIn.wmf",
		"SapOut.wmf",
		"Disconnector4P.wmf",
		"Overvoltage.wmf",
		"ThermalFuse1.wmf"
	));

	public static HashMap<String,WmfImageData> graphics;
	
	public static void initializeImages() {
		graphics = new HashMap<String, WmfImageData>();
		for (String img : imageNames) {
			try { try {
				var wmf = new WmfImageData("src/main/resources/" + img);
				graphics.put(img.replaceAll("\\.wmf", ""), wmf);
				} catch (MalformedURLException canHappen) {
					System.out.println("MalformedURLException");
			} } catch (PdfException e) {
				System.out.println("Error while loading image: " + img);
			}
		}
	}

	public static Image getImage(String name, Page parent) {
		 return new Image(graphics.get(name), parent.getDocument());
	}
}
