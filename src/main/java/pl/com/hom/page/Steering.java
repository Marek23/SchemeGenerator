package pl.com.hom.page;

import java.util.ArrayList;

import pl.com.hom.board.Board;
import pl.com.hom.configuration.Measures;
import pl.com.hom.element.main.Plc;
import pl.com.hom.element.secondary.PlcSignal;
import pl.com.hom.printer.Printer;

public class Steering extends Page {
	private static final long serialVersionUID = 1L;
	Printer printer;

	public Steering(Board board, ArrayList<String> steerings) {
		super(board);

		for (String s: steerings)
			new PlcSignal(this, board.nextOutput(), "Y", s, plcSignalX(), Measures.PLC_SIGNAL_HEIGHT, false)
				.addSteeringCoil();
	}
}
