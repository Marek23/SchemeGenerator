package pl.com.hom.page;

import pl.com.hom.configuration.Measures;
import pl.com.hom.data.Board;
import pl.com.hom.printer.Printer;

public class Plc extends Page {
	private static final long serialVersionUID = 1L;
	Printer printer;

	public Plc(Board board, int modules) {
		super(board);

		new pl.com.hom.element.main.Plc(this, "Cpu", Measures.BEGIN_MAIN_POINT, Measures.PLC_HEIGHT);
		for (int i = 0; i < modules; i++)
			new pl.com.hom.element.main.Plc(this, "Module", plcX(), Measures.PLC_HEIGHT);
			
	}
}
