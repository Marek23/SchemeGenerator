package pl.com.hom.page;

import pl.com.hom.board.Board;
import pl.com.hom.element.main.Ckf;
import pl.com.hom.element.main.CurrentCoil;
import pl.com.hom.element.main.Mks;
import pl.com.hom.element.main.Softstart;
import pl.com.hom.element.main.ThreePhaseFuse;
import pl.com.hom.element.receiver.ThreePhaseEngine;
import pl.com.hom.element.secondary.SingleContactor;
import pl.com.hom.elements.bridges.DownRightPhases;
import pl.com.hom.elements.bridges.UpDownLeftPhases;

import static pl.com.hom.configuration.Widths.x;
import static pl.com.hom.configuration.Heights.y;
import static pl.com.hom.configuration.Measures.scaled;

public class BiDirectionSoftstart extends Page {
	private static final long serialVersionUID = 1L;

	public BiDirectionSoftstart(Board board, String ster1, String ster2, String sterL, String sterR) {
		super(board);

		new UpDownLeftPhases(this, x("softstart"), y("softstart") - y("spaceUp") - scaled(300f));
		new DownRightPhases(this, x("1"), y("softstart") - y("spaceUp") - scaled(300f));

		new ThreePhaseFuse(this, x("3"), y("mainPhuse"));

		new Ckf(this, x("1"), y("ckf"));

		CurrentCoil cl = new CurrentCoil(this, coilX(), sterL);
		CurrentCoil cr = new CurrentCoil(this, coilX(), sterR);

		cl.left(this,  x("softstart"), "direction");
		cr.right(this, x("softstart") + x("colSpace"), "direction");

		new SingleContactor(this, cl, cr);
		new SingleContactor(this, cr, cl);

		new Softstart(this, "L10");

		new Mks(this).control(this);

		new ThreePhaseEngine(this);
	}
}
