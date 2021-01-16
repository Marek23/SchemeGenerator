package pl.com.cs.fps;

public abstract class Signal{
	private String function;
	protected Fps fps;
	protected String symbol;

	public Signal(Fps fps, String function) {
		this.fps = fps;
		this.function = function;
	}

	public String function() {
		return function;
	}

	public String symbol() {
		return this.symbol;
	}
}
