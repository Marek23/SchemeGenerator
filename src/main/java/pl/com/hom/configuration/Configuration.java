package pl.com.hom.configuration;

import java.io.IOException;

import pl.com.hom.data.Reader;

public class Configuration {
	public static void initialize() throws IOException {
		Resource.initializeImages();
		Potentials.initialize();

		Widths.initialize();
		Heights.initialize();
		Reader.init();
	}
}
