package pl.com.hom.data;

import pl.com.hom.page.Jet;

public class TO extends Receiver{
	public TO(Board parent, String name, String current1, String current2, String power1, String power2, String cable, String switchboard) {
		this.name = name;
		this.current2 = current2;
		this.power2   = power2;
		this.cable    = cable;

		parent.add(this);
	}

	// TODO
	public Jet page() {
		return new Jet(parent, this.steering1, this.steering2);
	}
}
