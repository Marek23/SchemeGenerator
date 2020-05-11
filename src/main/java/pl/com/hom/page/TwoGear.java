package pl.com.hom.page;

import pl.com.hom.configuration.Measures;
import pl.com.hom.configuration.Potentials;
import pl.com.hom.connections.Point;
import pl.com.hom.connections.Potential;
import pl.com.hom.data.Board;
import pl.com.hom.element.main.CurrentCoil;
import pl.com.hom.element.main.Mks;
import pl.com.hom.element.main.MotorFuse3;
import pl.com.hom.element.main.ThermalFuse3;
import pl.com.hom.element.receiver.TwoGearEngine;
import pl.com.hom.element.secondary.SingleContactor;
import pl.com.hom.printer.Printer;

public class TwoGear extends Page {
	private static final long serialVersionUID = 1L;
	Printer printer;

	public TwoGear(Board board, String firstGearPot, String secGearPot) {
		super(board);

		Potential origin = Potentials.potential(secGearPot);
		Potentials.add(new Potential("INTER" + secGearPot, origin.width(), origin.height()));

		CurrentCoil coil1 = new CurrentCoil(this, coilX(), Measures.COIL_HEIGHT, firstGearPot);
		coil1.firstGear(this);

		CurrentCoil coil2 = new CurrentCoil(this, coilX(), Measures.COIL_HEIGHT, secGearPot);
		coil2.secondGear(this);

		CurrentCoil coil3 = new CurrentCoil(this, coilX(), Measures.COIL_HEIGHT, "INTER" + secGearPot);
		coil3.secondGearBridge(this);

		new SingleContactor(this, coil2, coil1);
		new SingleContactor(this, coil1, coil2);

		Point.downLeft(this, coil3.widthPos() + 100f * Measures.SCALE, Measures.COIL_HEIGHT - 30f, false, "INTER" + secGearPot);
		Point.upDownRight(this, coil2.widthPos() + 100f * Measures.SCALE, Measures.COIL_HEIGHT - 30f, true, "INTER" + secGearPot);

		new MotorFuse3(this, Measures.FIRST_WIDTH, Measures.FUSE_HEIGHT, true);
		new ThermalFuse3(this, Measures.THIRD_WIDTH, Measures.FUSE_HEIGHT);

		Mks mks = new Mks(this,Measures.MKS_WIDTH, Measures.MKS_HEIGHT);
		mks.control(this);

		new TwoGearEngine(this, Measures.SEC_WIDTH, Measures.RECEIVER_HEIGHT);
	}
}
