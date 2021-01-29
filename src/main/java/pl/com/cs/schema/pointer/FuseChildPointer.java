package pl.com.cs.schema.pointer;

import pl.com.cs.schema.FuseMain;
import pl.com.cs.schema.child.FuseChild;

public class FuseChildPointer extends FusePointer {
	public FuseChildPointer(FuseMain main, FuseChild child) {
		super(main, child, "FuseChildPointer");
	}
}
