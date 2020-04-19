package pl.com.hom.data;

public abstract class Engine {
	protected String name;
	protected String current1;
	protected String current2;
	protected String power1;
	protected String power2;
	protected String ster1;
	protected String ster2;
	protected String cable;
	protected String switchboard;
	protected String runMethod;

	public String name() {
		return this.name + " " + this.switchboard;
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
}
