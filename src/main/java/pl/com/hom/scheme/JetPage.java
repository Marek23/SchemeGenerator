package pl.com.hom.scheme;

import pl.com.hom.configuration.Measures;
import pl.com.hom.data.Board;
import pl.com.hom.elements.graphics.CoilContactor;
import pl.com.hom.elements.graphics.Mks;
import pl.com.hom.elements.graphics.MotorFuse3;
import pl.com.hom.elements.graphics.ThermalFuse3;
import pl.com.hom.elements.graphics.receiver.JetEngine;
import pl.com.hom.printer.Printer;

import static pl.com.hom.configuration.Sequences.sequence;
public class JetPage extends Page {
	private static final long serialVersionUID = 1L;
	Printer printer;

	public JetPage(Board parent, String firstGearPot, String secGearPot) {
		super(parent);

		CoilContactor coil1 = new CoilContactor(this, coilX(), Measures.COIL_HEIGHT,10, firstGearPot);
		coil1.firstGear(this);

		CoilContactor coil2 = new CoilContactor(this, coilX(), Measures.COIL_HEIGHT,10, secGearPot);
		coil2.secondGear(this);

		new MotorFuse3(this, Measures.SECOND_JET_COL, Measures.FUSE_HEIGHT, 10);
		new ThermalFuse3(this, Measures.THIRD_JET_COL, Measures.FUSE_HEIGHT, 10);

		Mks mks = new Mks(this,Measures.MKS_COL, Measures.UNDER_CONTACTOR_BRIDGE_HEIGHT);
		mks.jetControl(this);

		new JetEngine(this, Measures.JET_ENGINE_COL, Measures.RECEIVER_HEIGHT);
	}

	private float coilX() {
		return Measures.COIL + 50f * sequence("PAGE" + String.valueOf(this.nr) + "COIL");
	}
}
