package pl.com.hom.page;

import java.util.ArrayList;

import pl.com.hom.board.Board;

public class Plc extends Page {
	private static final long serialVersionUID = 1L;

	private ArrayList<pl.com.hom.element.main.Plc> plcs;

	public Plc(Board board, int modules) {
		super(board);

		this.plcs = new ArrayList<pl.com.hom.element.main.Plc>();

		plcs.add(new pl.com.hom.element.main.Plc(this, "Cpu", -1f));
		for (int i = 0; i < modules; i++)
			plcs.add(new pl.com.hom.element.main.Plc(this, "Module", plcModuleX()));
			
	}

	public ArrayList<pl.com.hom.element.main.Plc> plcs() {
		return plcs;
	}
}
