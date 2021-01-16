package pl.com.cs.schema.page;

import static pl.com.cs.config.Measures.scaled;
import static pl.com.cs.config.Widths.x;

import static pl.com.cs.config.Heights.y;

import pl.com.cs.fps.Fps;
import pl.com.cs.schema.bridge.DownLeftPhases;
import pl.com.cs.schema.bridge.UpDownRightPhases;
import pl.com.cs.schema.child.ContactorSingleChild;
import pl.com.cs.schema.main.ContactorMain;
import pl.com.cs.schema.main.FuseMotorMain;
import pl.com.cs.schema.main.FuseThermalMotor;
import pl.com.cs.schema.main.MksMain;
import pl.com.cs.schema.out.MotorTwoGear;

public class MotorTwoGearBiDirectionPage extends Page {
	private static final long serialVersionUID = 1L;

	public MotorTwoGearBiDirectionPage(Fps fps, String first, String sec, String left, String right, String fuse1, String fuse2) {
		super(fps);

		ContactorMain c1 = new ContactorMain(this, coilX(), first);
		ContactorMain c2 = new ContactorMain(this, coilX(), sec);
		ContactorMain c3 = new ContactorMain(this, coilX(), sec);

		c1.firstGear(this);
		c2.secondGearBridge(this);
		c3.secondDirGear(this);

		new ContactorSingleChild(this, c1, c2);
		new ContactorSingleChild(this, c2, c1);

		ContactorMain cl = new ContactorMain(this, coilX(), left);
		ContactorMain cr = new ContactorMain(this, coilX(), right);

		cl.left(this, x("1"), "softstart");
		cr.right(this, x("2"), "softstart");

		new ContactorSingleChild(this, cl, cr);
		new ContactorSingleChild(this, cr, cl);

		new UpDownRightPhases(this, x("1"), y("directionPhuse") - scaled(400f), true);
		new DownLeftPhases(this, x("3"), y("directionPhuse") - scaled(400f));

		boolean directional = true;
		new FuseMotorMain(this, x("1"), y("directionPhuse"), fuse2, directional);
		new FuseThermalMotor(this, x("1"), y("mainPhuse"), fuse1);

		new MksMain(this).control(this);

		new MotorTwoGear(this);
	}
}
