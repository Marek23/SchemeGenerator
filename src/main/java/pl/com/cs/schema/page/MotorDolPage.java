package pl.com.cs.schema.page;

import static pl.com.cs.config.Widths.x;

import static pl.com.cs.config.Heights.y;

import pl.com.cs.fps.MotorDol;
import pl.com.cs.schema.FuseFactory;
import pl.com.cs.schema.main.CkfMain;
import pl.com.cs.schema.main.ContactorMain;
import pl.com.cs.schema.main.MksMain;
import pl.com.cs.schema.out.Motor;

public class MotorDolPage extends Page {
	private static final long serialVersionUID = 1L;

	public MotorDolPage(MotorDol motor) {
		super(motor.fps());

		FuseFactory.fuse(this, x("3"), y("mainPhuse"), motor.fuse2());

		var ckf = new CkfMain(this, x("1"), y("ckf"));
		ckf.addCkfFuse("L1", "L2", "L3");

		var c = new ContactorMain(this, coilX(), motor.steeringMain());

		c.mainOrLeft(this,  x("softstart"), "direction");

		new MksMain(this).control(this);
		new Motor(this);

		FuseFactory.fuse(this, x("3"), y("mainPhuse"), motor.fuse2());

	}
}
