package pl.com.cs.schema.page;

import static pl.com.cs.config.Measures.scaled;
import static pl.com.cs.config.Widths.x;

import static pl.com.cs.config.Heights.y;

import pl.com.cs.fps.MotorTwoGear;
import pl.com.cs.schema.FuseFactory;
import pl.com.cs.schema.Point;
import pl.com.cs.schema.Potential;
import pl.com.cs.schema.Potentials;
import pl.com.cs.schema.child.ContactorSingleChild;
import pl.com.cs.schema.main.ContactorMain;
import pl.com.cs.schema.main.MksMain;

public class MotorTwoGearPage extends Page {
	private static final long serialVersionUID = 1L;

	public MotorTwoGearPage(MotorTwoGear motor) {
		super(motor.fps());

		Potential origin = Potentials.potential(motor.steering2());
		Potentials.add(new Potential(motor.steering2() + "INTER", origin.width(), origin.height()));

		ContactorMain coil1 = new ContactorMain(this, coilX(), motor.steering1());
		ContactorMain coil2 = new ContactorMain(this, coilX(), motor.steering2());
		ContactorMain coil3 = new ContactorMain(this, coilX(), motor.steering2() + "INTER");

		new ContactorSingleChild(this, coil2, coil1);
		new ContactorSingleChild(this, coil1, coil2);

		coil1.firstGear(this);
		coil2.secondGear(this);
		coil3.secondGearBridge(this);

		Point.downLeft(this, coil3.widthPos() + scaled(100f), y("coil") - 30f, false, motor.steering2() + "INTER");
		Point.upDownRight(this, coil2.widthPos() + scaled(100f), y("coil") - 30f, true, motor.steering2() + "INTER");

		boolean directional = false;
		var fuse1B = FuseFactory.fuse(this, x("1"), y("mainPhuse"), motor.fuse2().toUpperCase(), directional);
		var fuse2B = FuseFactory.fuse(this, x("3"), y("mainPhuse"), motor.fuse1().toUpperCase());

		MksMain mks = new MksMain(this);
		mks.control(this);

		this.addNonFireFuse(fuse1B);
		if (motor.fireMode())
			this.addFireFuse(fuse2B);
		else
			this.addNonFireFuse(fuse2B);

		new pl.com.cs.schema.out.MotorTwoGear(this);
	}
}
