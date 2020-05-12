package pl.com.hom.page;

import java.util.ArrayList;

import pl.com.hom.board.Board;
import pl.com.hom.configuration.Measures;
import pl.com.hom.printer.Printer;

public class Plc extends Page {
	private static final long serialVersionUID = 1L;
	Printer printer;
	private ArrayList<pl.com.hom.element.main.Plc> plcs;
	public Plc(Board board, int modules) {
		super(board);

		this.plcs = new ArrayList<pl.com.hom.element.main.Plc>();

		plcs.add(new pl.com.hom.element.main.Plc(this, "Cpu", Measures.BEGIN_MAIN_POINT, Measures.PLC_HEIGHT));
		for (int i = 0; i < modules; i++)
			plcs.add(new pl.com.hom.element.main.Plc(this, "Module", plcX(), Measures.PLC_HEIGHT));
			
	}

	public ArrayList<pl.com.hom.element.main.Plc> plcs() {
		return plcs;
	}
}
