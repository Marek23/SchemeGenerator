package pl.com.hom.page;

import static pl.com.hom.configuration.Measures.scaled;

import pl.com.hom.board.Board;
import pl.com.hom.element.main.CurrentCoil;
import pl.com.hom.element.main.Mks;
import pl.com.hom.element.main.MotorFuse3;
import pl.com.hom.element.main.ThermalFuse3;
import pl.com.hom.element.receiver.TwoGearEngine;
import pl.com.hom.element.secondary.SingleContactor;
import pl.com.hom.elements.bridges.DownLeftPhases;
import pl.com.hom.elements.bridges.UpDownRightPhases;

import static pl.com.hom.configuration.Widths.x;
import static pl.com.hom.configuration.Heights.y;

public class BiDirectionTwoGear extends Page {
	private static final long serialVersionUID = 1L;

	public BiDirectionTwoGear(Board board, String first, String sec, String left, String right) {
		super(board);

		CurrentCoil c1 = new CurrentCoil(this, coilX(), first);
		CurrentCoil c2 = new CurrentCoil(this, coilX(), sec);
		CurrentCoil c3 = new CurrentCoil(this, coilX(), sec);

		c1.firstGear(this);
		c2.secondGearBridge(this);
		c3.secondDirGear(this);

		new SingleContactor(this, c1, c2);
		new SingleContactor(this, c2, c1);

		CurrentCoil cl = new CurrentCoil(this, coilX(), left);
		CurrentCoil cr = new CurrentCoil(this, coilX(), right);

		cl.left(this, x("1"), "softstart");
		cr.right(this, x("2"), "softstart");

		new SingleContactor(this, cl, cr);
		new SingleContactor(this, cr, cl);

		new UpDownRightPhases(this, x("1"), y("directionPhuse") - scaled(400f), true);
		new DownLeftPhases(this, x("3"), y("directionPhuse") - scaled(400f));

		new MotorFuse3(this, x("1"), y("directionPhuse"), false);
		new ThermalFuse3(this, x("1"), y("mainPhuse"));

		new Mks(this).control(this);

		new TwoGearEngine(this);
	}
}
