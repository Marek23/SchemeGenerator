package pl.com.hom.configuration;

import java.util.HashMap;

public final class TerminalGroups {
	private static HashMap<String, Integer> group;
	
	public static void initialize() {
		group = new HashMap<String, Integer>();

		group.put("X2", new Integer(0));
		group.put("XL", new Integer(0));
		group.put("XS", new Integer(0));
	}

	public static int getNewNumber(String g) {
		if (!group.containsKey(g))
			throw new RuntimeException("Group: " + g + " not included.");

		group.put(g,group.get(g) + 1);
		return group.get(g);
	}
}
