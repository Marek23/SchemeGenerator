package pl.com.hom.element.pointer;

import pl.com.hom.element.Element;

import static pl.com.hom.configuration.Measures.scaled;
import static pl.com.hom.configuration.Resource.getImage;

public class ContactCkfPointer extends Pointer {
	public ContactCkfPointer(Element secondary, Element main) {
		this.name   = "ContactCkfPointer";
		this.parent = secondary;
		this.page   = main.page();
		this.image = getImage(name, page);

		this.x = main.widthPos();
		this.y = main.pointerHeightPos();

		this.width  = image.getWidth();
		this.height = image.getHeight();

		float xMargin = scaled(60f);
		float yMargin = scaled(60f);

		this.parentX = x - xMargin;
		this.parentY = this.y + yMargin;
		this.parentPage = secondary.page().nr();

		page.add(this);
		main.add(this);
	}
}
