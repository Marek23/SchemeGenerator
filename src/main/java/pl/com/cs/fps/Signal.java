package pl.com.cs.fps;

import pl.com.cs.schema.Symbol;

public abstract class Signal {
	private String function;
	protected Fps fps;
	protected Symbol symbol;

	public Signal(Fps fps, String function) {
		this.fps = fps;
		this.function = function;
	}

	public String function() {
		return function;
	}

	public Symbol symbol() {
		return this.symbol;
	}
}
