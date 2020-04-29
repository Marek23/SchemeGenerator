package pl.com.hom.connections;

import pl.com.hom.configuration.Measures;

public class Potential {
	private String potential;
	private float  width;
	private float  height;

	public Potential(String potential, float width, float height) {
		this.potential = potential;
		this.width     = width  * Measures.SCALE;
		this.height    = height * Measures.SCALE;
	}

	public String name() {
		return potential;
	}

	public String prettyName() {
		return potential.replaceAll("MAIN", "").replaceAll("GROUND", "");
	}

	public float width() {
		if (this.width < 0)
			throw new RuntimeException("Wrong acces to potential: " + this.potential +" width");

		return width;
	}

	public float height() {
		if (this.height < 0)
			throw new RuntimeException("Wrong acces to potential: " + potential + " height");

		return height;
	}
}
