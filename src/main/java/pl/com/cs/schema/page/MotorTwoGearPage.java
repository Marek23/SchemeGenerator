package pl.com.cs.schema.page;

import static pl.com.cs.config.Measures.scaled;
import static pl.com.cs.config.Widths.x;

import static pl.com.cs.config.Heights.y;

import pl.com.cs.fps.Fps;
import pl.com.cs.schema.Point;
import pl.com.cs.schema.Potential;
import pl.com.cs.schema.Potentials;
import pl.com.cs.schema.child.ContactorSingleChild;
import pl.com.cs.schema.main.ContactorMain;
import pl.com.cs.schema.main.FuseMotorMain;
import pl.com.cs.schema.main.FuseThermalMotor;
import pl.com.cs.schema.main.MksMain;
import pl.com.cs.schema.out.MotorTwoGear;

public class MotorTwoGearPage extends Page {
	private static final long serialVersionUID = 1L;

	public MotorTwoGearPage(Fps fps, String firstGearPot, String secGearPot, String fuse1, String fuse2) {
		super(fps);

		Potential origin = Potentials.potential(secGearPot);
		Potentials.add(new Potential("INTER" + secGearPot, origin.width(), origin.height()));

		ContactorMain coil1 = new ContactorMain(this, coilX(), firstGearPot);
		ContactorMain coil2 = new ContactorMain(this, coilX(), secGearPot);
		ContactorMain coil3 = new ContactorMain(this, coilX(), "INTER" + secGearPot);

		new ContactorSingleChild(this, coil2, coil1);
		new ContactorSingleChild(this, coil1, coil2);

		coil1.firstGear(this);
		coil2.secondGear(this);
		coil3.secondGearBridge(this);

		Point.downLeft(this, coil3.widthPos() + scaled(100f), y("coil") - 30f, false, "INTER" + secGearPot);
		Point.upDownRight(this, coil2.widthPos() + scaled(100f), y("coil") - 30f, true, "INTER" + secGearPot);

		boolean directional = false;
		new FuseMotorMain(this, x("1"), y("mainPhuse"), fuse2, directional);
		new FuseThermalMotor(this, x("3"), y("mainPhuse"), fuse1);

		MksMain mks = new MksMain(this);
		mks.control(this);

		new MotorTwoGear(this);
	}
}
