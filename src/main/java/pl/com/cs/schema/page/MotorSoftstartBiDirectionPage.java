package pl.com.cs.schema.page;

import pl.com.cs.fps.Fps;
import pl.com.cs.schema.bridge.DownRightPhases;
import pl.com.cs.schema.bridge.UpDownLeftPhases;
import pl.com.cs.schema.child.ContactorSingleChild;
import pl.com.cs.schema.main.CkfMain;
import pl.com.cs.schema.main.ContactorMain;
import pl.com.cs.schema.main.FuseSwitchMain;
import pl.com.cs.schema.main.MksMain;
import pl.com.cs.schema.main.SoftstartMain;
import pl.com.cs.schema.out.Motor;

import static pl.com.cs.config.Widths.x;
import static pl.com.cs.config.Heights.y;

import static pl.com.cs.config.Measures.scaled;

public class MotorSoftstartBiDirectionPage extends Page {
	private static final long serialVersionUID = 1L;

	public MotorSoftstartBiDirectionPage(Fps fps, String ster1, String ster2, String sterL, String sterR, String fuse) {
		super(fps);

		new UpDownLeftPhases(this, x("softstart"), y("softstart") - y("spaceUp") - scaled(300f));
		new DownRightPhases(this, x("1"), y("softstart") - y("spaceUp") - scaled(300f));

		new FuseSwitchMain(this, x("3"), y("mainPhuse"), fuse);

		var ckf = new CkfMain(this, x("1"), y("ckf"));
		ckf.addCkfFuse("L1", "L2", "L3");

		ContactorMain cl = new ContactorMain(this, coilX(), sterL);
		ContactorMain cr = new ContactorMain(this, coilX(), sterR);

		cl.left(this,  x("softstart"), "direction");
		cr.right(this, x("softstart") + x("colSpace"), "direction");

		new ContactorSingleChild(this, cl, cr);
		new ContactorSingleChild(this, cr, cl);

		new SoftstartMain(this, "L10");

		new MksMain(this).control(this);

		new Motor(this);
	}
}
