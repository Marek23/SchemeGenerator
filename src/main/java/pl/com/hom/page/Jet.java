package pl.com.hom.page;

import pl.com.hom.board.Board;
import pl.com.hom.configuration.Measures;
import pl.com.hom.element.main.CurrentCoil;
import pl.com.hom.element.main.Mks;
import pl.com.hom.element.main.MotorFuse3;
import pl.com.hom.element.main.ThermalFuse3;
import pl.com.hom.element.receiver.TwoGearEngine;

public class Jet extends Page {
	private static final long serialVersionUID = 1L;

	public Jet(Board board, String firstGearPot, String secGearPot) {
		super(board);

		CurrentCoil coil1 = new CurrentCoil(this, coilX(), Measures.COIL_HEIGHT, firstGearPot);
		coil1.firstGear(this);

		CurrentCoil coil2 = new CurrentCoil(this, coilX(), Measures.COIL_HEIGHT, secGearPot);
		coil2.secondJetGear(this);

		new MotorFuse3(this, Measures.THIRD_WIDTH, Measures.FUSE_HEIGHT, true);
		new ThermalFuse3(this, Measures.FIRST_WIDTH, Measures.FUSE_HEIGHT);

		Mks mks = new Mks(this,Measures.MKS_WIDTH, Measures.MKS_HEIGHT);
		mks.control(this);

		new TwoGearEngine(this, Measures.SEC_WIDTH, Measures.RECEIVER_HEIGHT);
	}
}