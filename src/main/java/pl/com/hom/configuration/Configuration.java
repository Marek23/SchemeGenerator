package pl.com.hom.configuration;

import java.io.IOException;

import pl.com.hom.data.Reader;

public class Configuration {
	public static void initialize() throws IOException {
		Document.initialize("RWG3.pdf");
		Resource.initializeImages();
		Potentials.initialize();
		Reader.init();
	}
}
