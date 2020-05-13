package pl.com.hom.page;

import pl.com.hom.board.Board;
import pl.com.hom.configuration.Potentials;
import pl.com.hom.connections.Point;
import pl.com.hom.connections.Potential;
import pl.com.hom.element.main.CurrentCoil;
import pl.com.hom.element.main.Mks;
import pl.com.hom.element.main.MotorFuse3;
import pl.com.hom.element.main.ThermalFuse3;
import pl.com.hom.element.receiver.TwoGearEngine;
import pl.com.hom.element.secondary.SingleContactor;
import pl.com.hom.printer.Printer;

import static pl.com.hom.configuration.Widths.x;
import static pl.com.hom.configuration.Heights.y;
import static pl.com.hom.configuration.Measures.scaled;

public class TwoGear extends Page {
	private static final long serialVersionUID = 1L;
	Printer printer;

	public TwoGear(Board board, String firstGearPot, String secGearPot) {
		super(board);

		Potential origin = Potentials.potential(secGearPot);
		Potentials.add(new Potential("INTER" + secGearPot, origin.width(), origin.height()));

		CurrentCoil coil1 = new CurrentCoil(this, coilX(), firstGearPot);
		CurrentCoil coil2 = new CurrentCoil(this, coilX(), secGearPot);
		CurrentCoil coil3 = new CurrentCoil(this, coilX(), "INTER" + secGearPot);

		new SingleContactor(this, coil2, coil1);
		new SingleContactor(this, coil1, coil2);

		coil1.firstGear(this);
		coil2.secondGear(this);
		coil3.secondGearBridge(this);

		Point.downLeft(this, coil3.widthPos() + scaled(100f), y("coil") - 30f, false, "INTER" + secGearPot);
		Point.upDownRight(this, coil2.widthPos() + scaled(100f), y("coil") - 30f, true, "INTER" + secGearPot);

		new MotorFuse3(this, x("1"), y("mainPhuse"), true);
		new ThermalFuse3(this, x("3"), y("mainPhuse"));

		Mks mks = new Mks(this);
		mks.control(this);

		new TwoGearEngine(this);
	}
}
