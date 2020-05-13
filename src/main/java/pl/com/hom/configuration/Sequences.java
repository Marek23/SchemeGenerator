package pl.com.hom.configuration;

import java.util.HashMap;

public final class Sequences {
	private static HashMap<String, Integer> group = new HashMap<String, Integer>();

	public static int sequence(String g) {
		if (group.containsKey(g))
			group.put(g, group.get(g)+1);
		else
			group.put(g, 1);

		return group.get(g);
	}

	public static int sequence0(String g) {
		if (group.containsKey(g))
			group.put(g, group.get(g)+1);
		else
			group.put(g, 0);

		return group.get(g);
	}
}
