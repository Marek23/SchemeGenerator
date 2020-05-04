package pl.com.hom.data;

import pl.com.hom.scheme.SoftstartPage;

public class Softstart extends Receiver{
	public Softstart(Board parent, String name, String current2, String power2, String cable, String switchboard) {
		this.name     = name;
		this.current2 = current2;
		this.power2   = power2;
		this.cable    = cable;
		this.parent   = parent;

		parent.add(this);
	}

	public SoftstartPage page() {
		return new SoftstartPage(parent, this.ster1, this.ster2);
	}
}
