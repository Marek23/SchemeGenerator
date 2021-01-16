package pl.com.cs.schema.page;

import pl.com.cs.fps.Fps;
import pl.com.cs.schema.main.PlcMain;

public class PlcMainPage extends Page {
	private static final long serialVersionUID = 1L;
	private PlcMain plc;
	public PlcMainPage(Fps fps, String plcType) {
		super(fps);

		this.plc = new PlcMain(this, plcType);
		this.add(plc);
	}

	public PlcMain plcMain() {
		return this.plc;
	}
}
