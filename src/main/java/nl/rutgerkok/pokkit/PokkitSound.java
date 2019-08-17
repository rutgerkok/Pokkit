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
			return cn.nukkit.level.Sound.RANDOM_ANVIL_LAND;
		case BLOCK_ANVIL_STEP:
			break;
		case BLOCK_ANVIL_USE:
			return cn.nukkit.level.Sound.RANDOM_ANVIL_USE;
		case BLOCK_BREWING_STAND_BREW:
			return cn.nukkit.level.Sound.RANDOM_POTION_BREWED;
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
			return cn.nukkit.level.Sound.RANDOM_DOOR_CLOSE;
		case BLOCK_FENCE_GATE_OPEN:
			return cn.nukkit.level.Sound.RANDOM_DOOR_OPEN;
		case BLOCK_FIRE_AMBIENT:
			return cn.nukkit.level.Sound.FIRE_FIRE;
		case BLOCK_FIRE_EXTINGUISH:
			return cn.nukkit.level.Sound.RANDOM_FIZZ;
		case BLOCK_FURNACE_FIRE_CRACKLE:
			return cn.nukkit.level.Sound.FURNACE_LIT;
		case BLOCK_GLASS_BREAK:
			return cn.nukkit.level.Sound.RANDOM_GLASS;
		case BLOCK_GLASS_FALL:
			return cn.nukkit.level.Sound.FALL_STONE;
		case BLOCK_GLASS_HIT:
			return cn.nukkit.level.Sound.HIT_STONE;
		case BLOCK_GLASS_PLACE:
			return cn.nukkit.level.Sound.STEP_STONE;
		case BLOCK_GLASS_STEP:
			return cn.nukkit.level.Sound.STEP_STONE;
		case BLOCK_GRASS_BREAK:
			return cn.nukkit.level.Sound.DIG_GRASS;
		case BLOCK_GRASS_FALL:
			return cn.nukkit.level.Sound.FALL_GRASS;
		case BLOCK_GRASS_HIT:
			return cn.nukkit.level.Sound.HIT_GRASS;
		case BLOCK_GRASS_PLACE:
			return cn.nukkit.level.Sound.DIG_GRASS;
		case BLOCK_GRASS_STEP:
			return cn.nukkit.level.Sound.STEP_GRASS;
		case BLOCK_GRAVEL_BREAK:
			return cn.nukkit.level.Sound.DIG_GRAVEL;
		case BLOCK_GRAVEL_FALL:
			return cn.nukkit.level.Sound.FALL_GRAVEL;
		case BLOCK_GRAVEL_HIT:
			return cn.nukkit.level.Sound.HIT_GRAVEL;
		case BLOCK_GRAVEL_PLACE:
			return cn.nukkit.level.Sound.DIG_GRAVEL;
		case BLOCK_GRAVEL_STEP:
			return cn.nukkit.level.Sound.STEP_GRAVEL;
		case BLOCK_IRON_DOOR_CLOSE:
			return cn.nukkit.level.Sound.RANDOM_DOOR_CLOSE;
		case BLOCK_IRON_DOOR_OPEN:
			return cn.nukkit.level.Sound.RANDOM_DOOR_OPEN;
		case BLOCK_IRON_TRAPDOOR_CLOSE:
			return cn.nukkit.level.Sound.RANDOM_DOOR_CLOSE;
		case BLOCK_IRON_TRAPDOOR_OPEN:
			return cn.nukkit.level.Sound.RANDOM_DOOR_OPEN;
		case BLOCK_LADDER_BREAK:
			return cn.nukkit.level.Sound.DIG_WOOD;
		case BLOCK_LADDER_FALL:
			return cn.nukkit.level.Sound.FALL_LADDER;
		case BLOCK_LADDER_HIT:
			return cn.nukkit.level.Sound.HIT_LADDER;
		case BLOCK_LADDER_PLACE:
			return cn.nukkit.level.Sound.DIG_WOOD;
		case BLOCK_LADDER_STEP:
			return cn.nukkit.level.Sound.STEP_LADDER;
		case BLOCK_LAVA_AMBIENT:
			return cn.nukkit.level.Sound.LIQUID_LAVA;
		case BLOCK_LAVA_EXTINGUISH:
			return cn.nukkit.level.Sound.RANDOM_FIZZ;
		case BLOCK_LAVA_POP:
			return cn.nukkit.level.Sound.LIQUID_LAVAPOP;
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
			return cn.nukkit.level.Sound.TILE_PISTON_IN;
		case BLOCK_PISTON_EXTEND:
			return cn.nukkit.level.Sound.TILE_PISTON_OUT;
		case BLOCK_PORTAL_AMBIENT:
			return cn.nukkit.level.Sound.PORTAL_PORTAL;
		case BLOCK_PORTAL_TRAVEL:
			return cn.nukkit.level.Sound.PORTAL_TRAVEL;
		case BLOCK_PORTAL_TRIGGER:
			return cn.nukkit.level.Sound.PORTAL_TRIGGER;
		case BLOCK_REDSTONE_TORCH_BURNOUT:
			break;
		case BLOCK_SAND_BREAK:
			return cn.nukkit.level.Sound.DIG_SAND;
		case BLOCK_SAND_FALL:
			return cn.nukkit.level.Sound.FALL_SAND;
		case BLOCK_SAND_HIT:
			return cn.nukkit.level.Sound.HIT_SAND;
		case BLOCK_SAND_PLACE:
			return cn.nukkit.level.Sound.DIG_SAND;
		case BLOCK_SAND_STEP:
			return cn.nukkit.level.Sound.STEP_SAND;
		case BLOCK_SNOW_BREAK:
			return cn.nukkit.level.Sound.DIG_SNOW;
		case BLOCK_SNOW_FALL:
			return cn.nukkit.level.Sound.FALL_SNOW;
		case BLOCK_SNOW_HIT:
			return cn.nukkit.level.Sound.HIT_SNOW;
		case BLOCK_SNOW_PLACE:
			return cn.nukkit.level.Sound.DIG_SNOW;
		case BLOCK_SNOW_STEP:
			return cn.nukkit.level.Sound.STEP_SNOW;
		case BLOCK_STONE_BREAK:
			return cn.nukkit.level.Sound.DIG_STONE;
		case BLOCK_STONE_BUTTON_CLICK_OFF:
			break;
		case BLOCK_STONE_BUTTON_CLICK_ON:
			break;
		case BLOCK_STONE_FALL:
			return cn.nukkit.level.Sound.FALL_STONE;
		case BLOCK_STONE_HIT:
			return cn.nukkit.level.Sound.HIT_STONE;
		case BLOCK_STONE_PLACE:
			return cn.nukkit.level.Sound.DIG_STONE;
		case BLOCK_STONE_STEP:
			return cn.nukkit.level.Sound.STEP_STONE;
		case BLOCK_TRIPWIRE_ATTACH:
			break;
		case BLOCK_TRIPWIRE_CLICK_OFF:
			break;
		case BLOCK_TRIPWIRE_CLICK_ON:
			break;
		case BLOCK_TRIPWIRE_DETACH:
			break;
		case BLOCK_WATER_AMBIENT:
			return cn.nukkit.level.Sound.LIQUID_WATER;
		case BLOCK_WOODEN_DOOR_CLOSE:
			return cn.nukkit.level.Sound.RANDOM_DOOR_CLOSE;
		case BLOCK_WOODEN_DOOR_OPEN:
			return cn.nukkit.level.Sound.RANDOM_DOOR_OPEN;
		case BLOCK_WOODEN_TRAPDOOR_CLOSE:
			return cn.nukkit.level.Sound.RANDOM_DOOR_CLOSE;
		case BLOCK_WOODEN_TRAPDOOR_OPEN:
			return cn.nukkit.level.Sound.RANDOM_DOOR_OPEN;
		case BLOCK_WOOD_BREAK:
			return cn.nukkit.level.Sound.DIG_WOOD;
		case BLOCK_WOOD_FALL:
			return cn.nukkit.level.Sound.FALL_WOOD;
		case BLOCK_WOOD_HIT:
			return cn.nukkit.level.Sound.HIT_WOOD;
		case BLOCK_WOOD_PLACE:
			return cn.nukkit.level.Sound.DIG_WOOD;
		case BLOCK_WOOD_STEP:
			return cn.nukkit.level.Sound.STEP_WOOD;
		case ENCHANT_THORNS_HIT:
			break;
		case ENTITY_ARROW_HIT:
			return cn.nukkit.level.Sound.RANDOM_BOWHIT;
		case ENTITY_ARROW_HIT_PLAYER:
			return cn.nukkit.level.Sound.RANDOM_BOWHIT;
		case ENTITY_ARROW_SHOOT:
			return cn.nukkit.level.Sound.RANDOM_BOW;
		case ENTITY_BAT_AMBIENT:
			return cn.nukkit.level.Sound.MOB_BAT_IDLE;
		case ENTITY_BAT_DEATH:
			return cn.nukkit.level.Sound.MOB_BAT_DEATH;
		case ENTITY_BAT_HURT:
			return cn.nukkit.level.Sound.MOB_BAT_HURT;
		case ENTITY_BAT_LOOP:
			break;
		case ENTITY_BLAZE_AMBIENT:
			return cn.nukkit.level.Sound.MOB_BLAZE_BREATHE;
		case ENTITY_BLAZE_BURN:
			break;
		case ENTITY_BLAZE_DEATH:
			return cn.nukkit.level.Sound.MOB_BLAZE_DEATH;
		case ENTITY_BLAZE_HURT:
			return cn.nukkit.level.Sound.MOB_BLAZE_HIT;
		case ENTITY_BLAZE_SHOOT:
			return cn.nukkit.level.Sound.MOB_BLAZE_SHOOT;
		case ENTITY_CAT_AMBIENT:
			return cn.nukkit.level.Sound.MOB_OCELOT_IDLE;
		case ENTITY_CAT_DEATH:
			return cn.nukkit.level.Sound.MOB_OCELOT_DEATH;
		case ENTITY_CAT_HISS:
			return cn.nukkit.level.Sound.MOB_CAT_HISS;
		case ENTITY_CAT_HURT:
			return cn.nukkit.level.Sound.MOB_CAT_HIT;
		case ENTITY_CAT_PURR:
			return cn.nukkit.level.Sound.MOB_CAT_PURR;
		case ENTITY_CAT_PURREOW:
			return cn.nukkit.level.Sound.MOB_CAT_PURREOW;
		case ENTITY_CHICKEN_AMBIENT:
			return cn.nukkit.level.Sound.MOB_CHICKEN_SAY;
		case ENTITY_CHICKEN_DEATH:
			return cn.nukkit.level.Sound.MOB_CHICKEN_HURT;
		case ENTITY_CHICKEN_EGG:
			return cn.nukkit.level.Sound.MOB_CHICKEN_PLOP;
		case ENTITY_CHICKEN_HURT:
			return cn.nukkit.level.Sound.MOB_CHICKEN_HURT;
		case ENTITY_CHICKEN_STEP:
			return cn.nukkit.level.Sound.MOB_CHICKEN_STEP;
		case ENTITY_COW_AMBIENT:
			return cn.nukkit.level.Sound.MOB_COW_SAY;
		case ENTITY_COW_DEATH:
			return cn.nukkit.level.Sound.MOB_COW_HURT;
		case ENTITY_COW_HURT:
			return cn.nukkit.level.Sound.MOB_COW_HURT;
		case ENTITY_COW_MILK:
			return cn.nukkit.level.Sound.MOB_COW_MILK;
		case ENTITY_COW_STEP:
			return cn.nukkit.level.Sound.MOB_COW_STEP;
		case ENTITY_CREEPER_DEATH:
			return cn.nukkit.level.Sound.MOB_CREEPER_DEATH;
		case ENTITY_CREEPER_HURT:
			return cn.nukkit.level.Sound.MOB_CREEPER_SAY;
		case ENTITY_CREEPER_PRIMED:
			return cn.nukkit.level.Sound.RANDOM_FUSE;
		case ENTITY_DONKEY_AMBIENT:
			return cn.nukkit.level.Sound.MOB_HORSE_IDLE;
		case ENTITY_DONKEY_ANGRY:
			return cn.nukkit.level.Sound.MOB_HORSE_DONKEY_ANGRY;
		case ENTITY_DONKEY_CHEST:
			return cn.nukkit.level.Sound.MOB_HORSE_ARMOR;
		case ENTITY_DONKEY_DEATH:
			return cn.nukkit.level.Sound.MOB_HORSE_DONKEY_DEATH;
		case ENTITY_DONKEY_HURT:
			return cn.nukkit.level.Sound.MOB_HORSE_DONKEY_HIT;
		case ENTITY_EGG_THROW:
			return cn.nukkit.level.Sound.RANDOM_BOW;
		case ENTITY_ELDER_GUARDIAN_AMBIENT:
			return cn.nukkit.level.Sound.MOB_ELDERGUARDIAN_IDLE;
		case ENTITY_ELDER_GUARDIAN_AMBIENT_LAND:
			return cn.nukkit.level.Sound.MOB_GUARDIAN_LAND_IDLE;
		case ENTITY_ELDER_GUARDIAN_CURSE:
			return cn.nukkit.level.Sound.MOB_ELDERGUARDIAN_CURSE;
		case ENTITY_ELDER_GUARDIAN_DEATH:
			return cn.nukkit.level.Sound.MOB_ELDERGUARDIAN_DEATH;
		case ENTITY_ELDER_GUARDIAN_DEATH_LAND:
			return cn.nukkit.level.Sound.MOB_ELDERGUARDIAN_DEATH;
		case ENTITY_ELDER_GUARDIAN_HURT:
			return cn.nukkit.level.Sound.MOB_ELDERGUARDIAN_HIT;
		case ENTITY_ELDER_GUARDIAN_HURT_LAND:
			return cn.nukkit.level.Sound.MOB_GUARDIAN_LAND_HIT;
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
			return cn.nukkit.level.Sound.MOB_ENDERMITE_SAY;
		case ENTITY_ENDERMITE_DEATH:
			return cn.nukkit.level.Sound.MOB_ENDERMITE_KILL;
		case ENTITY_ENDERMITE_HURT:
			return cn.nukkit.level.Sound.MOB_ENDERMITE_HIT;
		case ENTITY_ENDERMITE_STEP:
			return cn.nukkit.level.Sound.MOB_ENDERMITE_STEP;
		case ENTITY_EXPERIENCE_BOTTLE_THROW:
			return cn.nukkit.level.Sound.RANDOM_BOW;
		case ENTITY_EXPERIENCE_ORB_PICKUP:
			return cn.nukkit.level.Sound.RANDOM_ORB;
		case ENTITY_GENERIC_BIG_FALL:
			return cn.nukkit.level.Sound.DAMAGE_FALLBIG;
		case ENTITY_GENERIC_BURN:
			break;
		case ENTITY_GENERIC_DEATH:
			return cn.nukkit.level.Sound.GAME_PLAYER_DIE;
		case ENTITY_GENERIC_DRINK:
			return cn.nukkit.level.Sound.RANDOM_DRINK;
		case ENTITY_GENERIC_EAT:
			return cn.nukkit.level.Sound.RANDOM_EAT;
		case ENTITY_GENERIC_EXPLODE:
			return cn.nukkit.level.Sound.RANDOM_EXPLODE;
		case ENTITY_GENERIC_EXTINGUISH_FIRE:
			return cn.nukkit.level.Sound.RANDOM_FIZZ;
		case ENTITY_GENERIC_HURT:
			return cn.nukkit.level.Sound.GAME_PLAYER_HURT;
		case ENTITY_GENERIC_SMALL_FALL:
			return cn.nukkit.level.Sound.DAMAGE_FALLSMALL;
		case ENTITY_GENERIC_SPLASH:
			return cn.nukkit.level.Sound.RANDOM_SPLASH;
		case ENTITY_GENERIC_SWIM:
			return cn.nukkit.level.Sound.RANDOM_SWIM;
		case ENTITY_GHAST_AMBIENT:
			return cn.nukkit.level.Sound.MOB_GHAST_MOAN;
		case ENTITY_GHAST_DEATH:
			return cn.nukkit.level.Sound.MOB_GHAST_DEATH;
		case ENTITY_GHAST_HURT:
			return cn.nukkit.level.Sound.MOB_GHAST_SCREAM;
		case ENTITY_GHAST_SCREAM:
			return cn.nukkit.level.Sound.MOB_GHAST_AFFECTIONATE_SCREAM;
		case ENTITY_GHAST_SHOOT:
			return cn.nukkit.level.Sound.MOB_GHAST_FIREBALL;
		case ENTITY_GHAST_WARN:
			return cn.nukkit.level.Sound.MOB_GHAST_CHARGE;
		case ENTITY_GUARDIAN_AMBIENT:
			return cn.nukkit.level.Sound.MOB_GUARDIAN_AMBIENT;
		case ENTITY_GUARDIAN_AMBIENT_LAND:
			return cn.nukkit.level.Sound.MOB_GUARDIAN_LAND_IDLE;
		case ENTITY_GUARDIAN_ATTACK:
			return cn.nukkit.level.Sound.MOB_GUARDIAN_ATTACK_LOOP;
		case ENTITY_GUARDIAN_DEATH:
			return cn.nukkit.level.Sound.MOB_GUARDIAN_DEATH;
		case ENTITY_GUARDIAN_DEATH_LAND:
			return cn.nukkit.level.Sound.MOB_GUARDIAN_LAND_DEATH;
		case ENTITY_GUARDIAN_FLOP:
			return cn.nukkit.level.Sound.MOB_GUARDIAN_FLOP;
		case ENTITY_GUARDIAN_HURT:
			return cn.nukkit.level.Sound.MOB_GUARDIAN_HIT;
		case ENTITY_GUARDIAN_HURT_LAND:
			return cn.nukkit.level.Sound.MOB_GUARDIAN_LAND_HIT;
		case ENTITY_HORSE_AMBIENT:
			return cn.nukkit.level.Sound.MOB_HORSE_IDLE;
		case ENTITY_HORSE_ANGRY:
			return cn.nukkit.level.Sound.MOB_HORSE_ANGRY;
		case ENTITY_HORSE_ARMOR:
			return cn.nukkit.level.Sound.MOB_HORSE_ARMOR;
		case ENTITY_HORSE_BREATHE:
			return cn.nukkit.level.Sound.MOB_HORSE_BREATHE;
		case ENTITY_HORSE_DEATH:
			return cn.nukkit.level.Sound.MOB_HORSE_DEATH;
		case ENTITY_HORSE_EAT:
			return cn.nukkit.level.Sound.MOB_HORSE_EAT;
		case ENTITY_HORSE_GALLOP:
			return cn.nukkit.level.Sound.MOB_HORSE_GALLOP;
		case ENTITY_HORSE_HURT:
			return cn.nukkit.level.Sound.MOB_HORSE_HIT;
		case ENTITY_HORSE_JUMP:
			return cn.nukkit.level.Sound.MOB_HORSE_JUMP;
		case ENTITY_HORSE_LAND:
			return cn.nukkit.level.Sound.MOB_HORSE_LAND;
		case ENTITY_HORSE_SADDLE:
			return cn.nukkit.level.Sound.MOB_HORSE_LEATHER;
		case ENTITY_HORSE_STEP:
			return cn.nukkit.level.Sound.MOB_HORSE_SOFT;
		case ENTITY_HORSE_STEP_WOOD:
			return cn.nukkit.level.Sound.MOB_HORSE_WOOD;
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
			return cn.nukkit.level.Sound.MOB_HUSK_AMBIENT;
		case ENTITY_HUSK_DEATH:
			return cn.nukkit.level.Sound.MOB_HUSK_DEATH;
		case ENTITY_HUSK_HURT:
			return cn.nukkit.level.Sound.MOB_HUSK_HURT;
		case ENTITY_HUSK_STEP:
			return cn.nukkit.level.Sound.MOB_HUSK_STEP;
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
			return cn.nukkit.level.Sound.MINECART_INSIDE;
		case ENTITY_MINECART_RIDING:
			return cn.nukkit.level.Sound.MINECART_BASE;
		case ENTITY_MOOSHROOM_SHEAR:
			return cn.nukkit.level.Sound.MOB_SHEEP_SHEAR;
		case ENTITY_MULE_AMBIENT:
			return cn.nukkit.level.Sound.MOB_HORSE_DONKEY_IDLE;
		case ENTITY_MULE_DEATH:
			return cn.nukkit.level.Sound.MOB_HORSE_DONKEY_DEATH;
		case ENTITY_MULE_HURT:
			return cn.nukkit.level.Sound.MOB_HORSE_DONKEY_HIT;
		case ENTITY_PAINTING_BREAK:
			break;
		case ENTITY_PAINTING_PLACE:
			break;
		case ENTITY_PIG_AMBIENT:
			return cn.nukkit.level.Sound.MOB_PIG_SAY;
		case ENTITY_PIG_DEATH:
			return cn.nukkit.level.Sound.MOB_PIG_DEATH;
		case ENTITY_PIG_HURT:
			return cn.nukkit.level.Sound.MOB_PIG_SAY;
		case ENTITY_PIG_SADDLE:
			return cn.nukkit.level.Sound.MOB_HORSE_LEATHER;
		case ENTITY_PIG_STEP:
			return cn.nukkit.level.Sound.MOB_PIG_STEP;
		case ENTITY_PLAYER_ATTACK_CRIT:
			break;
		case ENTITY_PLAYER_ATTACK_KNOCKBACK:
			break;
		case ENTITY_PLAYER_ATTACK_NODAMAGE:
			return cn.nukkit.level.Sound.GAME_PLAYER_ATTACK_NODAMAGE;
		case ENTITY_PLAYER_ATTACK_STRONG:
			return cn.nukkit.level.Sound.GAME_PLAYER_ATTACK_STRONG;
		case ENTITY_PLAYER_ATTACK_SWEEP:
			break;
		case ENTITY_PLAYER_ATTACK_WEAK:
			break;
		case ENTITY_PLAYER_BIG_FALL:
			return cn.nukkit.level.Sound.DAMAGE_FALLBIG;
		case ENTITY_PLAYER_BREATH:
			break;
		case ENTITY_PLAYER_BURP:
			return cn.nukkit.level.Sound.RANDOM_BURP;
		case ENTITY_PLAYER_DEATH:
			return cn.nukkit.level.Sound.GAME_PLAYER_DIE;
		case ENTITY_PLAYER_HURT:
			return cn.nukkit.level.Sound.GAME_PLAYER_HURT;
		case ENTITY_PLAYER_LEVELUP:
			return cn.nukkit.level.Sound.RANDOM_LEVELUP;
		case ENTITY_PLAYER_SMALL_FALL:
			return cn.nukkit.level.Sound.DAMAGE_FALLSMALL;
		case ENTITY_PLAYER_SPLASH:
			return cn.nukkit.level.Sound.RANDOM_SPLASH;
		case ENTITY_PLAYER_SWIM:
			return cn.nukkit.level.Sound.RANDOM_SWIM;
		case ENTITY_POLAR_BEAR_AMBIENT:
			return cn.nukkit.level.Sound.MOB_POLARBEAR_IDLE;
		case ENTITY_POLAR_BEAR_DEATH:
			return cn.nukkit.level.Sound.MOB_POLARBEAR_DEATH;
		case ENTITY_POLAR_BEAR_HURT:
			return cn.nukkit.level.Sound.MOB_POLARBEAR_HURT;
		case ENTITY_POLAR_BEAR_STEP:
			return cn.nukkit.level.Sound.MOB_POLARBEAR_STEP;
		case ENTITY_POLAR_BEAR_WARNING:
			return cn.nukkit.level.Sound.MOB_POLARBEAR_WARNING;
		case ENTITY_RABBIT_AMBIENT:
			return cn.nukkit.level.Sound.MOB_RABBIT_IDLE;
		case ENTITY_RABBIT_ATTACK:
			break;
		case ENTITY_RABBIT_DEATH:
			return cn.nukkit.level.Sound.MOB_RABBIT_DEATH;
		case ENTITY_RABBIT_HURT:
			return cn.nukkit.level.Sound.MOB_RABBIT_HURT;
		case ENTITY_RABBIT_JUMP:
			break;
		case ENTITY_SHEEP_AMBIENT:
			return cn.nukkit.level.Sound.MOB_SHEEP_SAY;
		case ENTITY_SHEEP_DEATH:
			return cn.nukkit.level.Sound.MOB_SHEEP_SAY;
		case ENTITY_SHEEP_HURT:
			return cn.nukkit.level.Sound.MOB_SHEEP_SAY;
		case ENTITY_SHEEP_SHEAR:
			return cn.nukkit.level.Sound.MOB_SHEEP_SHEAR;
		case ENTITY_SHEEP_STEP:
			return cn.nukkit.level.Sound.MOB_SHEEP_STEP;
		case ENTITY_SHULKER_AMBIENT:
			return cn.nukkit.level.Sound.MOB_SHULKER_AMBIENT;
		case ENTITY_SHULKER_BULLET_HIT:
			return cn.nukkit.level.Sound.MOB_SHULKER_BULLET_HIT;
		case ENTITY_SHULKER_BULLET_HURT:
			break;
		case ENTITY_SHULKER_CLOSE:
			return cn.nukkit.level.Sound.MOB_SHULKER_CLOSE;
		case ENTITY_SHULKER_DEATH:
			return cn.nukkit.level.Sound.MOB_SHULKER_DEATH;
		case ENTITY_SHULKER_HURT:
			return cn.nukkit.level.Sound.MOB_SHULKER_HURT;
		case ENTITY_SHULKER_HURT_CLOSED:
			return cn.nukkit.level.Sound.MOB_SHULKER_CLOSE_HURT;
		case ENTITY_SHULKER_OPEN:
			return cn.nukkit.level.Sound.MOB_SHULKER_OPEN;
		case ENTITY_SHULKER_SHOOT:
			return cn.nukkit.level.Sound.MOB_SHULKER_SHOOT;
		case ENTITY_SHULKER_TELEPORT:
			return cn.nukkit.level.Sound.MOB_SHULKER_TELEPORT;
		case ENTITY_SILVERFISH_AMBIENT:
			return cn.nukkit.level.Sound.MOB_SILVERFISH_SAY;
		case ENTITY_SILVERFISH_DEATH:
			return cn.nukkit.level.Sound.MOB_SILVERFISH_KILL;
		case ENTITY_SILVERFISH_HURT:
			return cn.nukkit.level.Sound.MOB_SILVERFISH_HIT;
		case ENTITY_SILVERFISH_STEP:
			return cn.nukkit.level.Sound.MOB_SILVERFISH_STEP;
		case ENTITY_SKELETON_AMBIENT:
			return cn.nukkit.level.Sound.MOB_SKELETON_SAY;
		case ENTITY_SKELETON_DEATH:
			return cn.nukkit.level.Sound.MOB_SKELETON_DEATH;
		case ENTITY_SKELETON_HORSE_AMBIENT:
			return cn.nukkit.level.Sound.MOB_HORSE_SKELETON_IDLE;
		case ENTITY_SKELETON_HORSE_DEATH:
			return cn.nukkit.level.Sound.MOB_HORSE_SKELETON_DEATH;
		case ENTITY_SKELETON_HORSE_HURT:
			return cn.nukkit.level.Sound.MOB_HORSE_SKELETON_HIT;
		case ENTITY_SKELETON_HURT:
			return cn.nukkit.level.Sound.MOB_SKELETON_HURT;
		case ENTITY_SKELETON_SHOOT:
			return cn.nukkit.level.Sound.RANDOM_BOW;
		case ENTITY_SKELETON_STEP:
			return cn.nukkit.level.Sound.MOB_SKELETON_STEP;
		case ENTITY_SLIME_ATTACK:
			return cn.nukkit.level.Sound.MOB_SLIME_ATTACK;
		case ENTITY_SLIME_DEATH:
			return cn.nukkit.level.Sound.MOB_SLIME_DEATH;
		case ENTITY_SLIME_HURT:
			return cn.nukkit.level.Sound.MOB_SLIME_HURT;
		case ENTITY_SLIME_JUMP:
			return cn.nukkit.level.Sound.MOB_SLIME_JUMP;
		case ENTITY_SLIME_SQUISH:
			return cn.nukkit.level.Sound.MOB_SLIME_SQUISH;
		case ENTITY_SNOWBALL_THROW:
			return cn.nukkit.level.Sound.RANDOM_BOW;
		case ENTITY_SPIDER_AMBIENT:
			return cn.nukkit.level.Sound.MOB_SPIDER_SAY;
		case ENTITY_SPIDER_DEATH:
			return cn.nukkit.level.Sound.MOB_SPIDER_DEATH;
		case ENTITY_SPIDER_HURT:
			return cn.nukkit.level.Sound.MOB_SPIDER_SAY;
		case ENTITY_SPIDER_STEP:
			return cn.nukkit.level.Sound.MOB_SPIDER_STEP;
		case ENTITY_SPLASH_POTION_BREAK:
			return cn.nukkit.level.Sound.RANDOM_GLASS;
		case ENTITY_SPLASH_POTION_THROW:
			return cn.nukkit.level.Sound.RANDOM_BOW;
		case ENTITY_SQUID_AMBIENT:
			return cn.nukkit.level.Sound.MOB_SQUID_AMBIENT;
		case ENTITY_SQUID_DEATH:
			return cn.nukkit.level.Sound.MOB_SQUID_DEATH;
		case ENTITY_SQUID_HURT:
			return cn.nukkit.level.Sound.MOB_SQUID_HURT;
		case ENTITY_STRAY_AMBIENT:
			return cn.nukkit.level.Sound.MOB_STRAY_AMBIENT;
		case ENTITY_STRAY_DEATH:
			return cn.nukkit.level.Sound.MOB_STRAY_DEATH;
		case ENTITY_STRAY_HURT:
			return cn.nukkit.level.Sound.MOB_STRAY_HURT;
		case ENTITY_STRAY_STEP:
			return cn.nukkit.level.Sound.MOB_STRAY_STEP;
		case ENTITY_TNT_PRIMED:
			return cn.nukkit.level.Sound.RANDOM_FUSE;
		case ENTITY_VILLAGER_AMBIENT:
			return cn.nukkit.level.Sound.MOB_VILLAGER_IDLE;
		case ENTITY_VILLAGER_DEATH:
			return cn.nukkit.level.Sound.MOB_VILLAGER_DEATH;
		case ENTITY_VILLAGER_HURT:
			return cn.nukkit.level.Sound.MOB_VILLAGER_HIT;
		case ENTITY_VILLAGER_NO:
			return cn.nukkit.level.Sound.MOB_VILLAGER_NO;
		case ENTITY_VILLAGER_YES:
			return cn.nukkit.level.Sound.MOB_VILLAGER_YES;
		case ENTITY_WITCH_AMBIENT:
			return cn.nukkit.level.Sound.MOB_WITCH_AMBIENT;
		case ENTITY_WITCH_DEATH:
			return cn.nukkit.level.Sound.MOB_WITCH_DEATH;
		case ENTITY_WITCH_DRINK:
			return cn.nukkit.level.Sound.MOB_WITCH_DRINK;
		case ENTITY_WITCH_HURT:
			return cn.nukkit.level.Sound.MOB_WITCH_HURT;
		case ENTITY_WITCH_THROW:
			return cn.nukkit.level.Sound.MOB_WITCH_THROW;
		case ENTITY_WITHER_AMBIENT:
			return cn.nukkit.level.Sound.MOB_WITHER_AMBIENT;
		case ENTITY_WITHER_BREAK_BLOCK:
			return cn.nukkit.level.Sound.MOB_WITHER_BREAK_BLOCK;
		case ENTITY_WITHER_DEATH:
			return cn.nukkit.level.Sound.MOB_WITHER_DEATH;
		case ENTITY_WITHER_HURT:
			return cn.nukkit.level.Sound.MOB_WITHER_HURT;
		case ENTITY_WITHER_SHOOT:
			return cn.nukkit.level.Sound.MOB_WITHER_SHOOT;
		case ENTITY_WITHER_SKELETON_AMBIENT:
			return cn.nukkit.level.Sound.MOB_SKELETON_SAY;
		case ENTITY_WITHER_SKELETON_DEATH:
			return cn.nukkit.level.Sound.MOB_SKELETON_DEATH;
		case ENTITY_WITHER_SKELETON_HURT:
			return cn.nukkit.level.Sound.MOB_SKELETON_HURT;
		case ENTITY_WITHER_SKELETON_STEP:
			return cn.nukkit.level.Sound.MOB_SKELETON_STEP;
		case ENTITY_WITHER_SPAWN:
			return cn.nukkit.level.Sound.MOB_WITHER_SPAWN;
		case ENTITY_WOLF_AMBIENT:
			return cn.nukkit.level.Sound.MOB_WOLF_BARK;
		case ENTITY_WOLF_DEATH:
			return cn.nukkit.level.Sound.MOB_WOLF_DEATH;
		case ENTITY_WOLF_GROWL:
			return cn.nukkit.level.Sound.MOB_WOLF_GROWL;
		case ENTITY_WOLF_HOWL:
			break;
		case ENTITY_WOLF_HURT:
			return cn.nukkit.level.Sound.MOB_WOLF_HURT;
		case ENTITY_WOLF_PANT:
			return cn.nukkit.level.Sound.MOB_WOLF_PANTING;
		case ENTITY_WOLF_SHAKE:
			return cn.nukkit.level.Sound.MOB_WOLF_SHAKE;
		case ENTITY_WOLF_STEP:
			return cn.nukkit.level.Sound.MOB_WOLF_STEP;
		case ENTITY_WOLF_WHINE:
			return cn.nukkit.level.Sound.MOB_WOLF_WHINE;
		case ENTITY_ZOMBIE_AMBIENT:
			return cn.nukkit.level.Sound.MOB_ZOMBIE_SAY;
		case ENTITY_ZOMBIE_ATTACK_IRON_DOOR:
			break;
		case ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR:
			return cn.nukkit.level.Sound.MOB_ZOMBIE_WOOD;
		case ENTITY_ZOMBIE_BREAK_WOODEN_DOOR:
			return cn.nukkit.level.Sound.MOB_ZOMBIE_WOODBREAK;
		case ENTITY_ZOMBIE_DEATH:
			return cn.nukkit.level.Sound.MOB_ZOMBIE_DEATH;
		case ENTITY_ZOMBIE_HORSE_AMBIENT:
			return cn.nukkit.level.Sound.MOB_HORSE_ZOMBIE_IDLE;
		case ENTITY_ZOMBIE_HORSE_DEATH:
			return cn.nukkit.level.Sound.MOB_HORSE_ZOMBIE_DEATH;
		case ENTITY_ZOMBIE_HORSE_HURT:
			return cn.nukkit.level.Sound.MOB_HORSE_ZOMBIE_HIT;
		case ENTITY_ZOMBIE_HURT:
			return cn.nukkit.level.Sound.MOB_ZOMBIE_HURT;
		case ENTITY_ZOMBIE_STEP:
			return cn.nukkit.level.Sound.MOB_ZOMBIE_STEP;
		case ENTITY_ZOMBIE_VILLAGER_AMBIENT:
			return cn.nukkit.level.Sound.MOB_ZOMBIE_VILLAGER_SAY;
		case ENTITY_ZOMBIE_VILLAGER_CONVERTED:
			break;
		case ENTITY_ZOMBIE_VILLAGER_DEATH:
			return cn.nukkit.level.Sound.MOB_ZOMBIE_VILLAGER_DEATH;
		case ENTITY_ZOMBIE_VILLAGER_HURT:
			return cn.nukkit.level.Sound.MOB_ZOMBIE_VILLAGER_HURT;
		case ENTITY_ZOMBIE_VILLAGER_STEP:
			return cn.nukkit.level.Sound.MOB_ZOMBIE_STEP;
		case ITEM_ARMOR_EQUIP_CHAIN:
			return cn.nukkit.level.Sound.ARMOR_EQUIP_CHAIN;
		case ITEM_ARMOR_EQUIP_DIAMOND:
			return cn.nukkit.level.Sound.ARMOR_EQUIP_DIAMOND;
		case ITEM_ARMOR_EQUIP_GENERIC:
			return cn.nukkit.level.Sound.ARMOR_EQUIP_GENERIC;
		case ITEM_ARMOR_EQUIP_GOLD:
			return cn.nukkit.level.Sound.ARMOR_EQUIP_GOLD;
		case ITEM_ARMOR_EQUIP_IRON:
			return cn.nukkit.level.Sound.ARMOR_EQUIP_IRON;
		case ITEM_ARMOR_EQUIP_LEATHER:
			return cn.nukkit.level.Sound.ARMOR_EQUIP_LEATHER;
		case ITEM_BOTTLE_FILL:
			break;
		case ITEM_BOTTLE_FILL_DRAGONBREATH:
			return cn.nukkit.level.Sound.BOTTLE_DRAGONBREATH;
		case ITEM_BUCKET_EMPTY:
			return cn.nukkit.level.Sound.BUCKET_EMPTY_WATER;
		case ITEM_BUCKET_EMPTY_LAVA:
			return cn.nukkit.level.Sound.BUCKET_EMPTY_LAVA;
		case ITEM_BUCKET_FILL:
			return cn.nukkit.level.Sound.BUCKET_FILL_WATER;
		case ITEM_BUCKET_FILL_LAVA:
			return cn.nukkit.level.Sound.BUCKET_FILL_LAVA;
		case ITEM_CHORUS_FRUIT_TELEPORT:
			break;
		case ITEM_ELYTRA_FLYING:
			return cn.nukkit.level.Sound.ELYTRA_LOOP;
		case ITEM_FIRECHARGE_USE:
			break;
		case ITEM_FLINTANDSTEEL_USE:
			break;
		case ITEM_HOE_TILL:
			break;
		case ITEM_SHIELD_BLOCK:
			return cn.nukkit.level.Sound.ITEM_SHIELD_BLOCK;
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
			return cn.nukkit.level.Sound.RECORD_11;
		case MUSIC_DISC_13:
			return cn.nukkit.level.Sound.RECORD_13;
		case MUSIC_DISC_BLOCKS:
			return cn.nukkit.level.Sound.RECORD_BLOCKS;
		case MUSIC_DISC_CAT:
			return cn.nukkit.level.Sound.RECORD_CAT;
		case MUSIC_DISC_CHIRP:
			return cn.nukkit.level.Sound.RECORD_CHIRP;
		case MUSIC_DISC_FAR:
			return cn.nukkit.level.Sound.RECORD_FAR;
		case MUSIC_DISC_MALL:
			return cn.nukkit.level.Sound.RECORD_MALL;
		case MUSIC_DISC_MELLOHI:
			return cn.nukkit.level.Sound.RECORD_MELLOHI;
		case MUSIC_DISC_STAL:
			return cn.nukkit.level.Sound.RECORD_STAL;
		case MUSIC_DISC_STRAD:
			return cn.nukkit.level.Sound.RECORD_STRAD;
		case MUSIC_DISC_WAIT:
			return cn.nukkit.level.Sound.RECORD_WAIT;
		case MUSIC_DISC_WARD:
			return cn.nukkit.level.Sound.RECORD_WARD;
		case UI_BUTTON_CLICK:
			return cn.nukkit.level.Sound.RANDOM_CLICK;
		case WEATHER_RAIN:
			return cn.nukkit.level.Sound.AMBIENT_WEATHER_RAIN;
		case WEATHER_RAIN_ABOVE:
			break;
		// Added in Minecraft 1.11:
		case BLOCK_SHULKER_BOX_CLOSE:
			return cn.nukkit.level.Sound.RANDOM_SHULKERBOXCLOSED;
		case BLOCK_SHULKER_BOX_OPEN:
			return cn.nukkit.level.Sound.RANDOM_SHULKERBOXOPEN;
		case ENTITY_ELDER_GUARDIAN_FLOP:
			return cn.nukkit.level.Sound.MOB_GUARDIAN_FLOP;
		case ENTITY_LLAMA_AMBIENT:
			return cn.nukkit.level.Sound.MOB_LLAMA_IDLE;
		case ENTITY_LLAMA_ANGRY:
			return cn.nukkit.level.Sound.MOB_LLAMA_ANGRY;
		case ENTITY_LLAMA_CHEST:
			return cn.nukkit.level.Sound.MOB_HORSE_ARMOR;
		case ENTITY_LLAMA_DEATH:
			return cn.nukkit.level.Sound.MOB_LLAMA_DEATH;
		case ENTITY_LLAMA_EAT:
			return cn.nukkit.level.Sound.MOB_LLAMA_EAT;
		case ENTITY_LLAMA_HURT:
			return cn.nukkit.level.Sound.MOB_LLAMA_HURT;
		case ENTITY_LLAMA_SPIT:
			return cn.nukkit.level.Sound.MOB_LLAMA_SPIT;
		case ENTITY_LLAMA_STEP:
			return cn.nukkit.level.Sound.MOB_LLAMA_STEP;
		case ENTITY_LLAMA_SWAG:
			return cn.nukkit.level.Sound.MOB_LLAMA_SWAG;
		case ENTITY_MULE_CHEST:
			return cn.nukkit.level.Sound.MOB_HORSE_ARMOR;
		case ENTITY_VEX_AMBIENT:
			return cn.nukkit.level.Sound.MOB_VEX_AMBIENT;
		case ENTITY_VEX_CHARGE:
			return cn.nukkit.level.Sound.MOB_VEX_CHARGE;
		case ENTITY_VEX_DEATH:
			return cn.nukkit.level.Sound.MOB_VEX_DEATH;
		case ENTITY_VEX_HURT:
			return cn.nukkit.level.Sound.MOB_VEX_HURT;
		case ITEM_ARMOR_EQUIP_ELYTRA:
			return cn.nukkit.level.Sound.ARMOR_EQUIP_GENERIC;
		case ITEM_BOTTLE_EMPTY:
			break;
		case ITEM_TOTEM_USE:
			return cn.nukkit.level.Sound.RANDOM_TOTEM;
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
			return cn.nukkit.level.Sound.BEACON_ACTIVATE;
		case BLOCK_BEACON_AMBIENT:
			return cn.nukkit.level.Sound.BEACON_AMBIENT;
		case BLOCK_BEACON_DEACTIVATE:
			return cn.nukkit.level.Sound.BEACON_DEACTIVATE;
		case BLOCK_BEACON_POWER_SELECT:
			return cn.nukkit.level.Sound.BEACON_POWER;
		case BLOCK_BUBBLE_COLUMN_BUBBLE_POP:
			return cn.nukkit.level.Sound.BUBBLE_POP;
		case BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT:
			return cn.nukkit.level.Sound.BUBBLE_UP;
		case BLOCK_BUBBLE_COLUMN_UPWARDS_INSIDE:
			return cn.nukkit.level.Sound.BUBBLE_UPINSIDE;
		case BLOCK_BUBBLE_COLUMN_WHIRLPOOL_AMBIENT:
			return cn.nukkit.level.Sound.BUBBLE_DOWN;
		case BLOCK_BUBBLE_COLUMN_WHIRLPOOL_INSIDE:
			return cn.nukkit.level.Sound.BUBBLE_DOWNINSIDE;
		case BLOCK_CONDUIT_ACTIVATE:
			return cn.nukkit.level.Sound.CONDUIT_ACTIVATE;
		case BLOCK_CONDUIT_AMBIENT:
			return cn.nukkit.level.Sound.CONDUIT_AMBIENT;
		case BLOCK_CONDUIT_AMBIENT_SHORT:
			return cn.nukkit.level.Sound.CONDUIT_SHORT;
		case BLOCK_CONDUIT_ATTACK_TARGET:
			return cn.nukkit.level.Sound.CONDUIT_ATTACK;
		case BLOCK_CONDUIT_DEACTIVATE:
			return cn.nukkit.level.Sound.CONDUIT_DEACTIVATE;
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
			return cn.nukkit.level.Sound.RANDOM_ENDERCHESTCLOSED;
		case BLOCK_ENDER_CHEST_OPEN:
			return cn.nukkit.level.Sound.RANDOM_ENDERCHESTOPEN;
		case BLOCK_END_PORTAL_FRAME_FILL:
			return cn.nukkit.level.Sound.BLOCK_END_PORTAL_FRAME_FILL;
		case BLOCK_END_PORTAL_SPAWN:
			return cn.nukkit.level.Sound.BLOCK_END_PORTAL_SPAWN;
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
			return cn.nukkit.level.Sound.MOB_SLIME_BIG;
		case BLOCK_SLIME_BLOCK_FALL:
			return cn.nukkit.level.Sound.FALL_SLIME;
		case BLOCK_SLIME_BLOCK_HIT:
			return cn.nukkit.level.Sound.HIT_SLIME;
		case BLOCK_SLIME_BLOCK_PLACE:
			return cn.nukkit.level.Sound.MOB_SLIME_BIG;
		case BLOCK_SLIME_BLOCK_STEP:
			return cn.nukkit.level.Sound.STEP_SLIME;
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
			return cn.nukkit.level.Sound.MOB_ARMOR_STAND_BREAK;
		case ENTITY_ARMOR_STAND_FALL:
			return cn.nukkit.level.Sound.MOB_ARMOR_STAND_LAND;
		case ENTITY_ARMOR_STAND_HIT:
			return cn.nukkit.level.Sound.MOB_ARMOR_STAND_HIT;
		case ENTITY_ARMOR_STAND_PLACE:
			return cn.nukkit.level.Sound.MOB_ARMOR_STAND_PLACE;
		case ENTITY_BAT_TAKEOFF:
			return cn.nukkit.level.Sound.MOB_BAT_TAKEOFF;
		case ENTITY_BOAT_PADDLE_LAND:
			break;
		case ENTITY_BOAT_PADDLE_WATER:
			break;
		case ENTITY_COD_AMBIENT:
			break;
		case ENTITY_COD_DEATH:
			break;
		case ENTITY_COD_FLOP:
			return cn.nukkit.level.Sound.MOB_FISH_FLOP;
		case ENTITY_COD_HURT:
			return cn.nukkit.level.Sound.MOB_FISH_HURT;
		case ENTITY_DOLPHIN_AMBIENT:
			return cn.nukkit.level.Sound.MOB_DOLPHIN_IDLE;
		case ENTITY_DOLPHIN_AMBIENT_WATER:
			return cn.nukkit.level.Sound.MOB_DOLPHIN_IDLE_WATER;
		case ENTITY_DOLPHIN_ATTACK:
			return cn.nukkit.level.Sound.MOB_DOLPHIN_ATTACK;
		case ENTITY_DOLPHIN_DEATH:
			return cn.nukkit.level.Sound.MOB_DOLPHIN_DEATH;
		case ENTITY_DOLPHIN_EAT:
			return cn.nukkit.level.Sound.MOB_DOLPHIN_EAT;
		case ENTITY_DOLPHIN_HURT:
			return cn.nukkit.level.Sound.MOB_DOLPHIN_HURT;
		case ENTITY_DOLPHIN_JUMP:
			return cn.nukkit.level.Sound.MOB_DOLPHIN_JUMP;
		case ENTITY_DOLPHIN_PLAY:
			return cn.nukkit.level.Sound.MOB_DOLPHIN_PLAY;
		case ENTITY_DOLPHIN_SPLASH:
			return cn.nukkit.level.Sound.MOB_DOLPHIN_SPLASH;
		case ENTITY_DOLPHIN_SWIM:
			return cn.nukkit.level.Sound.MOB_DOLPHIN_SWIM;
		case ENTITY_DRAGON_FIREBALL_EXPLODE:
			break;
		case ENTITY_DROWNED_AMBIENT:
			return cn.nukkit.level.Sound.MOB_DROWNED_SAY;
		case ENTITY_DROWNED_AMBIENT_WATER:
			return cn.nukkit.level.Sound.MOB_DROWNED_SAY_WATER;
		case ENTITY_DROWNED_DEATH:
			return cn.nukkit.level.Sound.MOB_DROWNED_DEATH;
		case ENTITY_DROWNED_DEATH_WATER:
			return cn.nukkit.level.Sound.MOB_DROWNED_DEATH_WATER;
		case ENTITY_DROWNED_HURT:
			return cn.nukkit.level.Sound.MOB_DROWNED_HURT;
		case ENTITY_DROWNED_HURT_WATER:
			return cn.nukkit.level.Sound.MOB_DROWNED_HURT_WATER;
		case ENTITY_DROWNED_SHOOT:
			return cn.nukkit.level.Sound.MOB_DROWNED_SHOOT;
		case ENTITY_DROWNED_STEP:
			return cn.nukkit.level.Sound.MOB_DROWNED_STEP;
		case ENTITY_DROWNED_SWIM:
			return cn.nukkit.level.Sound.MOB_DROWNED_SWIM;
		case ENTITY_ENDER_DRAGON_AMBIENT:
			break;
		case ENTITY_ENDER_DRAGON_DEATH:
			return cn.nukkit.level.Sound.MOB_ENDERDRAGON_DEATH;
		case ENTITY_ENDER_DRAGON_FLAP:
			return cn.nukkit.level.Sound.MOB_ENDERDRAGON_FLAP;
		case ENTITY_ENDER_DRAGON_GROWL:
			return cn.nukkit.level.Sound.MOB_ENDERDRAGON_GROWL;
		case ENTITY_ENDER_DRAGON_HURT:
			return cn.nukkit.level.Sound.MOB_ENDERDRAGON_HIT;
		case ENTITY_ENDER_DRAGON_SHOOT:
			break;
		case ENTITY_ENDER_EYE_DEATH:
			break;
		case ENTITY_ENDER_EYE_LAUNCH:
			break;
		case ENTITY_ENDER_PEARL_THROW:
			return cn.nukkit.level.Sound.RANDOM_BOW;
		case ENTITY_EVOKER_AMBIENT:
			return cn.nukkit.level.Sound.MOB_EVOCATION_ILLAGER_AMBIENT;
		case ENTITY_EVOKER_CAST_SPELL:
			return cn.nukkit.level.Sound.MOB_EVOCATION_ILLAGER_CAST_SPELL;
		case ENTITY_EVOKER_DEATH:
			return cn.nukkit.level.Sound.MOB_EVOCATION_ILLAGER_DEATH;
		case ENTITY_EVOKER_FANGS_ATTACK:
			return cn.nukkit.level.Sound.MOB_EVOCATION_FANGS_ATTACK;
		case ENTITY_EVOKER_HURT:
			return cn.nukkit.level.Sound.MOB_EVOCATION_ILLAGER_HURT;
		case ENTITY_EVOKER_PREPARE_ATTACK:
			return cn.nukkit.level.Sound.MOB_EVOCATION_ILLAGER_PREPARE_ATTACK;
		case ENTITY_EVOKER_PREPARE_SUMMON:
			return cn.nukkit.level.Sound.MOB_EVOCATION_ILLAGER_PREPARE_SUMMON;
		case ENTITY_EVOKER_PREPARE_WOLOLO:
			return cn.nukkit.level.Sound.MOB_EVOCATION_ILLAGER_PREPARE_WOLOLO;
		case ENTITY_FIREWORK_ROCKET_BLAST:
			return cn.nukkit.level.Sound.FIREWORK_BLAST;
		case ENTITY_FIREWORK_ROCKET_BLAST_FAR:
			return cn.nukkit.level.Sound.FIREWORK_BLAST;
		case ENTITY_FIREWORK_ROCKET_LARGE_BLAST:
			return cn.nukkit.level.Sound.FIREWORK_LARGE_BLAST;
		case ENTITY_FIREWORK_ROCKET_LARGE_BLAST_FAR:
			return cn.nukkit.level.Sound.FIREWORK_LARGE_BLAST;
		case ENTITY_FIREWORK_ROCKET_LAUNCH:
			return cn.nukkit.level.Sound.FIREWORK_LAUNCH;
		case ENTITY_FIREWORK_ROCKET_SHOOT:
			return cn.nukkit.level.Sound.FIREWORK_SHOOT;
		case ENTITY_FIREWORK_ROCKET_TWINKLE:
			return cn.nukkit.level.Sound.FIREWORK_TWINKLE;
		case ENTITY_FIREWORK_ROCKET_TWINKLE_FAR:
			return cn.nukkit.level.Sound.FIREWORK_TWINKLE;
		case ENTITY_FISHING_BOBBER_RETRIEVE:
			break;
		case ENTITY_FISHING_BOBBER_SPLASH:
			break;
		case ENTITY_FISHING_BOBBER_THROW:
			return cn.nukkit.level.Sound.RANDOM_BOW;
		case ENTITY_FISH_SWIM:
			break;
		case ENTITY_HUSK_CONVERTED_TO_ZOMBIE:
			return cn.nukkit.level.Sound.ENTITY_ZOMBIE_CONVERTED_TO_DROWNED;
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
			return cn.nukkit.level.Sound.MOB_IRONGOLEM_THROW;
		case ENTITY_IRON_GOLEM_DEATH:
			return cn.nukkit.level.Sound.MOB_IRONGOLEM_DEATH;
		case ENTITY_IRON_GOLEM_HURT:
			return cn.nukkit.level.Sound.MOB_IRONGOLEM_HIT;
		case ENTITY_IRON_GOLEM_STEP:
			return cn.nukkit.level.Sound.MOB_IRONGOLEM_WALK;
		case ENTITY_LEASH_KNOT_BREAK:
			return cn.nukkit.level.Sound.LEASHKNOT_BREAK;
		case ENTITY_LEASH_KNOT_PLACE:
			return cn.nukkit.level.Sound.LEASHKNOT_PLACE;
		case ENTITY_LIGHTNING_BOLT_IMPACT:
			return cn.nukkit.level.Sound.AMBIENT_WEATHER_LIGHTNING_IMPACT;
		case ENTITY_LIGHTNING_BOLT_THUNDER:
			return cn.nukkit.level.Sound.AMBIENT_WEATHER_THUNDER;
		case ENTITY_LINGERING_POTION_THROW:
			return cn.nukkit.level.Sound.RANDOM_BOW;
		case ENTITY_MAGMA_CUBE_DEATH:
			return cn.nukkit.level.Sound.MOB_MAGMACUBE_BIG;
		case ENTITY_MAGMA_CUBE_DEATH_SMALL:
			return cn.nukkit.level.Sound.MOB_MAGMACUBE_SMALL;
		case ENTITY_MAGMA_CUBE_HURT:
			return cn.nukkit.level.Sound.MOB_MAGMACUBE_BIG;
		case ENTITY_MAGMA_CUBE_HURT_SMALL:
			return cn.nukkit.level.Sound.MOB_MAGMACUBE_SMALL;
		case ENTITY_MAGMA_CUBE_JUMP:
			return cn.nukkit.level.Sound.MOB_MAGMACUBE_JUMP;
		case ENTITY_MAGMA_CUBE_SQUISH:
			return cn.nukkit.level.Sound.MOB_MAGMACUBE_BIG;
		case ENTITY_MAGMA_CUBE_SQUISH_SMALL:
			return cn.nukkit.level.Sound.MOB_MAGMACUBE_SMALL;
		case ENTITY_PARROT_AMBIENT:
			return cn.nukkit.level.Sound.MOB_PARROT_IDLE;
		case ENTITY_PARROT_DEATH:
			return cn.nukkit.level.Sound.MOB_PARROT_DEATH;
		case ENTITY_PARROT_EAT:
			return cn.nukkit.level.Sound.MOB_PARROT_EAT;
		case ENTITY_PARROT_FLY:
			return cn.nukkit.level.Sound.MOB_PARROT_FLY;
		case ENTITY_PARROT_HURT:
			return cn.nukkit.level.Sound.MOB_PARROT_HURT;
		case ENTITY_PARROT_IMITATE_BLAZE:
			return cn.nukkit.level.Sound.MOB_BLAZE_BREATHE;
		case ENTITY_PARROT_IMITATE_CREEPER:
			return cn.nukkit.level.Sound.RANDOM_FUSE;
		case ENTITY_PARROT_IMITATE_DROWNED:
			return cn.nukkit.level.Sound.MOB_ZOMBIE_SAY;
		case ENTITY_PARROT_IMITATE_ELDER_GUARDIAN:
			return cn.nukkit.level.Sound.MOB_GUARDIAN_LAND_IDLE;
		case ENTITY_PARROT_IMITATE_ENDERMAN:
			return cn.nukkit.level.Sound.MOB_ENDERMEN_IDLE;
		case ENTITY_PARROT_IMITATE_ENDERMITE:
			return cn.nukkit.level.Sound.MOB_ENDERMITE_SAY;
		case ENTITY_PARROT_IMITATE_ENDER_DRAGON:
			return cn.nukkit.level.Sound.MOB_ENDERDRAGON_GROWL;
		case ENTITY_PARROT_IMITATE_EVOKER:
			return cn.nukkit.level.Sound.MOB_EVOCATION_ILLAGER_AMBIENT;
		case ENTITY_PARROT_IMITATE_GHAST:
			return cn.nukkit.level.Sound.MOB_GHAST_MOAN;
		case ENTITY_PARROT_IMITATE_HUSK:
			return cn.nukkit.level.Sound.MOB_HUSK_AMBIENT;
		case ENTITY_PARROT_IMITATE_ILLUSIONER:
			break;
		case ENTITY_PARROT_IMITATE_MAGMA_CUBE:
			return cn.nukkit.level.Sound.MOB_MAGMACUBE_BIG;
		case ENTITY_PARROT_IMITATE_PHANTOM:
			return cn.nukkit.level.Sound.MOB_PHANTOM_IDLE;
		case ENTITY_PARROT_IMITATE_POLAR_BEAR:
			return cn.nukkit.level.Sound.MOB_POLARBEAR_IDLE;
		case ENTITY_PARROT_IMITATE_SHULKER:
			return cn.nukkit.level.Sound.MOB_SHULKER_AMBIENT;
		case ENTITY_PARROT_IMITATE_SILVERFISH:
			return cn.nukkit.level.Sound.MOB_SILVERFISH_SAY;
		case ENTITY_PARROT_IMITATE_SKELETON:
			return cn.nukkit.level.Sound.MOB_SKELETON_SAY;
		case ENTITY_PARROT_IMITATE_SLIME:
			return cn.nukkit.level.Sound.MOB_SLIME_BIG;
		case ENTITY_PARROT_IMITATE_SPIDER:
			return cn.nukkit.level.Sound.MOB_SPIDER_SAY;
		case ENTITY_PARROT_IMITATE_STRAY:
			return cn.nukkit.level.Sound.MOB_STRAY_AMBIENT;
		case ENTITY_PARROT_IMITATE_VEX:
			return cn.nukkit.level.Sound.MOB_VEX_AMBIENT;
		case ENTITY_PARROT_IMITATE_VINDICATOR:
			return cn.nukkit.level.Sound.MOB_VINDICATOR_IDLE;
		case ENTITY_PARROT_IMITATE_WITCH:
			return cn.nukkit.level.Sound.MOB_WITCH_AMBIENT;
		case ENTITY_PARROT_IMITATE_WITHER:
			return cn.nukkit.level.Sound.MOB_WITHER_AMBIENT;
		case ENTITY_PARROT_IMITATE_WITHER_SKELETON:
			return cn.nukkit.level.Sound.MOB_SKELETON_SAY;
		case ENTITY_PARROT_IMITATE_WOLF:
			return cn.nukkit.level.Sound.MOB_WOLF_BARK;
		case ENTITY_PARROT_IMITATE_ZOMBIE:
			return cn.nukkit.level.Sound.MOB_ZOMBIE_SAY;
		case ENTITY_PARROT_IMITATE_ZOMBIE_PIGMAN:
			return cn.nukkit.level.Sound.MOB_ZOMBIEPIG_ZPIG;
		case ENTITY_PARROT_IMITATE_ZOMBIE_VILLAGER:
			return cn.nukkit.level.Sound.MOB_ZOMBIE_VILLAGER_SAY;
		case ENTITY_PARROT_STEP:
			return cn.nukkit.level.Sound.MOB_PARROT_STEP;
		case ENTITY_PHANTOM_AMBIENT:
			return cn.nukkit.level.Sound.MOB_PHANTOM_IDLE;
		case ENTITY_PHANTOM_BITE:
			return cn.nukkit.level.Sound.MOB_PHANTOM_BITE;
		case ENTITY_PHANTOM_DEATH:
			return cn.nukkit.level.Sound.MOB_PHANTOM_DEATH;
		case ENTITY_PHANTOM_FLAP:
			break;
		case ENTITY_PHANTOM_HURT:
			return cn.nukkit.level.Sound.MOB_PHANTOM_HURT;
		case ENTITY_PHANTOM_SWOOP:
			return cn.nukkit.level.Sound.MOB_PHANTOM_SWOOP;
		case ENTITY_PLAYER_HURT_DROWN:
			return cn.nukkit.level.Sound.GAME_PLAYER_HURT;
		case ENTITY_PLAYER_HURT_ON_FIRE:
			return cn.nukkit.level.Sound.GAME_PLAYER_HURT;
		case ENTITY_PLAYER_SPLASH_HIGH_SPEED:
			return cn.nukkit.level.Sound.RANDOM_SPLASH;
		case ENTITY_POLAR_BEAR_AMBIENT_BABY:
			return cn.nukkit.level.Sound.MOB_POLARBEAR_BABY_IDLE;
		case ENTITY_PUFFER_FISH_AMBIENT:
			break;
		case ENTITY_PUFFER_FISH_BLOW_OUT:
			break;
		case ENTITY_PUFFER_FISH_BLOW_UP:
			break;
		case ENTITY_PUFFER_FISH_DEATH:
			break;
		case ENTITY_PUFFER_FISH_FLOP:
			return cn.nukkit.level.Sound.MOB_FISH_FLOP;
		case ENTITY_PUFFER_FISH_HURT:
			return cn.nukkit.level.Sound.MOB_FISH_HURT;
		case ENTITY_PUFFER_FISH_STING:
			break;
		case ENTITY_SALMON_AMBIENT:
			break;
		case ENTITY_SALMON_DEATH:
			break;
		case ENTITY_SALMON_FLOP:
			return cn.nukkit.level.Sound.MOB_FISH_FLOP;
		case ENTITY_SALMON_HURT:
			return cn.nukkit.level.Sound.MOB_FISH_HURT;
		case ENTITY_SKELETON_HORSE_AMBIENT_WATER:
			return cn.nukkit.level.Sound.MOB_HORSE_SKELETON_IDLE;
		case ENTITY_SKELETON_HORSE_GALLOP_WATER:
			return cn.nukkit.level.Sound.MOB_HORSE_GALLOP;
		case ENTITY_SKELETON_HORSE_JUMP_WATER:
			return cn.nukkit.level.Sound.MOB_HORSE_JUMP;
		case ENTITY_SKELETON_HORSE_STEP_WATER:
			return cn.nukkit.level.Sound.MOB_HORSE_SOFT;
		case ENTITY_SKELETON_HORSE_SWIM:
			break;
		case ENTITY_SLIME_DEATH_SMALL:
			return cn.nukkit.level.Sound.MOB_SLIME_SMALL;
		case ENTITY_SLIME_HURT_SMALL:
			return cn.nukkit.level.Sound.MOB_SLIME_SMALL;
		case ENTITY_SLIME_JUMP_SMALL:
			return cn.nukkit.level.Sound.MOB_SLIME_SMALL;
		case ENTITY_SLIME_SQUISH_SMALL:
			return cn.nukkit.level.Sound.MOB_SLIME_SMALL;
		case ENTITY_SNOW_GOLEM_AMBIENT:
			break;
		case ENTITY_SNOW_GOLEM_DEATH:
			return cn.nukkit.level.Sound.MOB_SNOWGOLEM_DEATH;
		case ENTITY_SNOW_GOLEM_HURT:
			return cn.nukkit.level.Sound.MOB_SNOWGOLEM_HURT;
		case ENTITY_SNOW_GOLEM_SHOOT:
			return cn.nukkit.level.Sound.MOB_SNOWGOLEM_SHOOT;
		case ENTITY_SQUID_SQUIRT:
			break;
		case ENTITY_TROPICAL_FISH_AMBIENT:
			break;
		case ENTITY_TROPICAL_FISH_DEATH:
			break;
		case ENTITY_TROPICAL_FISH_FLOP:
			return cn.nukkit.level.Sound.MOB_FISH_FLOP;
		case ENTITY_TROPICAL_FISH_HURT:
			return cn.nukkit.level.Sound.MOB_FISH_HURT;
		case ENTITY_TURTLE_AMBIENT_LAND:
			return cn.nukkit.level.Sound.MOB_TURTLE_AMBIENT;
		case ENTITY_TURTLE_DEATH:
			return cn.nukkit.level.Sound.MOB_TURTLE_DEATH;
		case ENTITY_TURTLE_DEATH_BABY:
			return cn.nukkit.level.Sound.MOB_TURTLE_BABY_DEATH;
		case ENTITY_TURTLE_EGG_BREAK:
			return cn.nukkit.level.Sound.BLOCK_TURTLE_EGG_BREAK;
		case ENTITY_TURTLE_EGG_CRACK:
			return cn.nukkit.level.Sound.BLOCK_TURTLE_EGG_CRACK;
		case ENTITY_TURTLE_EGG_HATCH:
			return cn.nukkit.level.Sound.BLOCK_TURTLE_EGG_DROP;
		case ENTITY_TURTLE_HURT:
			return cn.nukkit.level.Sound.MOB_TURTLE_HURT;
		case ENTITY_TURTLE_HURT_BABY:
			return cn.nukkit.level.Sound.MOB_TURTLE_BABY_HURT;
		case ENTITY_TURTLE_LAY_EGG:
			return cn.nukkit.level.Sound.MOB_TURTLE_BABY_BORN;
		case ENTITY_TURTLE_SHAMBLE:
			return cn.nukkit.level.Sound.MOB_TURTLE_STEP;
		case ENTITY_TURTLE_SHAMBLE_BABY:
			return cn.nukkit.level.Sound.MOB_TURTLE_BABY_STEP;
		case ENTITY_TURTLE_SWIM:
			return cn.nukkit.level.Sound.MOB_TURTLE_SWIM;
		case ENTITY_VILLAGER_TRADE:
			break;
		case ENTITY_VINDICATOR_AMBIENT:
			return cn.nukkit.level.Sound.MOB_VINDICATOR_IDLE;
		case ENTITY_VINDICATOR_DEATH:
			return cn.nukkit.level.Sound.MOB_VINDICATOR_DEATH;
		case ENTITY_VINDICATOR_HURT:
			return cn.nukkit.level.Sound.MOB_VINDICATOR_HURT;
		case ENTITY_ZOMBIE_CONVERTED_TO_DROWNED:
			return cn.nukkit.level.Sound.ENTITY_ZOMBIE_CONVERTED_TO_DROWNED;
		case ENTITY_ZOMBIE_DESTROY_EGG:
			break;
		case ENTITY_ZOMBIE_INFECT:
			break;
		case ENTITY_ZOMBIE_PIGMAN_AMBIENT:
			return cn.nukkit.level.Sound.MOB_ZOMBIEPIG_ZPIG;
		case ENTITY_ZOMBIE_PIGMAN_ANGRY:
			return cn.nukkit.level.Sound.MOB_ZOMBIEPIG_ZPIGANGRY;
		case ENTITY_ZOMBIE_PIGMAN_DEATH:
			return cn.nukkit.level.Sound.MOB_ZOMBIEPIG_ZPIGDEATH;
		case ENTITY_ZOMBIE_PIGMAN_HURT:
			return cn.nukkit.level.Sound.MOB_ZOMBIEPIG_ZPIGHURT;
		case ENTITY_ZOMBIE_VILLAGER_CURE:
			break;
		case ITEM_ARMOR_EQUIP_TURTLE:
			return cn.nukkit.level.Sound.ARMOR_EQUIP_GENERIC;
		case ITEM_AXE_STRIP:
			break;
		case ITEM_BUCKET_EMPTY_FISH:
			return cn.nukkit.level.Sound.BUCKET_EMPTY_FISH;
		case ITEM_BUCKET_FILL_FISH:
			return cn.nukkit.level.Sound.BUCKET_FILL_FISH;
		case ITEM_TRIDENT_HIT:
			return cn.nukkit.level.Sound.ITEM_TRIDENT_HIT;
		case ITEM_TRIDENT_HIT_GROUND:
			return cn.nukkit.level.Sound.ITEM_TRIDENT_HIT_GROUND;
		case ITEM_TRIDENT_RETURN:
			return cn.nukkit.level.Sound.ITEM_TRIDENT_RETURN;
		case ITEM_TRIDENT_RIPTIDE_1:
			return cn.nukkit.level.Sound.ITEM_TRIDENT_RIPTIDE_1;
		case ITEM_TRIDENT_RIPTIDE_2:
			return cn.nukkit.level.Sound.ITEM_TRIDENT_RIPTIDE_2;
		case ITEM_TRIDENT_RIPTIDE_3:
			return cn.nukkit.level.Sound.ITEM_TRIDENT_RIPTIDE_3;
		case ITEM_TRIDENT_THROW:
			return cn.nukkit.level.Sound.ITEM_TRIDENT_THROW;
		case ITEM_TRIDENT_THUNDER:
			return cn.nukkit.level.Sound.ITEM_TRIDENT_THUNDER;
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
