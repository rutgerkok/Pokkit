package nl.rutgerkok.pokkit.entity;

import org.bukkit.entity.EntityType;

public class PokkitEntityTranslator {

	//Only works with mobs that have an egg.
	//Use AddEntityPacket to add other entities, like zombie.
	public static int getEntityNetworkID(EntityType et) {
		switch (et) {
		case BOAT:
			return 90;
		case FALLING_BLOCK:
			return 66;
		case MINECART:
			return 84;
		//////
		case BAT:
			return 19;
		case BLAZE:
			return 43;
		case CAVE_SPIDER:
			return 40;
		case CHICKEN:
			return 10;
		case COW:
			return 11;
		case CREEPER:
			return 33;
		case ENDERMAN:
			return 38;
		case GHAST:
			return 41;
		case IRON_GOLEM:
			return 20;
		case MAGMA_CUBE:
			return 42;
		case MUSHROOM_COW:
			return 16;
		case OCELOT:
			return 22;
		case PIG:
			return 12;
		case PIG_ZOMBIE:
			return 36;
		case PRIMED_TNT:
			return 65;
		case SHEEP:
			return 13;
		case SILVERFISH:
			return 39;
		case SKELETON:
			return 34;
		case SLIME:
			return 37;
		case SNOWMAN:
			return 21;
		case SPIDER:
			return 35;
		case SQUID:
			return 17;
		case VILLAGER:
			return 15;
		case WOLF:
			return 14;
		case ZOMBIE:
			return 32;
		default:
			return -1;
		}
	}

}
