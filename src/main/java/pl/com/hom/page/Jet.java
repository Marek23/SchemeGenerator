package pl.com.hom.page;

import pl.com.hom.board.Board;
import pl.com.hom.element.main.CurrentCoil;
import pl.com.hom.element.main.Mks;
import pl.com.hom.element.main.MotorFuse3;
import pl.com.hom.element.main.ThermalFuse3;
import pl.com.hom.element.receiver.TwoGearEngine;
import pl.com.hom.element.secondary.SingleContactor;

import static pl.com.hom.configuration.Widths.x;
import static pl.com.hom.configuration.Heights.y;

public class Jet extends Page {
	private static final long serialVersionUID = 1L;

	public Jet(Board board, String firstGearPot, String secGearPot) {
		super(board);

		CurrentCoil c1 = new CurrentCoil(this, coilX(), firstGearPot);
		CurrentCoil c2 = new CurrentCoil(this, coilX(), secGearPot);

		c1.firstGear(this);
		c2.secondJetGear(this);

		new SingleContactor(this, c1, c2);
		new SingleContactor(this, c2, c1);

		new MotorFuse3(this, x("3"), y("mainPhuse"), true);
		new ThermalFuse3(this, x("1"), y("mainPhuse"));

		new Mks(this).control(this);

		new TwoGearEngine(this);
	}
}
