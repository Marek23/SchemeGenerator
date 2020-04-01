package pl.com.hom.electric;

import pl.com.hom.configuration.Measures;

public class Potential {
	private String name;
	private float  x;
	private float  y;
	private float  Y;

	public Potential(String name, float width, float height, float mainLineHeight) {
		this.name = name;
		this.x    = width  * Measures.SCALE;
		this.y    = height * Measures.SCALE;

		this.Y    = mainLineHeight * Measures.SCALE;
	}

	public String getName() {
		return name;
	}

	public float getWidth() {
		return x;
	}

	public float getHeight() {
		return y;
	}

	public float getMainLineHeight() {
		return Y;
	}
}
