package pl.com.cs.schema.page;

import java.util.ArrayList;

import pl.com.cs.fps.Fps;
import pl.com.cs.schema.Point;
import pl.com.cs.fps.SapInput;
import pl.com.cs.schema.child.PlcChild;
import pl.com.cs.schema.main.MklMain;

import static pl.com.cs.config.Heights.y;
import static pl.com.cs.config.Measures.scaled;

public class MklPage extends Page {
	public MklPage(Fps fps, ArrayList<SapInput> sapInputs) {
		super(fps);

		var virtualSignals = 1;
		for (var i = 0; i < virtualSignals; i++)
			plcSignalX();

		var mkl = new MklMain(this, sapInputs);

		int i = 0;
		for(SapInput in: sapInputs) {
			float widthPos = plcSignalX();

			new PlcChild(this, fps.nextPlcMainForInput(), "X", in.function(), widthPos, y("plcSignal"), false);

			Point.upLeft(this, widthPos + scaled(200f), y("plcSignal") + mkl.height() + i*y("spaceDown"), false, "DC24");
			Point.downRight(this, mkl.nextOutputX(), y("plcSignal") + mkl.height() + i*y("spaceDown"), false, "DC24");

			i++;

			widthPos = plcSignalX();

			new PlcChild(this, fps.nextPlcMainForInput(), "X", "Awaria linii: " + in.function(), widthPos, y("plcSignal"), false);

			Point.upLeft(this, widthPos + scaled(200f), y("plcSignal") + mkl.height() + i*y("spaceDown"), false, "DC24");
			Point.downRight(this, mkl.nextOutputX(), y("plcSignal") + mkl.height() + i*y("spaceDown"), false, "DC24");

			i++;

			new pl.com.cs.schema.out.SapInput(this, in, mkl.nextInputX());
		}
	}
}
