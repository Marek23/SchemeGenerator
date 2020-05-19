package pl.com.hom.page;

import pl.com.hom.board.Board;

public class PlcCpu extends Page {
	private static final long serialVersionUID = 1L;

	private pl.com.hom.element.main.Plc cpu;

	public PlcCpu(Board board) {
		super(board);

		cpu = new pl.com.hom.element.main.Plc(this, "Cpu", -1f);			
	}

	public pl.com.hom.element.main.Plc cpu() {
		return cpu;
	}
}
