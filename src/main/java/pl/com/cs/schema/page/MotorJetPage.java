package pl.com.cs.schema.page;

import pl.com.cs.fps.Fps;
import pl.com.cs.schema.child.ContactorSingleChild;
import pl.com.cs.schema.main.ContactorMain;
import pl.com.cs.schema.main.FuseMotorMain;
import pl.com.cs.schema.main.FuseThermalMotor;
import pl.com.cs.schema.main.MksMain;
import pl.com.cs.schema.out.MotorTwoGear;

import static pl.com.cs.config.Widths.x;
import static pl.com.cs.config.Heights.y;

public class MotorJetPage extends Page {
	private static final long serialVersionUID = 1L;

	public MotorJetPage(Fps fps, String firstGearPot, String secGearPot, String fuse1, String fuse2) {
		super(fps);

		ContactorMain c1 = new ContactorMain(this, coilX(), firstGearPot);
		ContactorMain c2 = new ContactorMain(this, coilX(), secGearPot);

		c1.firstGear(this);
		c2.secondJetGear(this);

		new ContactorSingleChild(this, c1, c2);
		new ContactorSingleChild(this, c2, c1);

		boolean directional = false;
		new FuseMotorMain(this, x("3"), y("mainPhuse"), fuse2, directional);
		new FuseThermalMotor(this, x("1"), y("mainPhuse"), fuse1);

		new MksMain(this).control(this);

		new MotorTwoGear(this);
	}
}
