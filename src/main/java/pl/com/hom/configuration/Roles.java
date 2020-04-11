package pl.com.hom.configuration;

import java.util.HashMap;

public final class Roles {
	private static HashMap<String, Role> roles;

	public static void initialize() {
		roles = new HashMap<String, Role>();

		add(new Role("Fuse", 1));
		add(new Role("ThreePhaseFuse", 1));

		add(new Role("AboveContactorBridge", 2));
		
		add(new Role("Contactor", 3));
		add(new Role("JetBridgeContactor", 3));
		add(new Role("FirstGearContactor", 3));
		add(new Role("SecGearContactor", 3));
		add(new Role("Steering", 3));
		
		add(new Role("ToJetBridge", 4));
		add(new Role("UpRightPhasesBridge", 4));
		add(new Role("Mks", 4));
		add(new Role("ToMksBridge", 4));

		add(new Role("Coil", 5));
		add(new Role("CoilContactor", 5));

		add(new Role("Terminals", 6));

		add(new Role("UpRightPhasesJet", 7));
		add(new Role("UpLeftPhasesJet", 7));
		add(new Role("JetEngine", 7));
		add(new Role("Receiver", 7));
		add(new Role("ThreePhaseEngine", 7));
	}

	public static void add(Role role) {
		roles.put(role.name(), role);
	}

	public static Role role(String name) {
		return roles.get(name);
	}

	public static int rolesNumber() {
		return roles.size();
	}
}

