package pl.com.hom.element.pointer;

import static pl.com.hom.configuration.Resource.getImage;

import pl.com.hom.configuration.Measures;
import pl.com.hom.element.Element;

public class ContactMksPointer extends Pointer {
	public ContactMksPointer(Element secondary, Element main) {
		this.name   = "ContactMksPointer";
		this.parent = secondary;
		this.page   = main.page();
		this.image = getImage(name, page);

		this.x = main.widthPos();
		this.y = main.pointerHeightPos();

		this.width  = image.getWidth()  * Measures.SCALE;
		this.height = image.getHeight() * Measures.SCALE;

		this.parentX = x - Measures.POINTER_PARENT_NR_X_MARGIN;
		this.parentY = this.y + Measures.POINTER_PARENT_NR_Y_MARGIN;
		this.parentPage = secondary.page().nr();

		page.add(this);
		main.add(this);
	}
}
