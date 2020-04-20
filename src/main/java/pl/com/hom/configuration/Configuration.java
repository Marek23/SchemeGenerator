package pl.com.hom.configuration;

import java.io.IOException;

import pl.com.hom.data.Reader;

public class Configuration {
	public static void initialize() throws IOException {
		Resource.initializeImages();
		Potentials.initialize();
		Reader.init();

		Sequences.sequence("page");
	}
}
