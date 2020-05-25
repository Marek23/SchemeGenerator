package pl.com.hom.page;

import java.util.ArrayList;

import pl.com.hom.board.Board;
import pl.com.hom.connections.Point;
import pl.com.hom.data.SapIn;
import pl.com.hom.element.secondary.PlcSignal;

import static pl.com.hom.configuration.Heights.y;
import static pl.com.hom.configuration.Measures.scaled;

public class Mkl extends Page {
	private static final long serialVersionUID = 1L;

	public Mkl(Board board, ArrayList<SapIn> sapInputs) {
		super(board);

		// ommiting first input positions
		plcSignalX(); plcSignalX();

		pl.com.hom.element.main.Mkl mkl = new pl.com.hom.element.main.Mkl(this, sapInputs);

		int i = 0;
		for(SapIn in: sapInputs) {
			float widthPos = plcSignalX();

			new PlcSignal(this, board.nextInput(), "X", in.function(), widthPos, y("plcSignal"), false);

			Point.upLeft(this, widthPos + scaled(200f), y("plcSignal") + mkl.height() + i*y("spaceDown"), false, "DC24");
			Point.downRight(this, mkl.nextOutputX(), y("plcSignal") + mkl.height() + i*y("spaceDown"), false, "DC24");

			i++;

			widthPos = plcSignalX();

			new PlcSignal(this, board.nextInput(), "X", "Awaria linii: " + in.function(), widthPos, y("plcSignal"), false);

			Point.upLeft(this, widthPos + scaled(200f), y("plcSignal") + mkl.height() + i*y("spaceDown"), false, "DC24");
			Point.downRight(this, mkl.nextOutputX(), y("plcSignal") + mkl.height() + i*y("spaceDown"), false, "DC24");

			i++;

			new pl.com.hom.element.receiver.SapIn(this, in, mkl.nextInputX());
		}
	}
}
