package pl.com.cs.schema.page;

import java.util.ArrayList;

import pl.com.cs.fps.Fps;
import pl.com.cs.schema.child.PlcChild;

import static pl.com.cs.config.Heights.y;
public class MotorsSteeringsPage extends Page {
	private static final long serialVersionUID = 1L;

	public MotorsSteeringsPage(Fps fps, ArrayList<String> steerings) {
		super(fps);

		for (String s: steerings)
			new PlcChild(this, fps.nextPlcMainForOutput(), "Y", s, plcSignalX(), y("plcSignal"), false)
				.addCoilMotorSteering();
	}
}
