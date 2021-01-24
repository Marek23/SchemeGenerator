package pl.com.cs.schema.page;

import static pl.com.cs.config.Measures.scaled;
import static pl.com.cs.config.Widths.x;

import static pl.com.cs.config.Heights.y;

import pl.com.cs.fps.MotorDol;
import pl.com.cs.schema.FuseFactory;
import pl.com.cs.schema.bridge.DownRightPhases;
import pl.com.cs.schema.bridge.UpDownLeftPhases;
import pl.com.cs.schema.main.CkfMain;
import pl.com.cs.schema.main.MksMain;
import pl.com.cs.schema.out.Motor;

public class MotorDolPage extends Page {
	private static final long serialVersionUID = 1L;

	public MotorDolPage(MotorDol motor) {
		super(motor.fps());

		new UpDownLeftPhases(this, x("softstart"), y("softstart") - y("spaceUp") - scaled(300f));
		new DownRightPhases(this, x("1"), y("softstart") - y("spaceUp") - scaled(300f));

		FuseFactory.fuse(this, x("3"), y("mainPhuse"), motor.fuse2().toUpperCase());

		var ckf = new CkfMain(this, x("1"), y("ckf"));
		ckf.addCkfFuse("L1", "L2", "L3");

		// new SoftstartMain(this, "L10"); na stycznik i sterowanie

		new MksMain(this).control(this);

		new Motor(this);
	}
}
