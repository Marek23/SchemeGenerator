package pl.com.cs.schema.pointer;

import pl.com.cs.schema.Drawable;

public class MksChildPointer extends Pointer {
	public MksChildPointer(Drawable main, Drawable child) {
		super(main, child, "ContactMksPointer");
	}
}
