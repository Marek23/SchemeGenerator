package pl.com.cs.schema;

public class Symbol {
	protected String symbol;

	public Symbol (String s) {this.symbol = s;}

	public void addPrefix(String s) {
		this.symbol = s + this.symbol;
	}

	public String value() {
		return this.symbol;
	}
}
