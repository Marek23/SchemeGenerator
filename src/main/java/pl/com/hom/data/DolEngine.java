package pl.com.hom.data;

public class DolEngine extends Receiver{
	public DolEngine(Board parent, String name, String current2, String power2, String cable, String switchboard) {
		this.name = name;
		this.current2 = current2;
		this.power2   = power2;
		this.cable    = cable;

		parent.add(this);
	}

	// TODO
	public void draw() {}
}
