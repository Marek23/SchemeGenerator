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
public class BiDirectionSoftstartPage extends Page {
	private static final long serialVersionUID = 1L;
	Printer printer;

	public BiDirectionSoftstartPage(Board board, String firstGearPot, String secGearPot) {
		super(board);

		CurrentCoil left = new CurrentCoil(this, coilX(), Measures.COIL_HEIGHT, firstGearPot);
		left.firstGear(this);

		CurrentCoil right = new CurrentCoil(this, coilX(), Measures.COIL_HEIGHT, secGearPot);
		right.secondGear(this);

		Mks mks = new Mks(this,Measures.MKS_COL, Measures.UNDER_CONTACTOR_BRIDGE_HEIGHT);
		mks.jetControl(this);

		new TwoGearEngine(this, Measures.JET_ENGINE_COL, Measures.RECEIVER_HEIGHT);
	}

	private float coilX() {
		return Measures.COIL + Measures.COIL_SPACE * sequence("PAGE" + String.valueOf(this.nr) + "COIL");
	}
}