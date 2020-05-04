package pl.com.hom.data;

import pl.com.hom.scheme.BiDirectionSoftstartPage;

public class BiDirectionSoftstart extends Receiver{
	public BiDirectionSoftstart(Board parent, String name, String current2, String power2, String cable, String switchboard) {
		this.name     = name;
		this.current2 = current2;
		this.power2   = power2;
		this.cable    = cable;
		this.parent   = parent;

		parent.add(this);
	}

	public BiDirectionSoftstartPage page() {
		return new BiDirectionSoftstartPage(parent, this.ster1, this.ster2);
	}
}
