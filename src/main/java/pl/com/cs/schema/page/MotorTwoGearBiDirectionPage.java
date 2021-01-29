package pl.com.cs.schema.page;

import static pl.com.cs.config.Measures.scaled;
import static pl.com.cs.config.Widths.x;

import static pl.com.cs.config.Heights.y;
import pl.com.cs.fps.MotorTwoGear;
import pl.com.cs.schema.FuseFactory;
import pl.com.cs.schema.bridge.DownLeftPhases;
import pl.com.cs.schema.bridge.UpDownRightPhases;
import pl.com.cs.schema.child.ContactorSingleChild;
import pl.com.cs.schema.main.ContactorMain;
import pl.com.cs.schema.main.MksMain;

public class MotorTwoGearBiDirectionPage extends Page {
	private static final long serialVersionUID = 1L;

	public MotorTwoGearBiDirectionPage(MotorTwoGear motor) {
		super(motor.fps());

		ContactorMain c1 = new ContactorMain(this, coilX(), motor.steering1());
		ContactorMain c2 = new ContactorMain(this, coilX(), motor.steering2());
		ContactorMain c3 = new ContactorMain(this, coilX(), motor.steering2());

		c1.firstGear(this);
		c2.secondGearBridge(this);
		c3.secondDirGear(this);

		new ContactorSingleChild(this, c1, c2);
		new ContactorSingleChild(this, c2, c1);

		ContactorMain cl = new ContactorMain(this, coilX(), motor.steeringL());
		ContactorMain cr = new ContactorMain(this, coilX(), motor.steeringR());

		cl.mainOrLeft(this, x("1"), "softstart");
		cr.right(this, x("2"), "softstart");

		new ContactorSingleChild(this, cl, cr);
		new ContactorSingleChild(this, cr, cl);

		new UpDownRightPhases(this, x("1"), y("directionPhuse") - scaled(400f), true);
		new DownLeftPhases(this, x("3"), y("directionPhuse") - scaled(400f));

		boolean directional = true;
		var fuse1B = FuseFactory.fuse(this, x("1"), y("directionPhuse"), motor.fuse1().toUpperCase(), directional);
		var fuse2B = FuseFactory.fuse(this, x("1"), y("mainPhuse"), motor.fuse2().toUpperCase());

		new MksMain(this).control(this);

		this.addNonFireFuse(fuse1B);
		if (motor.fireMode())
			this.addFireFuse(fuse2B);
		else
			this.addNonFireFuse(fuse2B);

		new pl.com.cs.schema.out.MotorTwoGear(this);
	}
}
