package pl.com.cs.schema;

import pl.com.cs.schema.main.*;
import pl.com.cs.schema.page.Page;

public class FuseFactory {
	public static Fuse fuse(Page page, float x, float y, String fuse) {
		if (fuse.startsWith("GG")) {
			return new FuseSwitchMain(page, x, y, fuse);
		}
		else if (fuse.startsWith("C") || fuse.startsWith("B")) {
			return new FuseThermalMotor(page, x, y, fuse);
		}
		throw new RuntimeException("Nierozpoznany typ zabezpieczenia " + fuse);
	} 

	public static Fuse fuse(Page page, float x, float y, String fuse, boolean directional) {
		if (fuse.startsWith("MP")) {
			return new FuseMotorMain(page, x, y, fuse, directional);
		}
		throw new RuntimeException("Nierozpoznany typ zabezpieczenia " + fuse);
	}
}
