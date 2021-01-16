package pl.com.cs.fps;

import java.util.Arrays;

import pl.com.cs.schema.page.MotorStarPage;
import pl.com.cs.schema.page.Page;

public class MotorStar extends Motor {
	public MotorStar(Fps parent, String name, String current2, String power2, String fuse2, String cable, String switchboard, String runMethod) {
		this.name = name;
		this.current2 = current2;
		this.power2   = power2;
		this.fuse2    = fuse2;
		this.cable    = cable;

		this.runMethod   = runMethod;

		parent.add(this);
	}

	public Page page() {
		Page page;
		page = new MotorStarPage(parent, this.steering1, this.steering2, this.fuse2);

		page.addMotorDescription(Arrays.asList(name, current2 + "A", power2 + "kW", cable));
		
		return page;
	}
}
