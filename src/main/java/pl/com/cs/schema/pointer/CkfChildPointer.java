package pl.com.cs.schema.pointer;

import pl.com.cs.schema.Drawable;

public class CkfChildPointer extends Pointer {
	public CkfChildPointer(Drawable main, Drawable child) {
		super(child, main, "ContactCkfPointer");
	}
}
