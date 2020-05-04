package pl.com.hom.data;

import pl.com.hom.scheme.BiDirectionTwoGearPage;

public class BiDirectionTwoGearEngine extends Receiver{
	public BiDirectionTwoGearEngine(Board parent, String name, String current1, String current2, String power1, String power2, String cable, String switchboard) {
		this.name     = name;
		this.current1 = current1;
		this.current2 = current2;
		this.power1   = power1;
		this.power2   = power2;
		this.cable    = cable;
		this.parent   = parent;

		parent.add(this);
	}

	public BiDirectionTwoGearPage page() {
		return new BiDirectionTwoGearPage(parent, this.ster1, this.ster2, this.ster1, this.ster2);
	}
}
