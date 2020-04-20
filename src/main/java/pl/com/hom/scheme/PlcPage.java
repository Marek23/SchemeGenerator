package pl.com.hom.scheme;

import pl.com.hom.configuration.Measures;
import pl.com.hom.connections.Point;
import pl.com.hom.data.Board;
import pl.com.hom.elements.plc.PlcCpu;
import pl.com.hom.elements.plc.PlcModule;
import pl.com.hom.printer.Printer;

public class PlcPage extends Page {
	private static final long serialVersionUID = 1L;
	Printer printer;

	public PlcPage(Board parent) {
		super(parent);

		new PlcCpu(this, Measures.PLC_WIDTH, Measures.PLC_HEIGHT);
		new PlcModule(this, 3 * Measures.PLC_WIDTH, Measures.PLC_HEIGHT);
	}

	public void showPoints() {
		for (Point p : points)
			System.out.println(p);
	}

	public void showLines() {
		for (Line l : lines)
			System.out.println(l);
	}
}
