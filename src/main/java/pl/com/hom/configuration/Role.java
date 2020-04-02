package pl.com.hom.configuration;

public class Role {
	private String name;
	private int    level;

	public Role(String name, int level) {
		this.name  = name;
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public int getLevel() {
		return level;
	}
}
