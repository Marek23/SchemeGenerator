package pl.com.hom.element.pointer;

import static pl.com.hom.configuration.Resource.getImage;

import pl.com.hom.configuration.Measures;
import pl.com.hom.element.Element;

public class ContactCkfPointer extends Pointer {
	public ContactCkfPointer(Element parent, float x, float y) {
		this.name   = "ContactCkfPointer";
		this.parent = parent;
		this.page   = parent.page();
		this.image = getImage(name, page);

		this.x = x;
		this.y = y;

		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;

		this.parentX = x - Measures.POINTER_PARENT_NR_X_MARGIN;
		this.parentY = this.y + Measures.POINTER_PARENT_NR_Y_MARGIN;
		this.parentPage = parent.page().nr();

		this.page.add(this);
	}
}
