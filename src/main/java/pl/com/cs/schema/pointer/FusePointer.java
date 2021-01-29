package pl.com.cs.schema.pointer;

import pl.com.cs.schema.FuseMain;
import pl.com.cs.schema.child.FuseChild;

import static pl.com.cs.config.Images.getImage;

public abstract class FusePointer extends Pointer{
	public FusePointer(FuseMain main, FuseChild child, String name) {
		this.name  = name;
		this.main  = main;
		this.child = child;
		this.page  = main.page();
		this.image = getImage(name, page);

		this.x = main.fuseWidthPos() + main.width();
		this.y = main.heightPos();

		this.width  = image.getWidth();
		this.height = image.getHeight();

		this.mainX = x + this.width()/2;
		this.mainY = this.y + this.height();

		this.mainPage  = main.page();
		this.childPage = child.page();

		page.add(this);
		page.add(this);
		main.addFusePointer(this);
	}
}
