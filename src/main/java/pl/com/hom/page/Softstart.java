package pl.com.hom.page;

import pl.com.hom.board.Board;
import pl.com.hom.configuration.Measures;
import pl.com.hom.element.main.Ckf;
import pl.com.hom.element.main.Mks;
import pl.com.hom.element.main.ThreePhaseFuse;
import pl.com.hom.element.receiver.ThreePhaseEngine;
import pl.com.hom.elements.bridges.DownRightPhases;
import pl.com.hom.elements.bridges.UpDownLeftPhases;

public class Softstart extends Page {
	private static final long serialVersionUID = 1L;

	public Softstart(Board board, String ster1, String ster2) {
		super(board);

		new ThreePhaseFuse(this, Measures.THIRD_WIDTH, Measures.FUSE_HEIGHT);

		new UpDownLeftPhases(this, Measures.THIRD_WIDTH, Measures.CKF_BRIDGE_HEIGHT);
		new DownRightPhases(this, Measures.FIRST_WIDTH, Measures.CKF_BRIDGE_HEIGHT);

		new Ckf(this, Measures.FIRST_WIDTH, Measures.CKF_HEIGHT);

		new pl.com.hom.element.main.Softstart(this, Measures.THIRD_WIDTH, Measures.SOFTSTART_HEIGHT, "L10");

		Mks mks = new Mks(this,Measures.MKS_WIDTH, Measures.MKS_HEIGHT);
		mks.control(this);

		new ThreePhaseEngine(this, Measures.THIRD_WIDTH, Measures.RECEIVER_HEIGHT);
	}
}
