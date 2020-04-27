package pl.com.hom.fpsdrawer;

import pl.com.hom.configuration.Configuration;
import pl.com.hom.data.Reader;

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