package pl.com.hom.data;

public class Jet {
	private String name;
	private String current1;
	private String current2;
	private String power1;
	private String power2;
	private String ster1;
	private String ster2;
	private String cable;
	private String switchboard;

	public Jet(String name, String current1, String current2, String power1, String power2, String cable, String switchboard) {
		this.name = name;
		this.current1 = current1;
		this.current2 = current2;
		this.power1   = power1;
		this.power2   = power2;
		this.cable    = cable;
		
		this.switchboard = switchboard;
	}

	public String name() {
		return this.name;
	}
}
