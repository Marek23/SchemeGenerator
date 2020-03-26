package pl.com.hom.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import java.net.MalformedURLException;

import com.itextpdf.kernel.pdf.canvas.wmf.WmfImageData;

public final class Resource {
	public final static String Contactor        = "Contactor.svg";
	public final static String Terminal         = "Terminal.svg";
	public final static String ThreePhaseFuse   = "ThreePhaseFuse.svg";
	public final static String ThreePhaseEngine = "ThreePhaseEngine.svg";
	public final static String Point            = "Point.svg";
	public final static String UVWBridge        = "UVWBridge.svg";

	private static ArrayList<String> imageNames = new ArrayList<String>(Arrays.asList(
		Contactor,
		Terminal,
		ThreePhaseFuse,
		ThreePhaseEngine,
		Point,
		UVWBridge
	));

	public static HashMap<String,WmfImageData> graphics;
	
	public static void initializeImages(String imgName) {
		for(String img : imageNames)
		{
			try
			{
				graphics.put(img, new WmfImageData("src/main/resources/" + img));
			} catch (MalformedURLException e)
			{
				System.out.println("Error while loading image: " + img);
			}
		}
	}

	public static WmfImageData getImage(String name) {
		 return graphics.get(name);
	}
}
