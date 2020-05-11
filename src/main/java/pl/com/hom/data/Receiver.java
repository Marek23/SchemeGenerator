package pl.com.hom.data;

import pl.com.hom.page.Page;

public abstract class Receiver {
	protected String name;
	protected String current1;
	protected String current2;
	protected String power1;
	protected String power2;
	protected String steering1;
	protected String steering2;
	protected String cable;
	protected String runMethod;

	protected Board parent;

	public String name() {
		return this.name;
	}

	public void steering1(String steering1) {
		this.steering1 = steering1;
	}

	public void steering2(String steering2) {
		this.steering2 = steering2;
	}

	public abstract Page page();

	@Override
	public String toString() {
		return "Engine [name=" + name + ", current1=" + current1 + ", current2=" + current2 + ", power1=" + power1
				+ ", power2=" + power2 + ", ster1=" + steering1 + ", ster2=" + steering2 + ", cable=" + cable + ", switchboard="
				+ ", runMethod=" + runMethod + "]";
	}

	public Board board() {
		return parent;
	}
}
