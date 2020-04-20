package pl.com.hom.data;

import pl.com.hom.scheme.Page;

public abstract class Receiver {
	protected String name;
	protected String current1;
	protected String current2;
	protected String power1;
	protected String power2;
	protected String ster1;
	protected String ster2;
	protected String cable;
	protected String runMethod;

	protected Board parent;

	public String name() {
		return this.name;
	}

	public void setSters(String ster1, String ster2) {
		this.ster1 = ster1;
		this.ster2 = ster2;
	}

	public void setSter1(String ster1) {
		this.ster1 = ster1;
	}

	public void setSter2(String ster2) {
		this.ster2 = ster2;
	}

	public abstract Page page();

	@Override
	public String toString() {
		return "Engine [name=" + name + ", current1=" + current1 + ", current2=" + current2 + ", power1=" + power1
				+ ", power2=" + power2 + ", ster1=" + ster1 + ", ster2=" + ster2 + ", cable=" + cable + ", switchboard="
				+ ", runMethod=" + runMethod + "]";
	}
}
