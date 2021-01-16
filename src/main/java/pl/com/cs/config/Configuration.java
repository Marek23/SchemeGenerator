package pl.com.cs.config;

import java.io.IOException;

import pl.com.cs.Reader;
import pl.com.cs.schema.Potentials;

public class Configuration {
	public static void initialize() throws IOException {
		Images.initializeImages();
		Potentials.initialize();

		Widths.initialize();
		Heights.initialize();

		var balance = "BilansMocy.xls";
		var matrix  = "MatrycaSterowan.xls";
		var signals = "PunktyDanychDoInstalacjiSSP.xls";
		Reader.readFiles(balance, matrix, signals);
	}
}
