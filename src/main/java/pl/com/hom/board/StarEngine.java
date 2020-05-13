package pl.com.hom.board;

import pl.com.hom.page.Jet;

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
	public Jet page() {
		return new Jet(parent, this.steering1, this.steering2);
	}
}
