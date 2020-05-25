package pl.com.hom.page;

import java.util.ArrayList;

import pl.com.hom.board.Board;
import pl.com.hom.element.secondary.PlcSignal;

import static pl.com.hom.configuration.Heights.y;

public class SapOut extends Page {
	private static final long serialVersionUID = 1L;

	public SapOut(Board board, ArrayList<pl.com.hom.data.SapOut> sapOutputs) {
		super(board);

		// ommiting first input positions
		for(int i = 0; i < 5; i++)
			plcSignalX();

		for(pl.com.hom.data.SapOut out: sapOutputs) {
			float widthPos = nextSapOutput();
			this.addSapOut(out);
			new PlcSignal(this, board.nextOutput(), "Y", out.function(), plcSignalX(), y("plcSignal"), false)
				.addSapOutCoil(widthPos);

			new pl.com.hom.element.receiver.SapOut(this, out, widthPos);
		}
	}
}
