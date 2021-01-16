package pl.com.cs.fps;

import pl.com.cs.schema.page.Page;

public abstract class Motor {
	protected String name;
	protected String current1;
	protected String current2;
	protected String power1;
	protected String power2;
	protected String steering1;
	protected String steering2;
	protected String steeringL;
	protected String steeringR;
	protected String fuse1;
	protected String fuse2;
	protected String cable;
	protected String runMethod;

	protected Fps parent;

	public String name() {
		return this.name;
	}

	public void steering1(String steering1) {
		this.steering1 = steering1;
	}

	public void steering2(String steering2) {
		this.steering2 = steering2;
	}

	public void steeringL(String steeringL) {
		this.steeringL = steeringL;
	}
	
	public void steeringR(String steeringR) {
		this.steeringR = steeringR;
	}

	public String steering1() {
		return this.steering1;
	}

	public String steering2() {
		return this.steering2;
	}

	public String steeringL() {
		return this.steeringL;
	}
	
	public String steeringR() {
		return this.steeringR;
	}
	public abstract Page page();

	public String fuse1() {
		return this.fuse1;
	}
	
	public String fuse2() {
		return this.fuse2;
	}
	
	public Fps fps() {
		return parent;
	}
}