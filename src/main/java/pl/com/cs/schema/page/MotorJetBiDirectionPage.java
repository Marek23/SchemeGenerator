package pl.com.cs.schema.page;

import static pl.com.cs.config.Measures.scaled;
import static pl.com.cs.config.Widths.x;

import pl.com.cs.fps.MotorJet;
import pl.com.cs.schema.FuseFactory;
import pl.com.cs.schema.bridge.DownLeftPhases;
import pl.com.cs.schema.bridge.UpDownRightPhases;
import pl.com.cs.schema.child.ContactorSingleChild;
import pl.com.cs.schema.main.ContactorMain;
import pl.com.cs.schema.main.MksMain;
import pl.com.cs.schema.out.MotorTwoGear;

import static pl.com.cs.config.Heights.y;

public class MotorJetBiDirectionPage extends Page {
	private static final long serialVersionUID = 1L;

	public MotorJetBiDirectionPage(MotorJet motor) {
		super(motor.fps());

		ContactorMain c1 = new ContactorMain(this, coilX(), motor.steering1());
		ContactorMain c2 = new ContactorMain(this, coilX(), motor.steering2());

		c1.firstGear(this);
		c2.secondDirJetGear(this);

		new ContactorSingleChild(this, c1, c2);
		new ContactorSingleChild(this, c2, c1);

		ContactorMain cl = new ContactorMain(this, coilX(), motor.steeringL());
		ContactorMain cr = new ContactorMain(this, coilX(), motor.steeringR());

		cl.mainOrLeft(this, x("1"), "softstart");
		cr.right(this, x("2"), "softstart");

		new ContactorSingleChild(this, cl, cr);
		new ContactorSingleChild(this, cr, cl);

		new UpDownRightPhases(this, x("1"), y("directionPhuse") - y("spaceUp") - scaled(300f), true);
		new DownLeftPhases(this, x("3"), y("directionPhuse") - y("spaceUp") - scaled(300f));

		boolean directional = true;

		var fuse1B = FuseFactory.fuse(this, x("1"), y("directionPhuse"), motor.fuse1().toUpperCase(), directional);
		var fuse2B = FuseFactory.fuse(this, x("1"), y("mainPhuse"),      motor.fuse2().toUpperCase());

		new MksMain(this).control(this);

		new MotorTwoGear(this);

		this.addNonFireFuse(fuse1B);
		if (motor.fireMode())
			this.addFireFuse(fuse2B);
		else
			this.addNonFireFuse(fuse2B);
	}
}
