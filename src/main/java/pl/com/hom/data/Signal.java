package pl.com.hom.data;

public abstract class Signal{
	String function;

	public Signal(String function) {
		this.function = function;
	}

	public String function() {
		return function;
	}
}
