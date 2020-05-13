package pl.com.hom.board;

import pl.com.hom.page.Page;

public abstract class Receiver {
	protected String name;
	protected String current1;
	protected String current2;
	protected String power1;
	protected String power2;
	protected String steering1;
	protected String steering2;
	protected String steeringL;
	protected String steeringR;
	protected String cable;
	protected String runMethod;

	protected Board parent;

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

	public abstract Page page();

	public Board board() {
		return parent;
	}
}
