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

		Reader.read();
		Reader.show();

		Reader.generate();
		System.out.println("Object drawn on pdf successfully");
   }
}