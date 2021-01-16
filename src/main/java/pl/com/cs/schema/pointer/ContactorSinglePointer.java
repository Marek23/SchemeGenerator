package pl.com.cs.schema.pointer;

import pl.com.cs.schema.Drawable;

public class ContactorSinglePointer extends Pointer {
	public ContactorSinglePointer(Drawable main, Drawable child) {
		super(main, child, "SingleContactorPointer");
	}
}
