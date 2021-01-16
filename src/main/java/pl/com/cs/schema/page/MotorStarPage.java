package pl.com.cs.schema.page;

import static pl.com.cs.config.Measures.scaled;
import static pl.com.cs.config.Widths.x;

import static pl.com.cs.config.Heights.y;

import pl.com.cs.fps.Fps;
import pl.com.cs.schema.bridge.DownRightPhases;
import pl.com.cs.schema.bridge.UpDownLeftPhases;
import pl.com.cs.schema.main.CkfMain;
import pl.com.cs.schema.main.FuseSwitchMain;
import pl.com.cs.schema.main.MksMain;
import pl.com.cs.schema.main.SoftstartMain;
import pl.com.cs.schema.out.Motor;

public class MotorStarPage extends Page {
	private static final long serialVersionUID = 1L;

	public MotorStarPage(Fps fps, String ster1, String ster2, String fuse) {
		super(fps);

		new UpDownLeftPhases(this, x("softstart"), y("softstart") - y("spaceUp") - scaled(300f));
		new DownRightPhases(this, x("1"), y("softstart") - y("spaceUp") - scaled(300f));

		new FuseSwitchMain(this, x("3"), y("mainPhuse"), fuse);

		new CkfMain(this, x("1"), y("ckf"));

		new SoftstartMain(this, "L10");

		new MksMain(this).control(this);

		new Motor(this);
	}
}
