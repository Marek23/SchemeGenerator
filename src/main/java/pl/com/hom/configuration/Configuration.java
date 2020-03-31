package pl.com.hom.configuration;

public class Configuration {
	public static void initialize() {
		pl.com.hom.configuration.Document.initialize("RWG3.pdf");
		pl.com.hom.configuration.Resource.initializeImages();
		pl.com.hom.configuration.Level.initialize();
	}
}
