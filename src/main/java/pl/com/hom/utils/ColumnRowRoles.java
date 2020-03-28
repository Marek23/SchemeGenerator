package pl.com.hom.utils;

import java.util.HashMap;

import pl.com.hom.elements.ColumnRow;

public final class ColumnRowRoles {
	private static final Integer rowsAmount = 6; 

	private static HashMap<Role, Integer> roleLevels;
	static {
		roleLevels.put(Role.UpLines, new Integer(0));

		roleLevels.put(Role.Fuse, new Integer(1));

		roleLevels.put(Role.Launcher,  new Integer(2));
		roleLevels.put(Role.Steering,  new Integer(2));
		roleLevels.put(Role.JetBridge, new Integer(2));

		roleLevels.put(Role.Coil,       new Integer(3));
		roleLevels.put(Role.MKS,        new Integer(3));
		roleLevels.put(Role.Connection, new Integer(3));

		roleLevels.put(Role.Conjugation, new Integer(4));

		roleLevels.put(Role.DownLines, new Integer(5));
		
		roleLevels.put(Role.Receiver, new Integer(6));
	}

	public static Integer getColumnRowLevel(ColumnRow e) {
		return roleLevels.get(e.getRole());
	}

	public static Integer lastLevel() {
		return rowsAmount;
	}
}
