package pl.com.hom.data;

import pl.com.hom.board.Board;

import static pl.com.hom.configuration.Sequences.sequence;

public class SapIn extends Signal{
	public SapIn(Board parent, String function){
		super(parent, function);

		this.symbol = String.valueOf(sequence(super.board.name() + "SAPIN"));

		parent.addSapInput(this);
	}
}
