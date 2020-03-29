package pl.com.hom;

public class Configuration {
	public static void initialize() {
		pl.com.hom.Document.initialize("RWG3.pdf");
		pl.com.hom.utils.Resource.initializeImages();
		pl.com.hom.utils.ColumnLevels.initialize();
	}
}
