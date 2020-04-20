package pl.com.hom.fpsdrawer;

import pl.com.hom.configuration.Configuration;
import pl.com.hom.data.Board;
import pl.com.hom.data.Reader;
import pl.com.hom.scheme.JetPage;
import pl.com.hom.scheme.Page;
import pl.com.hom.scheme.PlcPage;

public class Main {
	public static void main(String args[]) throws Exception {				
		System.out.println("Start");

		Configuration.initialize();

		Board board = new Board("RWG3.pdf");

		Reader.read();
		Reader.show();
//		Page page1 = new JetPage("L1", "L2", 1);
//		page1.draw();
//
//		Page page2 = new PlcPage(2);
//		page2.draw();

//		getPdfDocument().close();

		System.out.println("Object drawn on pdf successfully");
   }
}