package pl.com.cs.schema;

import pl.com.cs.schema.main.*;
import pl.com.cs.schema.page.Page;

public class FuseFactory {
	public static FuseMain fuse(Page page, float x, float y, String fuse) {
		if (fuse.startsWith("GG")) {
			return new FuseSwitchMain(page, x, y, fuse);
		}
		else if (fuse.startsWith("C") || fuse.startsWith("B")) {
			return new FuseThermalMotor(page, x, y, fuse);
		}
		if (fuse.startsWith("MP")) {
			var directional = false;
			return new FuseMotorMain(page, x, y, fuse, directional);
		}
		throw new RuntimeException("Nierozpoznany typ zabezpieczenia " + fuse);
	} 

	public static FuseMain fuse(Page page, float x, float y, String fuse, boolean directional) {
		if (fuse.startsWith("GG")) {
			// TODO
			// return new FuseSwitchMain(page, x, y, fuse, directional);
		}
		else if (fuse.startsWith("C") || fuse.startsWith("B")) {
			// TODO
			// return new FuseThermalMotor(page, x, y, fuse, directional);
		}
		if (fuse.startsWith("MP")) {
			return new FuseMotorMain(page, x, y, fuse, directional);
		}
		throw new RuntimeException("Nierozpoznany typ zabezpieczenia " + fuse);
	}
}
