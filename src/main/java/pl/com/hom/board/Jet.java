package pl.com.hom.board;

import pl.com.hom.page.Page;

public class Jet extends Receiver{
	public Jet(Board parent, String name, String current1, String current2, String power1, String power2, String cable, String switchboard) {
		this.name     = name;
		this.current1 = current1;
		this.current2 = current2;
		this.power1   = power1;
		this.power2   = power2;
		this.cable    = cable;
		this.parent   = parent;

		parent.add(this);
	}

	public Page page() {
		System.out.println(steeringL + steeringR);
		if (steeringL != null && steeringR != null)
			return new pl.com.hom.page.BiDirectionJet(parent, this.steering1, this.steering2, this.steeringL, this.steeringR);
		else
			return new pl.com.hom.page.Jet(parent, this.steering1, this.steering2);
	}
}
