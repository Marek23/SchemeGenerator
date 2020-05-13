package pl.com.hom.board;

import pl.com.hom.page.Page;

public class Softstart extends Receiver{
	public Softstart(Board parent, String name, String current2, String power2, String cable, String switchboard) {
		this.name     = name;
		this.current2 = current2;
		this.power2   = power2;
		this.cable    = cable;
		this.parent   = parent;

		parent.add(this);
	}

	public Page page() {
		if (steeringL != null && steeringR != null)
			return new pl.com.hom.page.BiDirectionSoftstart(parent, this.steering1, this.steering2, this.steeringL, this.steeringR);
		else
			return new pl.com.hom.page.Softstart(parent, this.steering1, this.steering2);
	}
}
