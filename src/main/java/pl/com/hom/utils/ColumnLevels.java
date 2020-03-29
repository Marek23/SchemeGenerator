package pl.com.hom.utils;

import java.util.EnumMap;

import pl.com.hom.electric.Role;
import pl.com.hom.elements.ColumnRow;

public final class ColumnLevels {
	private static final Integer rowsAmount = 6;

	private static EnumMap<Role, Integer> levels;

	public static void initialize() {
		levels = new EnumMap<Role, Integer>(Role.class);

		levels.put(Role.UpLines, new Integer(0));

		levels.put(Role.Fuse, new Integer(1));

		levels.put(Role.Launcher,  new Integer(2));
		levels.put(Role.Steering,  new Integer(2));
		levels.put(Role.JetBridge, new Integer(2));

		levels.put(Role.Coil,       new Integer(3));
		levels.put(Role.MKS,        new Integer(3));
		levels.put(Role.Connection, new Integer(3));

		levels.put(Role.Conjugation, new Integer(4));

		levels.put(Role.DownLines, new Integer(5));
		
		levels.put(Role.Receiver, new Integer(6));
	}

	public static Integer getRowLevel(ColumnRow e) {
		return levels.get(e.getRole());
	}

	public static Integer lastRowLevel() {
		return rowsAmount;
	}
}
