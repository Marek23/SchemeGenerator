package pl.com.hom.data;

import pl.com.hom.board.Board;

public class SapOut extends Signal{
	public SapOut(Board parent, String function){
		super(function);

		parent.addSapOutput(this);
	}
}
