package pl.com.hom.data;

import pl.com.hom.board.Board;

public abstract class Signal{
	private String function;
	protected Board board;
	protected String symbol;

	public Signal(Board board, String function) {
		this.board = board;
		this.function = function;
	}

	public String function() {
		return function;
	}

	public String symbol() {
		return this.symbol;
	}
}
