package pl.com.hom.page;

import java.util.ArrayList;

import pl.com.hom.board.Board;

public class PlcModules extends Page {
	private static final long serialVersionUID = 1L;

	private ArrayList<pl.com.hom.element.main.Plc> modules;

	public PlcModules(Board board, int amount) {
		super(board);

		this.modules = new ArrayList<pl.com.hom.element.main.Plc>();

		for (int i = 0; i < amount; i++)
			modules.add(new pl.com.hom.element.main.Plc(this, "Module", plcModuleX()));
	}

	public ArrayList<pl.com.hom.element.main.Plc> modules() {
		return modules;
	}
}
