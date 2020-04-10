package pl.com.hom.fpsdrawer;

import static pl.com.hom.configuration.Document.getPdfDocument;

import pl.com.hom.configuration.Configuration;
import pl.com.hom.scheme.JetPage;

public class Main {
	public static void main(String args[]) throws Exception {				
		System.out.println("Start");

		Configuration.initialize();

		JetPage page = new JetPage("L1", "L2", 1);
		page.draw();
			  		 
		getPdfDocument().close();  
		
		System.out.println("Object drawn on pdf successfully");
   }
}