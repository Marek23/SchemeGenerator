package pl.com.hom.scheme;

import pl.com.hom.configuration.Measures;
import pl.com.hom.data.Board;
import pl.com.hom.element.main.CurrentCoil;
import pl.com.hom.element.main.Mks;
import pl.com.hom.element.main.MotorFuse3;
import pl.com.hom.element.main.ThermalFuse3;
import pl.com.hom.element.receiver.TwoGearEngine;
import pl.com.hom.elements.bridges.DownLeftPhases;
import pl.com.hom.elements.bridges.UpDownRightPhases;
import pl.com.hom.printer.Printer;

public class BiDirectionTwoGearPage extends Page {
	private static final long serialVersionUID = 1L;
	Printer printer;

	public BiDirectionTwoGearPage(Board board, String first, String sec, String left, String right) {
		super(board);

		CurrentCoil firstC = new CurrentCoil(this, coilX(), Measures.COIL_HEIGHT, first);
		firstC.firstGear(this);

		CurrentCoil secC = new CurrentCoil(this, coilX(), Measures.COIL_HEIGHT, sec);
		secC.secondGear(this);

		CurrentCoil leftC = new CurrentCoil(this, coilX(), Measures.COIL_HEIGHT, left);
		leftC.biGearLeft(this);

		CurrentCoil rightC = new CurrentCoil(this, coilX(), Measures.COIL_HEIGHT, right);
		rightC.biGearRight(this);

		new UpDownRightPhases(this, Measures.FIRST_WIDTH, Measures.FUSE_DIR_HEIGHT - 400f * Measures.SCALE, true);
		new DownLeftPhases(this, Measures.THIRD_WIDTH, Measures.FUSE_DIR_HEIGHT - 400f * Measures.SCALE);

		new MotorFuse3(this, Measures.FIRST_WIDTH, Measures.FUSE_DIR_HEIGHT, false);
		new ThermalFuse3(this, Measures.FIRST_WIDTH, Measures.FUSE_HEIGHT);

		Mks mks = new Mks(this, Measures.MKS_WIDTH, Measures.MKS_HEIGHT);
		mks.control(this);

		new TwoGearEngine(this, Measures.SEC_WIDTH, Measures.RECEIVER_HEIGHT);
	}
}
