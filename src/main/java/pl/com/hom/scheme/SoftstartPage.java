package pl.com.hom.scheme;

import pl.com.hom.configuration.Measures;
import pl.com.hom.data.Board;
import pl.com.hom.element.main.CurrentCoil;
import pl.com.hom.element.main.Mks;
import pl.com.hom.element.main.MotorFuse3;
import pl.com.hom.element.main.ThermalFuse3;
import pl.com.hom.element.receiver.TwoGearEngine;
import pl.com.hom.printer.Printer;

import static pl.com.hom.configuration.Sequences.sequence;
public class SoftstartPage extends Page {
	private static final long serialVersionUID = 1L;
	Printer printer;

	public SoftstartPage(Board board, String firstGearPot, String secGearPot) {
		super(board);

		CurrentCoil coil1 = new CurrentCoil(this, coilX(), Measures.COIL_HEIGHT, firstGearPot);
		coil1.firstGear(this);

		CurrentCoil coil2 = new CurrentCoil(this, coilX(), Measures.COIL_HEIGHT, secGearPot);
		coil2.secondGear(this);


		Mks mks = new Mks(this,Measures.MKS_COL, Measures.UNDER_CONTACTOR_BRIDGE_HEIGHT);
		mks.jetControl(this);

		new TwoGearEngine(this, Measures.JET_ENGINE_COL, Measures.RECEIVER_HEIGHT);
	}

	private float coilX() {
		return Measures.COIL + Measures.COIL_SPACE * sequence("PAGE" + String.valueOf(this.nr) + "COIL");
	}
}
