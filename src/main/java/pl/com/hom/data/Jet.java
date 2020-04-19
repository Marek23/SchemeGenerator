package pl.com.hom.data;

public class Jet extends Engine{
	public Jet(Board parent, String name, String current1, String current2, String power1, String power2, String cable, String switchboard) {
		this.name = name;
		this.current1 = current1;
		this.current2 = current2;
		this.power1   = power1;
		this.power2   = power2;
		this.cable    = cable;
		
		this.switchboard = switchboard;

		parent.add(this);
	}
}
