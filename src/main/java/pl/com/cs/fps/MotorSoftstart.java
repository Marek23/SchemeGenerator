package pl.com.cs.fps;

import java.util.Arrays;

import pl.com.cs.schema.page.MotorSoftstartBiDirectionPage;
import pl.com.cs.schema.page.MotorSoftstartPage;
import pl.com.cs.schema.page.Page;

public class MotorSoftstart extends Executable {
	public MotorSoftstart(Fps parent, String name, String current2, String power2, String fuse2, String cable, String switchboard) {
		this.name     = name;
		this.current2 = current2;
		this.power2   = power2;
		this.fuse2    = fuse2;
		this.cable    = cable;
		this.parent   = parent;

		this.runMethod = "SOFTSTART";

		parent.add(this);
	}

	public Page page() {
		Page page;
		if (steeringL != null && steeringR != null)
			page = new MotorSoftstartBiDirectionPage(parent, this.steering1, this.steering2, this.steeringL, this.steeringR, this.fuse2);
		else
			page = new MotorSoftstartPage(parent, this.steering1, this.steering2, this.fuse2);

		page.addMotorDescription(Arrays.asList(name, current2 + "A", power2 + "kW", cable));

		return page;
	}
}
