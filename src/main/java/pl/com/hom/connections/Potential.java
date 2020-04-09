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

	public String getName() {
		return potential.substring(0, 6);
	}

	public String getFullName() {
		return potential;
	}

	public float getWidth() {
		if (this.width < 0)
			throw new RuntimeException("Wrong acces to potential: " + this.potential +" width");

		return width;
	}

	public float getHeight() {
		if (this.height < 0)
			throw new RuntimeException("Wrong acces to potential width");

		return height;
	}
}
