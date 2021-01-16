package pl.com.cs.fps;

import java.util.Arrays;

import pl.com.cs.schema.page.MotorTwoGearBiDirectionPage;
import pl.com.cs.schema.page.MotorTwoGearPage;
import pl.com.cs.schema.page.Page;

public class MotorTwoGear extends Motor{
	public MotorTwoGear(Fps parent, String name, String current1, String current2, String power1, String power2, String fuse1, String fuse2, String cable, String switchboard) {
		this.name     = name;
		this.current1 = current1;
		this.current2 = current2;
		this.power1   = power1;
		this.power2   = power2;
		this.fuse1    = fuse1;
		this.fuse2    = fuse2;
		this.cable    = cable;
		this.parent   = parent;

		parent.add(this);
	}

	public Page page() {
		Page page;
		if (steeringL != null && steeringR != null)
			page = new MotorTwoGearBiDirectionPage(parent, this.steering1, this.steering2, this.steeringL, this.steeringR, this.fuse1, this.fuse2);
		else
			page = new MotorTwoGearPage(parent, this.steering1, this.steering2, this.fuse1, this.fuse2);

		page.addMotorDescription(Arrays.asList(name, current1 + "A / " + current2 + "A", power1 + "kW / " + power2 + "kW", cable));

		return page;
	}
}
