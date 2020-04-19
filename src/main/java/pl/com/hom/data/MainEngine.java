package pl.com.hom.data;

public class MainEngine extends Engine{
	public MainEngine(Board parent, String name, String current2, String power2, String cable, String switchboard, String runMethod) {
		this.name = name;
		this.current2 = current2;
		this.power2   = power2;
		this.cable    = cable;

		this.switchboard = switchboard;
		this.runMethod   = runMethod;

		parent.add(this);
	}
}
