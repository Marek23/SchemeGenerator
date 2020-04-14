package pl.com.hom.fpsdrawer;

import static pl.com.hom.configuration.Document.getPdfDocument;

import pl.com.hom.configuration.Configuration;
import pl.com.hom.scheme.Page;

public class Main {
	public static void main(String args[]) throws Exception {				
		System.out.println("Start");

		Configuration.initialize();

		Page page1 = new Page("L1", "L2", 1);
		page1.draw();

		Page page2 = new Page("L1", "L2", 2);
		page2.draw();

		getPdfDocument().close();  
		
		System.out.println("Object drawn on pdf successfully");
   }
}