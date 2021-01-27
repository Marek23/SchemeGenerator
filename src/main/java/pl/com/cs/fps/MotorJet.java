package pl.com.cs.fps;

import java.util.Arrays;

import pl.com.cs.schema.page.MotorJetBiDirectionPage;
import pl.com.cs.schema.page.MotorJetPage;
import pl.com.cs.schema.page.Page;

public class MotorJet extends Executable{
	public MotorJet(Fps fps, String name, String current1, String current2, String power1, String power2, String fuse1, String fuse2, String cable, String switchboard) {
		this.name     = name;
		this.current1 = current1;
		this.current2 = current2;
		this.power1   = power1;
		this.power2   = power2;
		this.fuse1    = fuse1;
		this.fuse2    = fuse2;
		this.cable    = cable;
		this.parent   = fps;

		this.runMethod = "TWOGEAR";

		parent.add(this);
	}

	public Page page() {
		Page page;

		if (steeringL != null && steeringR != null)
			page =  new MotorJetBiDirectionPage(this);
		else
			page = new MotorJetPage(this);

		page.addMotorDescription(Arrays.asList(name, current1 + "A / " + current2 + "A", power1 + "kW / " + power2 + "kW", cable));

		return page;
	}
}
