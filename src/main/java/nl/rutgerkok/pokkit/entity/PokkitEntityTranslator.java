package nl.rutgerkok.pokkit.entity;

import org.bukkit.entity.EntityType;

public class PokkitEntityTranslator {

	//Only works with mobs registered.
	//Use AddEntityPacket to add other entities, like zombie.
	@SuppressWarnings("deprecation")
	public static String getEntity(EntityType et) {
		switch (et) {
		case ARROW:
		case PRIMED_TNT:
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
		case THROWN_EXP_BOTTLE:
		case BOAT:
		case LIGHTNING:
		case CHICKEN:
			return et.getName().replace("_", "");
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
