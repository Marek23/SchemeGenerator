package pl.com.hom.data;

import pl.com.hom.scheme.JetPage;
import pl.com.hom.scheme.Page;

public class DolEngine extends Receiver{
	public DolEngine(Board parent, String name, String current2, String power2, String cable, String switchboard) {
		this.name = name;
		this.current2 = current2;
		this.power2   = power2;
		this.cable    = cable;

		parent.add(this);
	}

	// TODO
	public Page page() {
		return new JetPage(parent, this.ster1, this.ster2);
	}
}
