package pl.com.cs.schema.page;

import pl.com.cs.fps.Fps;
import pl.com.cs.schema.Point;
import pl.com.cs.schema.child.CkfChild;
import pl.com.cs.schema.child.OvervoltageChild;
import pl.com.cs.schema.child.PlcChild;
import pl.com.cs.schema.main.CkfMain;
import pl.com.cs.schema.main.Disconnector;
import pl.com.cs.schema.main.FuseThermal1;
import pl.com.cs.schema.main.OvervoltageMain;

import static pl.com.cs.config.Widths.x;
import static pl.com.cs.config.Heights.y;
import static pl.com.cs.config.Measures.scaled;

public class FirstPage extends Page {
	private static final long serialVersionUID = -3508996729032743913L;

	private static final float F_L10Margin = 6f;
	public FirstPage(Fps parent) {
		super(parent);

		var todo = 100; // ampery
		new Disconnector(this, todo);

		var F_L10 = new FuseThermal1(this, x("5"), y("mainPhuse"), "MAINL1", "B10");

		Point.upRight(this, F_L10.widthPos() + scaled(100f), F_L10.heightPos() + F_L10.height() + F_L10Margin, false, "L10");
		Point.upLeft( this, F_L10.widthPos() + scaled(400f), F_L10.heightPos() + F_L10.height() + F_L10Margin, false, "L10");

		var ignore = Point.upLeft(this, F_L10.widthPos() + scaled(400f), F_L10.heightPos() + F_L10.height() + F_L10Margin, false, "MAINL10");
		createMainForPoint(ignore);


		var ckf = new CkfMain(this, x("4"), y("coil"));
		ckf.addCkfFuse();

		var virtualInputs = 6;
		for (int i = 0; i < virtualInputs; i++)
			plcSignalX();

		var childTargetingUP = true;
		var ch1 = new PlcChild(this, fps.nextPlcMainForInput(), "X", "Kontrola faz",           plcSignalX(), y("plcSignal"), childTargetingUP);
		var ch2 = new PlcChild(this, fps.nextPlcMainForInput(), "X", "Kontrola przepięciówki", plcSignalX(), y("plcSignal"), childTargetingUP);

		var contactMarginToTargetPlcIn = scaled(100f);
		var overvoltage = new OvervoltageMain(this);

		new OvervoltageChild(this, overvoltage, ch1.widthPos() + contactMarginToTargetPlcIn, y("steeringContact"));
		new CkfChild(this, ckf, ch2.widthPos() + contactMarginToTargetPlcIn, y("steeringContact"));
	}
}
