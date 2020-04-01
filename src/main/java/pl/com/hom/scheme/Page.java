package pl.com.hom.scheme;

import java.util.ArrayList;
import java.util.List;

public class Page {
	private List<Column> columns;
	private float x;

	public Page() {
		this.columns = new ArrayList<Column>();
		this.x = 0f;
	}

	public float getWidth() {
		float out = 0f;
		for (Column c : columns)
			out += c.getWidth();

		return out;
	}

	public float getWidthPos() {
		return x;
	}
}
