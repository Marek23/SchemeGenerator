package pl.com.hom.configuration;

public class Configuration {
	public static void initialize() {
		Document.initialize("RWG3.pdf");
		Resource.initializeImages();
		Roles.initialize();
		Potentials.initialize();
	}
}
