package pl.com.hom.page;

import java.util.ArrayList;

import pl.com.hom.board.Board;
import pl.com.hom.element.secondary.PlcSignal;

public class Steering extends Page {
	private static final long serialVersionUID = 1L;
	public Steering(Board board, ArrayList<String> steerings) {
		super(board);

		for (String s: steerings)
			new PlcSignal(this, board.nextOutput(), "Y", s, plcSignalX(), false)
				.addSteeringCoil();
	}
}
