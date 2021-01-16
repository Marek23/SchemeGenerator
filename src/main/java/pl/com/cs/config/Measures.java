package pl.com.cs.config;

public final class Measures {
	public final static float scale = 0.14f;

	public static float scaled(float value) {
		return value * scale;
	}

	public static float scale() {
		return scale;
	}
}
