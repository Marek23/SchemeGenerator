package pl.com.cs.schema.page;

import java.util.ArrayList;

import pl.com.cs.fps.Fps;
import pl.com.cs.schema.Point;
import pl.com.cs.fps.SapInput;
import pl.com.cs.schema.child.PlcChild;

import static pl.com.cs.config.Heights.y;
import static pl.com.cs.config.Measures.scaled;

public class MklPage extends Page {
	private static final long serialVersionUID = 1L;

	public MklPage(Fps board, ArrayList<SapInput> sapInputs) {
		super(board);

		// ommiting first input positions
		plcSignalX(); plcSignalX();

		pl.com.cs.schema.main.MklMain mkl = new pl.com.cs.schema.main.MklMain(this, sapInputs);

		int i = 0;
		for(SapInput in: sapInputs) {
			float widthPos = plcSignalX();

			new PlcChild(this, board.nextInput(), "X", in.function(), widthPos, y("plcSignal"), false);

			Point.upLeft(this, widthPos + scaled(200f), y("plcSignal") + mkl.height() + i*y("spaceDown"), false, "DC24");
			Point.downRight(this, mkl.nextOutputX(), y("plcSignal") + mkl.height() + i*y("spaceDown"), false, "DC24");

			i++;

			widthPos = plcSignalX();

			new PlcChild(this, board.nextInput(), "X", "Awaria linii: " + in.function(), widthPos, y("plcSignal"), false);

			Point.upLeft(this, widthPos + scaled(200f), y("plcSignal") + mkl.height() + i*y("spaceDown"), false, "DC24");
			Point.downRight(this, mkl.nextOutputX(), y("plcSignal") + mkl.height() + i*y("spaceDown"), false, "DC24");

			i++;

			new pl.com.cs.schema.out.SapInput(this, in, mkl.nextInputX());
		}
	}
}