package pl.com.cs.schema.page;

import java.util.ArrayList;

import pl.com.cs.fps.Fps;
import pl.com.cs.fps.SapOutput;
import pl.com.cs.schema.child.PlcChild;

import static pl.com.cs.config.Heights.y;

public class SapOutputsPage extends Page {
	private static final long serialVersionUID = 1L;

	public SapOutputsPage(Fps fps, ArrayList<SapOutput> sapOutputs) {
		super(fps);

		// ommiting first input positions
		for(int i = 0; i < 5; i++)
			plcSignalX();

		for(SapOutput out: sapOutputs) {
			float widthPos = nextSapOutput();
			this.addSapOutput(out);
			new PlcChild(this, fps.nextPlcMainForOutput(), "Y", out.function(), plcSignalX(), y("plcSignal"), false)
				.addCoilSapOutput(widthPos);

			new pl.com.cs.schema.out.SapOutput(this, out, widthPos);
		}
	}
}
