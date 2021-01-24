package pl.com.cs.schema.page;

import pl.com.cs.fps.Fps;
import pl.com.cs.schema.main.PlcMain;

public class PlcMainPage extends Page {
	private static final long serialVersionUID = 1L;
	public PlcMainPage(Fps fps, PlcMain plc) {
		super(fps);

		plc.setPage(this);
	}
}
