package nl.rutgerkok.pokkit;

import org.bukkit.Location;
import org.bukkit.Sound;

/**
 * Sound conversion. Many sounds from PC cannot be played in PE, as far as I
 * know.
 *
 */
public final class PokkitSound {

	public static cn.nukkit.level.Sound toNukkit(Location location, Sound sound, float pitch) {

		switch (sound) {
		case AMBIENT_CAVE:
			break;
		case BLOCK_ANVIL_BREAK:
			return cn.nukkit.level.Sound.RANDOM_ANVIL_BREAK;
		case BLOCK_ANVIL_DESTROY:
			break;
		case BLOCK_ANVIL_FALL:
			break;
		case BLOCK_ANVIL_HIT:
			break;
		case BLOCK_ANVIL_LAND:
			return cn.nukkit.level.Sound.RANDOM_ANVIL_LAND;
		case BLOCK_ANVIL_PLACE:
			break;
		case BLOCK_ANVIL_STEP:
			break;
		case BLOCK_ANVIL_USE:
			return cn.nukkit.level.Sound.RANDOM_ANVIL_USE;
		case BLOCK_BREWING_STAND_BREW:
			break;
		case BLOCK_CHEST_CLOSE:
			return cn.nukkit.level.Sound.RANDOM_CHESTCLOSED;
		case BLOCK_CHEST_LOCKED:
			break;
		case BLOCK_CHEST_OPEN:
			return cn.nukkit.level.Sound.RANDOM_CHESTOPEN;
		case BLOCK_CHORUS_FLOWER_DEATH:
			return cn.nukkit.level.Sound.BLOCK_CHORUSFLOWER_DEATH;
		case BLOCK_CHORUS_FLOWER_GROW:
			return cn.nukkit.level.Sound.BLOCK_CHORUSFLOWER_GROW;
		case BLOCK_COMPARATOR_CLICK:
			break;
		case BLOCK_DISPENSER_DISPENSE:
			break;
		case BLOCK_DISPENSER_FAIL:
			break;
		case BLOCK_DISPENSER_LAUNCH:
			break;
		case BLOCK_ENCHANTMENT_TABLE_USE:
			break;
		case BLOCK_END_GATEWAY_SPAWN:
			break;
		case BLOCK_FENCE_GATE_CLOSE:
			break;
		case BLOCK_FENCE_GATE_OPEN:
			break;
		case BLOCK_FIRE_AMBIENT:
			break;
		case BLOCK_FIRE_EXTINGUISH:
			break;
		case BLOCK_FURNACE_FIRE_CRACKLE:
			break;
		case BLOCK_GLASS_BREAK:
			break;
		case BLOCK_GLASS_FALL:
			break;
		case BLOCK_GLASS_HIT:
			break;
		case BLOCK_GLASS_PLACE:
			break;
		case BLOCK_GLASS_STEP:
			break;
		case BLOCK_GRASS_BREAK:
			break;
		case BLOCK_GRASS_FALL:
			break;
		case BLOCK_GRASS_HIT:
			break;
		case BLOCK_GRASS_PLACE:
			break;
		case BLOCK_GRASS_STEP:
			break;
		case BLOCK_GRAVEL_BREAK:
			break;
		case BLOCK_GRAVEL_FALL:
			break;
		case BLOCK_GRAVEL_HIT:
			break;
		case BLOCK_GRAVEL_PLACE:
			break;
		case BLOCK_GRAVEL_STEP:
			break;
		case BLOCK_IRON_DOOR_CLOSE:
			break;
		case BLOCK_IRON_DOOR_OPEN:
			break;
		case BLOCK_IRON_TRAPDOOR_CLOSE:
			break;
		case BLOCK_IRON_TRAPDOOR_OPEN:
			break;
		case BLOCK_LADDER_BREAK:
			break;
		case BLOCK_LADDER_FALL:
			break;
		case BLOCK_LADDER_HIT:
			break;
		case BLOCK_LADDER_PLACE:
			break;
		case BLOCK_LADDER_STEP:
			break;
		case BLOCK_LAVA_AMBIENT:
			break;
		case BLOCK_LAVA_EXTINGUISH:
			break;
		case BLOCK_LAVA_POP:
			break;
		case BLOCK_LEVER_CLICK:
			break;
		case BLOCK_METAL_BREAK:
			break;
		case BLOCK_METAL_FALL:
			break;
		case BLOCK_METAL_HIT:
			break;
		case BLOCK_METAL_PLACE:
			break;
		case BLOCK_METAL_STEP:
			break;
		case BLOCK_NOTE_BLOCK_BASEDRUM:
			return cn.nukkit.level.Sound.NOTE_BASSATTACK;
		case BLOCK_NOTE_BLOCK_BASS:
			return cn.nukkit.level.Sound.NOTE_BASS;
		case BLOCK_NOTE_BLOCK_HARP:
			return cn.nukkit.level.Sound.NOTE_HARP;
		case BLOCK_NOTE_BLOCK_HAT:
			return cn.nukkit.level.Sound.NOTE_HAT;
		case BLOCK_NOTE_BLOCK_PLING:
			return cn.nukkit.level.Sound.NOTE_PLING;
		case BLOCK_NOTE_BLOCK_SNARE:
			return cn.nukkit.level.Sound.NOTE_SNARE;
		case BLOCK_PISTON_CONTRACT:
			break;
		case BLOCK_PISTON_EXTEND:
			break;
		case BLOCK_PORTAL_AMBIENT:
			break;
		case BLOCK_PORTAL_TRAVEL:
			break;
		case BLOCK_PORTAL_TRIGGER:
			break;
		case BLOCK_REDSTONE_TORCH_BURNOUT:
			break;
		case BLOCK_SAND_BREAK:
			break;
		case BLOCK_SAND_FALL:
			break;
		case BLOCK_SAND_HIT:
			break;
		case BLOCK_SAND_PLACE:
			break;
		case BLOCK_SAND_STEP:
			break;
		case BLOCK_SNOW_BREAK:
			break;
		case BLOCK_SNOW_FALL:
			break;
		case BLOCK_SNOW_HIT:
			break;
		case BLOCK_SNOW_PLACE:
			break;
		case BLOCK_SNOW_STEP:
			break;
		case BLOCK_STONE_BREAK:
			break;
		case BLOCK_STONE_BUTTON_CLICK_OFF:
			break;
		case BLOCK_STONE_BUTTON_CLICK_ON:
			break;
		case BLOCK_STONE_FALL:
			break;
		case BLOCK_STONE_HIT:
			break;
		case BLOCK_STONE_PLACE:
			break;
		case BLOCK_STONE_STEP:
			break;
		case BLOCK_TRIPWIRE_ATTACH:
			break;
		case BLOCK_TRIPWIRE_CLICK_OFF:
			break;
		case BLOCK_TRIPWIRE_CLICK_ON:
			break;
		case BLOCK_TRIPWIRE_DETACH:
			break;
		case BLOCK_WATER_AMBIENT:
			break;
		case BLOCK_WOODEN_DOOR_CLOSE:
			return cn.nukkit.level.Sound.RANDOM_DOOR_CLOSE;
		case BLOCK_WOODEN_DOOR_OPEN:
			return cn.nukkit.level.Sound.RANDOM_DOOR_OPEN;
		case BLOCK_WOODEN_TRAPDOOR_CLOSE:
			return cn.nukkit.level.Sound.RANDOM_DOOR_CLOSE;
		case BLOCK_WOODEN_TRAPDOOR_OPEN:
			return cn.nukkit.level.Sound.RANDOM_DOOR_OPEN;
		case BLOCK_WOOD_BREAK:
			return cn.nukkit.level.Sound.HIT_WOOD;
		case BLOCK_WOOD_FALL:
			break;
		case BLOCK_WOOD_HIT:
			break;
		case BLOCK_WOOD_PLACE:
			break;
		case BLOCK_WOOD_STEP:
			break;
		case ENCHANT_THORNS_HIT:
			break;
		case ENTITY_ARROW_HIT:
			break;
		case ENTITY_ARROW_HIT_PLAYER:
			break;
		case ENTITY_ARROW_SHOOT:
			return cn.nukkit.level.Sound.RANDOM_BOW;
		case ENTITY_BAT_AMBIENT:
			break;
		case ENTITY_BAT_DEATH:
			break;
		case ENTITY_BAT_HURT:
			break;
		case ENTITY_BAT_LOOP:
			break;
		case ENTITY_BLAZE_AMBIENT:
			break;
		case ENTITY_BLAZE_BURN:
			break;
		case ENTITY_BLAZE_DEATH:
			break;
		case ENTITY_BLAZE_HURT:
			break;
		case ENTITY_BLAZE_SHOOT:
			break;
		case ENTITY_CAT_AMBIENT:
			break;
		case ENTITY_CAT_DEATH:
			break;
		case ENTITY_CAT_HISS:
			break;
		case ENTITY_CAT_HURT:
			break;
		case ENTITY_CAT_PURR:
			break;
		case ENTITY_CAT_PURREOW:
			break;
		case ENTITY_CHICKEN_AMBIENT:
			break;
		case ENTITY_CHICKEN_DEATH:
			break;
		case ENTITY_CHICKEN_EGG:
			break;
		case ENTITY_CHICKEN_HURT:
			break;
		case ENTITY_CHICKEN_STEP:
			break;
		case ENTITY_COW_AMBIENT:
			break;
		case ENTITY_COW_DEATH:
			break;
		case ENTITY_COW_HURT:
			break;
		case ENTITY_COW_MILK:
			break;
		case ENTITY_COW_STEP:
			break;
		case ENTITY_CREEPER_DEATH:
			break;
		case ENTITY_CREEPER_HURT:
			break;
		case ENTITY_CREEPER_PRIMED:
			break;
		case ENTITY_DONKEY_AMBIENT:
			break;
		case ENTITY_DONKEY_ANGRY:
			break;
		case ENTITY_DONKEY_CHEST:
			break;
		case ENTITY_DONKEY_DEATH:
			break;
		case ENTITY_DONKEY_HURT:
			break;
		case ENTITY_EGG_THROW:
			break;
		case ENTITY_ELDER_GUARDIAN_AMBIENT:
			break;
		case ENTITY_ELDER_GUARDIAN_AMBIENT_LAND:
			break;
		case ENTITY_ELDER_GUARDIAN_CURSE:
			break;
		case ENTITY_ELDER_GUARDIAN_DEATH:
			break;
		case ENTITY_ELDER_GUARDIAN_DEATH_LAND:
			break;
		case ENTITY_ELDER_GUARDIAN_HURT:
			break;
		case ENTITY_ELDER_GUARDIAN_HURT_LAND:
			break;
		case ENTITY_ENDERMAN_AMBIENT:
			return cn.nukkit.level.Sound.MOB_ENDERMEN_IDLE;
		case ENTITY_ENDERMAN_DEATH:
			return cn.nukkit.level.Sound.MOB_ENDERMEN_DEATH;
		case ENTITY_ENDERMAN_HURT:
			return cn.nukkit.level.Sound.MOB_ENDERMEN_HIT;
		case ENTITY_ENDERMAN_SCREAM:
			return cn.nukkit.level.Sound.MOB_ENDERMEN_SCREAM;
		case ENTITY_ENDERMAN_STARE:
			return cn.nukkit.level.Sound.MOB_ENDERMEN_STARE;
		case ENTITY_ENDERMAN_TELEPORT:
			return cn.nukkit.level.Sound.MOB_ENDERMEN_PORTAL;
		case ENTITY_ENDERMITE_AMBIENT:
			break;
		case ENTITY_ENDERMITE_DEATH:
			break;
		case ENTITY_ENDERMITE_HURT:
			break;
		case ENTITY_ENDERMITE_STEP:
			break;
		case ENTITY_EXPERIENCE_BOTTLE_THROW:
			break;
		case ENTITY_EXPERIENCE_ORB_PICKUP:
			return cn.nukkit.level.Sound.RANDOM_ORB;
		case ENTITY_GENERIC_BIG_FALL:
			break;
		case ENTITY_GENERIC_BURN:
			break;
		case ENTITY_GENERIC_DEATH:
			break;
		case ENTITY_GENERIC_DRINK:
			break;
		case ENTITY_GENERIC_EAT:
			break;
		case ENTITY_GENERIC_EXPLODE:
			break;
		case ENTITY_GENERIC_EXTINGUISH_FIRE:
			break;
		case ENTITY_GENERIC_HURT:
			break;
		case ENTITY_GENERIC_SMALL_FALL:
			break;
		case ENTITY_GENERIC_SPLASH:
			break;
		case ENTITY_GENERIC_SWIM:
			break;
		case ENTITY_GHAST_AMBIENT:
			return cn.nukkit.level.Sound.MOB_GHAST_AFFECTIONATE_SCREAM;
		case ENTITY_GHAST_DEATH:
			return cn.nukkit.level.Sound.MOB_GHAST_DEATH;
		case ENTITY_GHAST_HURT:
			return cn.nukkit.level.Sound.MOB_GHAST_MOAN;
		case ENTITY_GHAST_SCREAM:
			return cn.nukkit.level.Sound.MOB_GHAST_SCREAM;
		case ENTITY_GHAST_SHOOT:
			return cn.nukkit.level.Sound.MOB_GHAST_FIREBALL;
		case ENTITY_GHAST_WARN:
			return cn.nukkit.level.Sound.MOB_GHAST_CHARGE;
		case ENTITY_GUARDIAN_AMBIENT:
			break;
		case ENTITY_GUARDIAN_AMBIENT_LAND:
			break;
		case ENTITY_GUARDIAN_ATTACK:
			break;
		case ENTITY_GUARDIAN_DEATH:
			break;
		case ENTITY_GUARDIAN_DEATH_LAND:
			break;
		case ENTITY_GUARDIAN_FLOP:
			break;
		case ENTITY_GUARDIAN_HURT:
			break;
		case ENTITY_GUARDIAN_HURT_LAND:
			break;
		case ENTITY_HORSE_AMBIENT:
			break;
		case ENTITY_HORSE_ANGRY:
			break;
		case ENTITY_HORSE_ARMOR:
			break;
		case ENTITY_HORSE_BREATHE:
			break;
		case ENTITY_HORSE_DEATH:
			break;
		case ENTITY_HORSE_EAT:
			break;
		case ENTITY_HORSE_GALLOP:
			break;
		case ENTITY_HORSE_HURT:
			break;
		case ENTITY_HORSE_JUMP:
			break;
		case ENTITY_HORSE_LAND:
			break;
		case ENTITY_HORSE_SADDLE:
			break;
		case ENTITY_HORSE_STEP:
			break;
		case ENTITY_HORSE_STEP_WOOD:
			break;
		case ENTITY_HOSTILE_BIG_FALL:
			break;
		case ENTITY_HOSTILE_DEATH:
			break;
		case ENTITY_HOSTILE_HURT:
			break;
		case ENTITY_HOSTILE_SMALL_FALL:
			break;
		case ENTITY_HOSTILE_SPLASH:
			break;
		case ENTITY_HOSTILE_SWIM:
			break;
		case ENTITY_HUSK_AMBIENT:
			break;
		case ENTITY_HUSK_DEATH:
			break;
		case ENTITY_HUSK_HURT:
			break;
		case ENTITY_HUSK_STEP:
			break;
		case ENTITY_ITEM_FRAME_ADD_ITEM:
			return cn.nukkit.level.Sound.BLOCK_ITEMFRAME_ADD_ITEM;
		case ENTITY_ITEM_FRAME_BREAK:
			return cn.nukkit.level.Sound.BLOCK_ITEMFRAME_BREAK;
		case ENTITY_ITEM_FRAME_PLACE:
			return cn.nukkit.level.Sound.BLOCK_ITEMFRAME_PLACE;
		case ENTITY_ITEM_FRAME_REMOVE_ITEM:
			return cn.nukkit.level.Sound.BLOCK_ITEMFRAME_REMOVE_ITEM;
		case ENTITY_ITEM_FRAME_ROTATE_ITEM:
			return cn.nukkit.level.Sound.BLOCK_ITEMFRAME_ROTATE_ITEM;
		case ENTITY_ITEM_BREAK:
			break;
		case ENTITY_ITEM_PICKUP:
			break;
		case ENTITY_MINECART_INSIDE:
			break;
		case ENTITY_MINECART_RIDING:
			break;
		case ENTITY_MOOSHROOM_SHEAR:
			break;
		case ENTITY_MULE_AMBIENT:
			break;
		case ENTITY_MULE_DEATH:
			break;
		case ENTITY_MULE_HURT:
			break;
		case ENTITY_PAINTING_BREAK:
			break;
		case ENTITY_PAINTING_PLACE:
			break;
		case ENTITY_PIG_AMBIENT:
			break;
		case ENTITY_PIG_DEATH:
			break;
		case ENTITY_PIG_HURT:
			break;
		case ENTITY_PIG_SADDLE:
			break;
		case ENTITY_PIG_STEP:
			break;
		case ENTITY_PLAYER_ATTACK_CRIT:
			break;
		case ENTITY_PLAYER_ATTACK_KNOCKBACK:
			break;
		case ENTITY_PLAYER_ATTACK_NODAMAGE:
			break;
		case ENTITY_PLAYER_ATTACK_STRONG:
			break;
		case ENTITY_PLAYER_ATTACK_SWEEP:
			break;
		case ENTITY_PLAYER_ATTACK_WEAK:
			break;
		case ENTITY_PLAYER_BIG_FALL:
			break;
		case ENTITY_PLAYER_BREATH:
			break;
		case ENTITY_PLAYER_BURP:
			break;
		case ENTITY_PLAYER_DEATH:
			break;
		case ENTITY_PLAYER_HURT:
			break;
		case ENTITY_PLAYER_LEVELUP:
			break;
		case ENTITY_PLAYER_SMALL_FALL:
			break;
		case ENTITY_PLAYER_SPLASH:
			break;
		case ENTITY_PLAYER_SWIM:
			break;
		case ENTITY_POLAR_BEAR_AMBIENT:
			break;
		case ENTITY_POLAR_BEAR_DEATH:
			break;
		case ENTITY_POLAR_BEAR_HURT:
			break;
		case ENTITY_POLAR_BEAR_STEP:
			break;
		case ENTITY_POLAR_BEAR_WARNING:
			break;
		case ENTITY_RABBIT_AMBIENT:
			break;
		case ENTITY_RABBIT_ATTACK:
			break;
		case ENTITY_RABBIT_DEATH:
			break;
		case ENTITY_RABBIT_HURT:
			break;
		case ENTITY_RABBIT_JUMP:
			break;
		case ENTITY_SHEEP_AMBIENT:
			break;
		case ENTITY_SHEEP_DEATH:
			break;
		case ENTITY_SHEEP_HURT:
			break;
		case ENTITY_SHEEP_SHEAR:
			break;
		case ENTITY_SHEEP_STEP:
			break;
		case ENTITY_SHULKER_AMBIENT:
			break;
		case ENTITY_SHULKER_BULLET_HIT:
			break;
		case ENTITY_SHULKER_BULLET_HURT:
			break;
		case ENTITY_SHULKER_CLOSE:
			break;
		case ENTITY_SHULKER_DEATH:
			break;
		case ENTITY_SHULKER_HURT:
			break;
		case ENTITY_SHULKER_HURT_CLOSED:
			break;
		case ENTITY_SHULKER_OPEN:
			break;
		case ENTITY_SHULKER_SHOOT:
			break;
		case ENTITY_SHULKER_TELEPORT:
			break;
		case ENTITY_SILVERFISH_AMBIENT:
			break;
		case ENTITY_SILVERFISH_DEATH:
			break;
		case ENTITY_SILVERFISH_HURT:
			break;
		case ENTITY_SILVERFISH_STEP:
			break;
		case ENTITY_SKELETON_AMBIENT:
			break;
		case ENTITY_SKELETON_DEATH:
			break;
		case ENTITY_SKELETON_HORSE_AMBIENT:
			break;
		case ENTITY_SKELETON_HORSE_DEATH:
			break;
		case ENTITY_SKELETON_HORSE_HURT:
			break;
		case ENTITY_SKELETON_HURT:
			break;
		case ENTITY_SKELETON_SHOOT:
			return cn.nukkit.level.Sound.RANDOM_BOW;
		case ENTITY_SKELETON_STEP:
			break;
		case ENTITY_SLIME_ATTACK:
			break;
		case ENTITY_SLIME_DEATH:
			break;
		case ENTITY_SLIME_HURT:
			break;
		case ENTITY_SLIME_JUMP:
			break;
		case ENTITY_SLIME_SQUISH:
			break;
		case ENTITY_SNOWBALL_THROW:
			break;
		case ENTITY_SPIDER_AMBIENT:
			break;
		case ENTITY_SPIDER_DEATH:
			break;
		case ENTITY_SPIDER_HURT:
			break;
		case ENTITY_SPIDER_STEP:
			break;
		case ENTITY_SPLASH_POTION_BREAK:
			break;
		case ENTITY_SPLASH_POTION_THROW:
			break;
		case ENTITY_SQUID_AMBIENT:
			break;
		case ENTITY_SQUID_DEATH:
			break;
		case ENTITY_SQUID_HURT:
			break;
		case ENTITY_STRAY_AMBIENT:
			break;
		case ENTITY_STRAY_DEATH:
			break;
		case ENTITY_STRAY_HURT:
			break;
		case ENTITY_STRAY_STEP:
			break;
		case ENTITY_TNT_PRIMED:
			return cn.nukkit.level.Sound.RANDOM_FUSE;
		case ENTITY_VILLAGER_AMBIENT:
			break;
		case ENTITY_VILLAGER_DEATH:
			break;
		case ENTITY_VILLAGER_HURT:
			break;
		case ENTITY_VILLAGER_NO:
			break;
		case ENTITY_VILLAGER_YES:
			break;
		case ENTITY_WITCH_AMBIENT:
			break;
		case ENTITY_WITCH_DEATH:
			break;
		case ENTITY_WITCH_DRINK:
			break;
		case ENTITY_WITCH_HURT:
			break;
		case ENTITY_WITCH_THROW:
			break;
		case ENTITY_WITHER_AMBIENT:
			break;
		case ENTITY_WITHER_BREAK_BLOCK:
			break;
		case ENTITY_WITHER_DEATH:
			break;
		case ENTITY_WITHER_HURT:
			break;
		case ENTITY_WITHER_SHOOT:
			break;
		case ENTITY_WITHER_SKELETON_AMBIENT:
			break;
		case ENTITY_WITHER_SKELETON_DEATH:
			break;
		case ENTITY_WITHER_SKELETON_HURT:
			break;
		case ENTITY_WITHER_SKELETON_STEP:
			break;
		case ENTITY_WITHER_SPAWN:
			break;
		case ENTITY_WOLF_AMBIENT:
			break;
		case ENTITY_WOLF_DEATH:
			break;
		case ENTITY_WOLF_GROWL:
			break;
		case ENTITY_WOLF_HOWL:
			break;
		case ENTITY_WOLF_HURT:
			break;
		case ENTITY_WOLF_PANT:
			break;
		case ENTITY_WOLF_SHAKE:
			break;
		case ENTITY_WOLF_STEP:
			break;
		case ENTITY_WOLF_WHINE:
			break;
		case ENTITY_ZOMBIE_AMBIENT:
			break;
		case ENTITY_ZOMBIE_ATTACK_IRON_DOOR:
			break;
		case ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR:
			return cn.nukkit.level.Sound.MOB_ZOMBIE_WOOD;
		case ENTITY_ZOMBIE_BREAK_WOODEN_DOOR:
			return cn.nukkit.level.Sound.MOB_ZOMBIE_WOODBREAK;
		case ENTITY_ZOMBIE_DEATH:
			break;
		case ENTITY_ZOMBIE_HORSE_AMBIENT:
			break;
		case ENTITY_ZOMBIE_HORSE_DEATH:
			break;
		case ENTITY_ZOMBIE_HORSE_HURT:
			break;
		case ENTITY_ZOMBIE_HURT:
			break;
		case ENTITY_ZOMBIE_STEP:
			break;
		case ENTITY_ZOMBIE_VILLAGER_AMBIENT:
			break;
		case ENTITY_ZOMBIE_VILLAGER_CONVERTED:
			break;
		case ENTITY_ZOMBIE_VILLAGER_DEATH:
			break;
		case ENTITY_ZOMBIE_VILLAGER_HURT:
			break;
		case ENTITY_ZOMBIE_VILLAGER_STEP:
			break;
		case ITEM_ARMOR_EQUIP_CHAIN:
			break;
		case ITEM_ARMOR_EQUIP_DIAMOND:
			break;
		case ITEM_ARMOR_EQUIP_GENERIC:
			break;
		case ITEM_ARMOR_EQUIP_GOLD:
			break;
		case ITEM_ARMOR_EQUIP_IRON:
			break;
		case ITEM_ARMOR_EQUIP_LEATHER:
			break;
		case ITEM_BOTTLE_FILL:
			break;
		case ITEM_BOTTLE_FILL_DRAGONBREATH:
			break;
		case ITEM_BUCKET_EMPTY:
			break;
		case ITEM_BUCKET_EMPTY_LAVA:
			break;
		case ITEM_BUCKET_FILL:
			break;
		case ITEM_BUCKET_FILL_LAVA:
			break;
		case ITEM_CHORUS_FRUIT_TELEPORT:
			break;
		case ITEM_ELYTRA_FLYING:
			break;
		case ITEM_FIRECHARGE_USE:
			break;
		case ITEM_FLINTANDSTEEL_USE:
			break;
		case ITEM_HOE_TILL:
			break;
		case ITEM_SHIELD_BLOCK:
			break;
		case ITEM_SHIELD_BREAK:
			break;
		case ITEM_SHOVEL_FLATTEN:
			break;
		case MUSIC_CREATIVE:
			return cn.nukkit.level.Sound.MUSIC_GAME_CREATIVE;
		case MUSIC_CREDITS:
			return cn.nukkit.level.Sound.MUSIC_GAME_CREDITS;
		case MUSIC_DRAGON:
			return cn.nukkit.level.Sound.MUSIC_GAME_ENDBOSS;
		case MUSIC_END:
			return cn.nukkit.level.Sound.MUSIC_GAME_END;
		case MUSIC_GAME:
			return cn.nukkit.level.Sound.MUSIC_GAME;
		case MUSIC_MENU:
			return cn.nukkit.level.Sound.MUSIC_MENU;
		case MUSIC_NETHER:
			return cn.nukkit.level.Sound.MUSIC_GAME_NETHER;
		case MUSIC_DISC_11:
			break;
		case MUSIC_DISC_13:
			break;
		case MUSIC_DISC_BLOCKS:
			break;
		case MUSIC_DISC_CAT:
			break;
		case MUSIC_DISC_CHIRP:
			break;
		case MUSIC_DISC_FAR:
			break;
		case MUSIC_DISC_MALL:
			break;
		case MUSIC_DISC_MELLOHI:
			break;
		case MUSIC_DISC_STAL:
			break;
		case MUSIC_DISC_STRAD:
			break;
		case MUSIC_DISC_WAIT:
			break;
		case MUSIC_DISC_WARD:
			break;
		case UI_BUTTON_CLICK:
			return cn.nukkit.level.Sound.RANDOM_CLICK;
		case WEATHER_RAIN:
			break;
		case WEATHER_RAIN_ABOVE:
			break;
		// Added in Minecraft 1.11:
		case BLOCK_SHULKER_BOX_CLOSE:
			break;
		case BLOCK_SHULKER_BOX_OPEN:
			break;
		case ENTITY_ELDER_GUARDIAN_FLOP:
			break;
		case ENTITY_LLAMA_AMBIENT:
			break;
		case ENTITY_LLAMA_ANGRY:
			break;
		case ENTITY_LLAMA_CHEST:
			break;
		case ENTITY_LLAMA_DEATH:
			break;
		case ENTITY_LLAMA_EAT:
			break;
		case ENTITY_LLAMA_HURT:
			break;
		case ENTITY_LLAMA_SPIT:
			break;
		case ENTITY_LLAMA_STEP:
			break;
		case ENTITY_LLAMA_SWAG:
			break;
		case ENTITY_MULE_CHEST:
			break;
		case ENTITY_VEX_AMBIENT:
			break;
		case ENTITY_VEX_CHARGE:
			break;
		case ENTITY_VEX_DEATH:
			break;
		case ENTITY_VEX_HURT:
			break;
		case ITEM_ARMOR_EQUIP_ELYTRA:
			break;
		case ITEM_BOTTLE_EMPTY:
			break;
		case ITEM_TOTEM_USE:
			break;
		case AMBIENT_UNDERWATER_ENTER:
			break;
		case AMBIENT_UNDERWATER_EXIT:
			break;
		case AMBIENT_UNDERWATER_LOOP:
			break;
		case AMBIENT_UNDERWATER_LOOP_ADDITIONS:
			break;
		case AMBIENT_UNDERWATER_LOOP_ADDITIONS_RARE:
			break;
		case AMBIENT_UNDERWATER_LOOP_ADDITIONS_ULTRA_RARE:
			break;
		case BLOCK_BEACON_ACTIVATE:
			break;
		case BLOCK_BEACON_AMBIENT:
			break;
		case BLOCK_BEACON_DEACTIVATE:
			break;
		case BLOCK_BEACON_POWER_SELECT:
			break;
		case BLOCK_BUBBLE_COLUMN_BUBBLE_POP:
			break;
		case BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT:
			break;
		case BLOCK_BUBBLE_COLUMN_UPWARDS_INSIDE:
			break;
		case BLOCK_BUBBLE_COLUMN_WHIRLPOOL_AMBIENT:
			break;
		case BLOCK_BUBBLE_COLUMN_WHIRLPOOL_INSIDE:
			break;
		case BLOCK_CONDUIT_ACTIVATE:
			break;
		case BLOCK_CONDUIT_AMBIENT:
			break;
		case BLOCK_CONDUIT_AMBIENT_SHORT:
			break;
		case BLOCK_CONDUIT_ATTACK_TARGET:
			break;
		case BLOCK_CONDUIT_DEACTIVATE:
			break;
		case BLOCK_CORAL_BLOCK_BREAK:
			break;
		case BLOCK_CORAL_BLOCK_FALL:
			break;
		case BLOCK_CORAL_BLOCK_HIT:
			break;
		case BLOCK_CORAL_BLOCK_PLACE:
			break;
		case BLOCK_CORAL_BLOCK_STEP:
			break;
		case BLOCK_ENDER_CHEST_CLOSE:
			break;
		case BLOCK_ENDER_CHEST_OPEN:
			break;
		case BLOCK_END_PORTAL_FRAME_FILL:
			break;
		case BLOCK_END_PORTAL_SPAWN:
			break;
		case BLOCK_LILY_PAD_PLACE:
			break;
		case BLOCK_METAL_PRESSURE_PLATE_CLICK_OFF:
			break;
		case BLOCK_METAL_PRESSURE_PLATE_CLICK_ON:
			break;
		case BLOCK_NOTE_BLOCK_BELL:
			break;
		case BLOCK_NOTE_BLOCK_CHIME:
			break;
		case BLOCK_NOTE_BLOCK_FLUTE:
			break;
		case BLOCK_NOTE_BLOCK_GUITAR:
			break;
		case BLOCK_NOTE_BLOCK_XYLOPHONE:
			break;
		case BLOCK_PUMPKIN_CARVE:
			break;
		case BLOCK_SLIME_BLOCK_BREAK:
			break;
		case BLOCK_SLIME_BLOCK_FALL:
			break;
		case BLOCK_SLIME_BLOCK_HIT:
			break;
		case BLOCK_SLIME_BLOCK_PLACE:
			break;
		case BLOCK_SLIME_BLOCK_STEP:
			break;
		case BLOCK_STONE_PRESSURE_PLATE_CLICK_OFF:
			break;
		case BLOCK_STONE_PRESSURE_PLATE_CLICK_ON:
			break;
		case BLOCK_WET_GRASS_BREAK:
			break;
		case BLOCK_WET_GRASS_FALL:
			break;
		case BLOCK_WET_GRASS_HIT:
			break;
		case BLOCK_WET_GRASS_PLACE:
			break;
		case BLOCK_WET_GRASS_STEP:
			break;
		case BLOCK_WOODEN_BUTTON_CLICK_OFF:
			break;
		case BLOCK_WOODEN_BUTTON_CLICK_ON:
			break;
		case BLOCK_WOODEN_PRESSURE_PLATE_CLICK_OFF:
			break;
		case BLOCK_WOODEN_PRESSURE_PLATE_CLICK_ON:
			break;
		case BLOCK_WOOL_BREAK:
			break;
		case BLOCK_WOOL_FALL:
			break;
		case BLOCK_WOOL_HIT:
			break;
		case BLOCK_WOOL_PLACE:
			break;
		case BLOCK_WOOL_STEP:
			break;
		case ENTITY_ARMOR_STAND_BREAK:
			break;
		case ENTITY_ARMOR_STAND_FALL:
			break;
		case ENTITY_ARMOR_STAND_HIT:
			break;
		case ENTITY_ARMOR_STAND_PLACE:
			break;
		case ENTITY_BAT_TAKEOFF:
			break;
		case ENTITY_BOAT_PADDLE_LAND:
			break;
		case ENTITY_BOAT_PADDLE_WATER:
			break;
		case ENTITY_COD_AMBIENT:
			break;
		case ENTITY_COD_DEATH:
			break;
		case ENTITY_COD_FLOP:
			break;
		case ENTITY_COD_HURT:
			break;
		case ENTITY_DOLPHIN_AMBIENT:
			break;
		case ENTITY_DOLPHIN_AMBIENT_WATER:
			break;
		case ENTITY_DOLPHIN_ATTACK:
			break;
		case ENTITY_DOLPHIN_DEATH:
			break;
		case ENTITY_DOLPHIN_EAT:
			break;
		case ENTITY_DOLPHIN_HURT:
			break;
		case ENTITY_DOLPHIN_JUMP:
			break;
		case ENTITY_DOLPHIN_PLAY:
			break;
		case ENTITY_DOLPHIN_SPLASH:
			break;
		case ENTITY_DOLPHIN_SWIM:
			break;
		case ENTITY_DRAGON_FIREBALL_EXPLODE:
			break;
		case ENTITY_DROWNED_AMBIENT:
			break;
		case ENTITY_DROWNED_AMBIENT_WATER:
			break;
		case ENTITY_DROWNED_DEATH:
			break;
		case ENTITY_DROWNED_DEATH_WATER:
			break;
		case ENTITY_DROWNED_HURT:
			break;
		case ENTITY_DROWNED_HURT_WATER:
			break;
		case ENTITY_DROWNED_SHOOT:
			break;
		case ENTITY_DROWNED_STEP:
			break;
		case ENTITY_DROWNED_SWIM:
			break;
		case ENTITY_ENDER_DRAGON_AMBIENT:
			break;
		case ENTITY_ENDER_DRAGON_DEATH:
			break;
		case ENTITY_ENDER_DRAGON_FLAP:
			break;
		case ENTITY_ENDER_DRAGON_GROWL:
			break;
		case ENTITY_ENDER_DRAGON_HURT:
			break;
		case ENTITY_ENDER_DRAGON_SHOOT:
			break;
		case ENTITY_ENDER_EYE_DEATH:
			break;
		case ENTITY_ENDER_EYE_LAUNCH:
			break;
		case ENTITY_ENDER_PEARL_THROW:
			break;
		case ENTITY_EVOKER_AMBIENT:
			break;
		case ENTITY_EVOKER_CAST_SPELL:
			break;
		case ENTITY_EVOKER_DEATH:
			break;
		case ENTITY_EVOKER_FANGS_ATTACK:
			break;
		case ENTITY_EVOKER_HURT:
			break;
		case ENTITY_EVOKER_PREPARE_ATTACK:
			break;
		case ENTITY_EVOKER_PREPARE_SUMMON:
			break;
		case ENTITY_EVOKER_PREPARE_WOLOLO:
			break;
		case ENTITY_FIREWORK_ROCKET_BLAST:
			break;
		case ENTITY_FIREWORK_ROCKET_BLAST_FAR:
			break;
		case ENTITY_FIREWORK_ROCKET_LARGE_BLAST:
			break;
		case ENTITY_FIREWORK_ROCKET_LARGE_BLAST_FAR:
			break;
		case ENTITY_FIREWORK_ROCKET_LAUNCH:
			break;
		case ENTITY_FIREWORK_ROCKET_SHOOT:
			break;
		case ENTITY_FIREWORK_ROCKET_TWINKLE:
			break;
		case ENTITY_FIREWORK_ROCKET_TWINKLE_FAR:
			break;
		case ENTITY_FISHING_BOBBER_RETRIEVE:
			break;
		case ENTITY_FISHING_BOBBER_SPLASH:
			break;
		case ENTITY_FISHING_BOBBER_THROW:
			break;
		case ENTITY_FISH_SWIM:
			break;
		case ENTITY_HUSK_CONVERTED_TO_ZOMBIE:
			break;
		case ENTITY_ILLUSIONER_AMBIENT:
			break;
		case ENTITY_ILLUSIONER_CAST_SPELL:
			break;
		case ENTITY_ILLUSIONER_DEATH:
			break;
		case ENTITY_ILLUSIONER_HURT:
			break;
		case ENTITY_ILLUSIONER_MIRROR_MOVE:
			break;
		case ENTITY_ILLUSIONER_PREPARE_BLINDNESS:
			break;
		case ENTITY_ILLUSIONER_PREPARE_MIRROR:
			break;
		case ENTITY_IRON_GOLEM_ATTACK:
			break;
		case ENTITY_IRON_GOLEM_DEATH:
			break;
		case ENTITY_IRON_GOLEM_HURT:
			break;
		case ENTITY_IRON_GOLEM_STEP:
			break;
		case ENTITY_LEASH_KNOT_BREAK:
			break;
		case ENTITY_LEASH_KNOT_PLACE:
			break;
		case ENTITY_LIGHTNING_BOLT_IMPACT:
			break;
		case ENTITY_LIGHTNING_BOLT_THUNDER:
			break;
		case ENTITY_LINGERING_POTION_THROW:
			break;
		case ENTITY_MAGMA_CUBE_DEATH:
			break;
		case ENTITY_MAGMA_CUBE_DEATH_SMALL:
			break;
		case ENTITY_MAGMA_CUBE_HURT:
			break;
		case ENTITY_MAGMA_CUBE_HURT_SMALL:
			break;
		case ENTITY_MAGMA_CUBE_JUMP:
			break;
		case ENTITY_MAGMA_CUBE_SQUISH:
			break;
		case ENTITY_MAGMA_CUBE_SQUISH_SMALL:
			break;
		case ENTITY_PARROT_AMBIENT:
			break;
		case ENTITY_PARROT_DEATH:
			break;
		case ENTITY_PARROT_EAT:
			break;
		case ENTITY_PARROT_FLY:
			break;
		case ENTITY_PARROT_HURT:
			break;
		case ENTITY_PARROT_IMITATE_BLAZE:
			break;
		case ENTITY_PARROT_IMITATE_CREEPER:
			break;
		case ENTITY_PARROT_IMITATE_DROWNED:
			break;
		case ENTITY_PARROT_IMITATE_ELDER_GUARDIAN:
			break;
		case ENTITY_PARROT_IMITATE_ENDERMAN:
			break;
		case ENTITY_PARROT_IMITATE_ENDERMITE:
			break;
		case ENTITY_PARROT_IMITATE_ENDER_DRAGON:
			break;
		case ENTITY_PARROT_IMITATE_EVOKER:
			break;
		case ENTITY_PARROT_IMITATE_GHAST:
			break;
		case ENTITY_PARROT_IMITATE_HUSK:
			break;
		case ENTITY_PARROT_IMITATE_ILLUSIONER:
			break;
		case ENTITY_PARROT_IMITATE_MAGMA_CUBE:
			break;
		case ENTITY_PARROT_IMITATE_PHANTOM:
			break;
		case ENTITY_PARROT_IMITATE_POLAR_BEAR:
			break;
		case ENTITY_PARROT_IMITATE_SHULKER:
			break;
		case ENTITY_PARROT_IMITATE_SILVERFISH:
			break;
		case ENTITY_PARROT_IMITATE_SKELETON:
			break;
		case ENTITY_PARROT_IMITATE_SLIME:
			break;
		case ENTITY_PARROT_IMITATE_SPIDER:
			break;
		case ENTITY_PARROT_IMITATE_STRAY:
			break;
		case ENTITY_PARROT_IMITATE_VEX:
			break;
		case ENTITY_PARROT_IMITATE_VINDICATOR:
			break;
		case ENTITY_PARROT_IMITATE_WITCH:
			break;
		case ENTITY_PARROT_IMITATE_WITHER:
			break;
		case ENTITY_PARROT_IMITATE_WITHER_SKELETON:
			break;
		case ENTITY_PARROT_IMITATE_WOLF:
			break;
		case ENTITY_PARROT_IMITATE_ZOMBIE:
			break;
		case ENTITY_PARROT_IMITATE_ZOMBIE_PIGMAN:
			break;
		case ENTITY_PARROT_IMITATE_ZOMBIE_VILLAGER:
			break;
		case ENTITY_PARROT_STEP:
			break;
		case ENTITY_PHANTOM_AMBIENT:
			break;
		case ENTITY_PHANTOM_BITE:
			break;
		case ENTITY_PHANTOM_DEATH:
			break;
		case ENTITY_PHANTOM_FLAP:
			break;
		case ENTITY_PHANTOM_HURT:
			break;
		case ENTITY_PHANTOM_SWOOP:
			break;
		case ENTITY_PLAYER_HURT_DROWN:
			break;
		case ENTITY_PLAYER_HURT_ON_FIRE:
			break;
		case ENTITY_PLAYER_SPLASH_HIGH_SPEED:
			break;
		case ENTITY_POLAR_BEAR_AMBIENT_BABY:
			break;
		case ENTITY_PUFFER_FISH_AMBIENT:
			break;
		case ENTITY_PUFFER_FISH_BLOW_OUT:
			break;
		case ENTITY_PUFFER_FISH_BLOW_UP:
			break;
		case ENTITY_PUFFER_FISH_DEATH:
			break;
		case ENTITY_PUFFER_FISH_FLOP:
			break;
		case ENTITY_PUFFER_FISH_HURT:
			break;
		case ENTITY_PUFFER_FISH_STING:
			break;
		case ENTITY_SALMON_AMBIENT:
			break;
		case ENTITY_SALMON_DEATH:
			break;
		case ENTITY_SALMON_FLOP:
			break;
		case ENTITY_SALMON_HURT:
			break;
		case ENTITY_SKELETON_HORSE_AMBIENT_WATER:
			break;
		case ENTITY_SKELETON_HORSE_GALLOP_WATER:
			break;
		case ENTITY_SKELETON_HORSE_JUMP_WATER:
			break;
		case ENTITY_SKELETON_HORSE_STEP_WATER:
			break;
		case ENTITY_SKELETON_HORSE_SWIM:
			break;
		case ENTITY_SLIME_DEATH_SMALL:
			break;
		case ENTITY_SLIME_HURT_SMALL:
			break;
		case ENTITY_SLIME_JUMP_SMALL:
			break;
		case ENTITY_SLIME_SQUISH_SMALL:
			break;
		case ENTITY_SNOW_GOLEM_AMBIENT:
			break;
		case ENTITY_SNOW_GOLEM_DEATH:
			break;
		case ENTITY_SNOW_GOLEM_HURT:
			break;
		case ENTITY_SNOW_GOLEM_SHOOT:
			break;
		case ENTITY_SQUID_SQUIRT:
			break;
		case ENTITY_TROPICAL_FISH_AMBIENT:
			break;
		case ENTITY_TROPICAL_FISH_DEATH:
			break;
		case ENTITY_TROPICAL_FISH_FLOP:
			break;
		case ENTITY_TROPICAL_FISH_HURT:
			break;
		case ENTITY_TURTLE_AMBIENT_LAND:
			break;
		case ENTITY_TURTLE_DEATH:
			break;
		case ENTITY_TURTLE_DEATH_BABY:
			break;
		case ENTITY_TURTLE_EGG_BREAK:
			break;
		case ENTITY_TURTLE_EGG_CRACK:
			break;
		case ENTITY_TURTLE_EGG_HATCH:
			break;
		case ENTITY_TURTLE_HURT:
			break;
		case ENTITY_TURTLE_HURT_BABY:
			break;
		case ENTITY_TURTLE_LAY_EGG:
			break;
		case ENTITY_TURTLE_SHAMBLE:
			break;
		case ENTITY_TURTLE_SHAMBLE_BABY:
			break;
		case ENTITY_TURTLE_SWIM:
			break;
		case ENTITY_VILLAGER_TRADE:
			break;
		case ENTITY_VINDICATOR_AMBIENT:
			break;
		case ENTITY_VINDICATOR_DEATH:
			break;
		case ENTITY_VINDICATOR_HURT:
			break;
		case ENTITY_ZOMBIE_CONVERTED_TO_DROWNED:
			break;
		case ENTITY_ZOMBIE_DESTROY_EGG:
			break;
		case ENTITY_ZOMBIE_INFECT:
			break;
		case ENTITY_ZOMBIE_PIGMAN_AMBIENT:
			break;
		case ENTITY_ZOMBIE_PIGMAN_ANGRY:
			break;
		case ENTITY_ZOMBIE_PIGMAN_DEATH:
			break;
		case ENTITY_ZOMBIE_PIGMAN_HURT:
			break;
		case ENTITY_ZOMBIE_VILLAGER_CURE:
			break;
		case ITEM_ARMOR_EQUIP_TURTLE:
			break;
		case ITEM_AXE_STRIP:
			break;
		case ITEM_BUCKET_EMPTY_FISH:
			break;
		case ITEM_BUCKET_FILL_FISH:
			break;
		case ITEM_TRIDENT_HIT:
			break;
		case ITEM_TRIDENT_HIT_GROUND:
			break;
		case ITEM_TRIDENT_RETURN:
			break;
		case ITEM_TRIDENT_RIPTIDE_1:
			break;
		case ITEM_TRIDENT_RIPTIDE_2:
			break;
		case ITEM_TRIDENT_RIPTIDE_3:
			break;
		case ITEM_TRIDENT_THROW:
			break;
		case ITEM_TRIDENT_THUNDER:
			break;
		case MUSIC_UNDER_WATER:
			break;
		case UI_TOAST_CHALLENGE_COMPLETE:
			break;
		case UI_TOAST_IN:
			break;
		case UI_TOAST_OUT:
			break;
		default:
			break;
		}
		return null;
	}

}
