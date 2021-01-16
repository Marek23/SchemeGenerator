package pl.com.cs;

import pl.com.cs.config.Configuration;

public class Main {
	public static void main(String args[]) throws Exception {				
		System.out.println("Start");

		Configuration.initialize();

		Reader.read();

		Reader.generate();
		System.out.println("Object drawn on pdf successfully");
   }
}