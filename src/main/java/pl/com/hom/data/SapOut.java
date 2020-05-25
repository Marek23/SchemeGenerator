package pl.com.hom.data;

import static pl.com.hom.configuration.Sequences.sequence;

import pl.com.hom.board.Board;

public class SapOut extends Signal{
	public SapOut(Board parent, String function){
		super(parent, function);

		this.symbol = String.valueOf(sequence(super.board.name() + "SAPOUT"));

		parent.addSapOutput(this);
	}
}
