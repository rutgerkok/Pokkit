package nl.rutgerkok.pokkit.entity;

import org.bukkit.entity.EntityType;

public class PokkitEntityTranslator {

	//Only works with mobs registered.
	//Use AddEntityPacket to add other entities, like zombie.
	@SuppressWarnings("deprecation")
	public static String getEntity(EntityType et) {
		switch (et) {
		case ARROW:
		case SNOWBALL:
		case PAINTING:
		case CREEPER:
		case COW:
		case PIG:
		case RABBIT:
		case SHEEP:
		case WOLF:
		case OCELOT:
		case VILLAGER:
		case BOAT:
		case LIGHTNING:
		case CHICKEN:
			String name = et.getName();
			name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
			return name;
		case ENDER_PEARL:
			return "EnderPearl";
		case THROWN_EXP_BOTTLE:
			return "ThrownExpBottle";
		case PRIMED_TNT:
			return "PrimedTnt";
		case EXPERIENCE_ORB:
			return "XpOrb";
		case SPLASH_POTION:
			return "ThrownPotion";
		case FALLING_BLOCK:
			return "FallingSand";
		case PLAYER:
			return "Human";
		case MINECART:
		case MINECART_CHEST:
		case MINECART_COMMAND:
		case MINECART_FURNACE:
		case MINECART_HOPPER:
		case MINECART_MOB_SPAWNER:
		case MINECART_TNT:
			return "MinecartRideable";
		default:
			return "null";
		}
	}

}
