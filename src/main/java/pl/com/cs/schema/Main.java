package pl.com.cs.schema;

public abstract class Main extends Drawable {
	public void updateSymbolByPageNr() {
		this.symbol().addPrefix(String.valueOf(this.page.nr()));
	}

	protected Symbol symbol(String g) {
		return new Symbol(g + page.nextValueOf(g));
	}
}
