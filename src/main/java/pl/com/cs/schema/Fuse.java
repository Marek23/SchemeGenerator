package pl.com.cs.schema;

public abstract class Fuse extends Drawable{
	protected String fuse;
	protected float  fuseX;
	protected float  fuseY;

	public float fuseWidthPos() {
		return fuseX;
	}

	public float fuseHeightPos() {
		return fuseY;
	}

	public String fuse() {
		return fuse;
	}
}
