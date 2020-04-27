package pl.com.hom.data;

import pl.com.hom.scheme.JetPage;

public class StarEngine extends Receiver{
	public StarEngine(Board parent, String name, String current2, String power2, String cable, String switchboard, String runMethod) {
		this.name = name;
		this.current2 = current2;
		this.power2   = power2;
		this.cable    = cable;

		this.runMethod   = runMethod;

		parent.add(this);
	}

	// TODO
	public JetPage page() {
		return new JetPage(parent, this.ster1, this.ster2);
	}
}
