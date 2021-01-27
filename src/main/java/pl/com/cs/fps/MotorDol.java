package pl.com.cs.fps;

import pl.com.cs.schema.page.MotorDolPage;
import pl.com.cs.schema.page.Page;

public class MotorDol extends Executable{
	public MotorDol(Fps parent, String name, String current2, String power2, String fuse2, String cable, String switchboard) {
		this.name = name;
		this.current2 = current2;
		this.power2   = power2;
		this.fuse2    = fuse2;
		this.cable    = cable;

		this.runMethod = "DOL";

		parent.add(this);
	}

	// TODO
	public Page page() {
		return new MotorDolPage(this);
	}
}
