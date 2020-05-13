package pl.com.hom.connections;

import static pl.com.hom.configuration.Measures.scaled;

public class Potential {
	private String potential;
	private float  width;
	private float  height;

	public Potential(String potential, float width, float height) {
		this.potential = potential;
		this.width     = scaled(width);
		this.height    = scaled(height);
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
