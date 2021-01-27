package pl.com.cs.fps;

import java.util.Arrays;

import pl.com.cs.schema.page.MotorStarPage;
import pl.com.cs.schema.page.Page;

public class MotorStar extends Executable {
	public MotorStar(Fps parent, String name, String current2, String power2, String fuse2, String cable, String switchboard) {
		this.name = name;
		this.current2 = current2;
		this.power2   = power2;
		this.fuse2    = fuse2;
		this.cable    = cable;

		this.runMethod = "STAR";

		parent.add(this);
	}

	public Page page() {
		Page page;
		page = new MotorStarPage(parent, this.steering1, this.steering2, this.fuse2);

		page.addMotorDescription(Arrays.asList(name, current2 + "A", power2 + "kW", cable));
		
		return page;
	}
}
