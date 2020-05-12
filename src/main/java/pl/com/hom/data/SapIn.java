package pl.com.hom.data;

import pl.com.hom.board.Board;

public class SapIn extends Signal{
	public SapIn(Board parent, String function){
		super(function);

		parent.addSapInput(this);
	}
}
