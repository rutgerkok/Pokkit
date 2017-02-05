package nl.rutgerkok.pokkit;

import org.bukkit.Location;
import org.bukkit.Sound;

import cn.nukkit.level.sound.AnvilBreakSound;
import cn.nukkit.level.sound.AnvilFallSound;
import cn.nukkit.level.sound.AnvilUseSound;
import cn.nukkit.level.sound.ClickSound;
import cn.nukkit.level.sound.DoorBumpSound;
import cn.nukkit.level.sound.DoorCrashSound;
import cn.nukkit.level.sound.DoorSound;
import cn.nukkit.level.sound.EndermanTeleportSound;
import cn.nukkit.level.sound.ExperienceOrbSound;
import cn.nukkit.level.sound.GenericSound;
import cn.nukkit.level.sound.GhastShootSound;
import cn.nukkit.level.sound.GhastSound;
import cn.nukkit.level.sound.LaunchSound;
import cn.nukkit.level.sound.LeverSound;
import cn.nukkit.level.sound.NoteBoxSound;
import cn.nukkit.level.sound.PopSound;
import cn.nukkit.level.sound.TNTPrimeSound;
import cn.nukkit.math.Vector3;
import cn.nukkit.network.protocol.LevelEventPacket;

/**
 * Sound conversion. Many sounds from PC cannot be played in PE, as far as I
 * know.
 *
 */
public final class PokkitSound {

	public static cn.nukkit.level.sound.Sound toNukkit(Location location, Sound sound, float pitch) {

		Vector3 vector = new Vector3(location.getX(), location.getY(), location.getZ());
		switch (sound) {
		case AMBIENT_CAVE:
			break;
		case BLOCK_ANVIL_BREAK:
			return new AnvilBreakSound(vector, pitch);
		case BLOCK_ANVIL_DESTROY:
			break;
		case BLOCK_ANVIL_FALL:
			return new AnvilFallSound(vector, pitch);
		case BLOCK_ANVIL_HIT:
			break;
		case BLOCK_ANVIL_LAND:
			break;
		case BLOCK_ANVIL_PLACE:
			break;
		case BLOCK_ANVIL_STEP:
			break;
		case BLOCK_ANVIL_USE:
			return new AnvilUseSound(vector, pitch);
		case BLOCK_BREWING_STAND_BREW:
			break;
		case BLOCK_CHEST_CLOSE:
			break;
		case BLOCK_CHEST_LOCKED:
			break;
		case BLOCK_CHEST_OPEN:
			break;
		case BLOCK_CHORUS_FLOWER_DEATH:
			break;
		case BLOCK_CHORUS_FLOWER_GROW:
			break;
		case BLOCK_CLOTH_BREAK:
			break;
		case BLOCK_CLOTH_FALL:
			break;
		case BLOCK_CLOTH_HIT:
			break;
		case BLOCK_CLOTH_PLACE:
			break;
		case BLOCK_CLOTH_STEP:
			break;
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
		case BLOCK_ENDERCHEST_CLOSE:
			break;
		case BLOCK_ENDERCHEST_OPEN:
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
			return new LeverSound(vector, pitch > 0.6);
		case BLOCK_METAL_BREAK:
			break;
		case BLOCK_METAL_FALL:
			break;
		case BLOCK_METAL_HIT:
			break;
		case BLOCK_METAL_PLACE:
			break;
		case BLOCK_METAL_PRESSUREPLATE_CLICK_OFF:
			break;
		case BLOCK_METAL_PRESSUREPLATE_CLICK_ON:
			break;
		case BLOCK_METAL_STEP:
			break;
		case BLOCK_NOTE_BASEDRUM:
			return new NoteBoxSound(vector, NoteBoxSound.INSTRUMENT_BASS_DRUM, (int) (pitch * 16));
		case BLOCK_NOTE_BASS:
			return new NoteBoxSound(vector, NoteBoxSound.INSTRUMENT_BASS, (int) (pitch * 16));
		case BLOCK_NOTE_HARP:
			break;
		case BLOCK_NOTE_HAT:
			return new NoteBoxSound(vector, NoteBoxSound.INSTRUMENT_CLICK, (int) (pitch * 16));
		case BLOCK_NOTE_PLING:
			return new NoteBoxSound(vector, NoteBoxSound.INSTRUMENT_PIANO, (int) (pitch * 16));
		case BLOCK_NOTE_SNARE:
			return new NoteBoxSound(vector, NoteBoxSound.INSTRUMENT_TABOUR, (int) (pitch * 16));
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
		case BLOCK_SLIME_BREAK:
			break;
		case BLOCK_SLIME_FALL:
			break;
		case BLOCK_SLIME_HIT:
			break;
		case BLOCK_SLIME_PLACE:
			break;
		case BLOCK_SLIME_STEP:
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
		case BLOCK_STONE_PRESSUREPLATE_CLICK_OFF:
			break;
		case BLOCK_STONE_PRESSUREPLATE_CLICK_ON:
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
		case BLOCK_WATERLILY_PLACE:
			break;
		case BLOCK_WATER_AMBIENT:
			break;
		case BLOCK_WOODEN_DOOR_CLOSE:
			break;
		case BLOCK_WOODEN_DOOR_OPEN:
			return new DoorSound(vector, pitch);
		case BLOCK_WOODEN_TRAPDOOR_CLOSE:
			break;
		case BLOCK_WOODEN_TRAPDOOR_OPEN:
			break;
		case BLOCK_WOOD_BREAK:
			break;
		case BLOCK_WOOD_BUTTON_CLICK_OFF:
			break;
		case BLOCK_WOOD_BUTTON_CLICK_ON:
			break;
		case BLOCK_WOOD_FALL:
			break;
		case BLOCK_WOOD_HIT:
			break;
		case BLOCK_WOOD_PLACE:
			break;
		case BLOCK_WOOD_PRESSUREPLATE_CLICK_OFF:
			break;
		case BLOCK_WOOD_PRESSUREPLATE_CLICK_ON:
			break;
		case BLOCK_WOOD_STEP:
			break;
		case ENCHANT_THORNS_HIT:
			break;
		case ENTITY_ARMORSTAND_BREAK:
			break;
		case ENTITY_ARMORSTAND_FALL:
			break;
		case ENTITY_ARMORSTAND_HIT:
			break;
		case ENTITY_ARMORSTAND_PLACE:
			break;
		case ENTITY_ARROW_HIT:
			break;
		case ENTITY_ARROW_HIT_PLAYER:
			break;
		case ENTITY_ARROW_SHOOT:
			return new LaunchSound(vector, pitch);
		case ENTITY_BAT_AMBIENT:
			break;
		case ENTITY_BAT_DEATH:
			break;
		case ENTITY_BAT_HURT:
			break;
		case ENTITY_BAT_LOOP:
			break;
		// case ENTITY_BAT_TAKEOFF:
		// 	return new BatSound(vector, pitch);
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
		case ENTITY_BOBBER_SPLASH:
			break;
		case ENTITY_BOBBER_THROW:
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
		case ENTITY_ENDERDRAGON_AMBIENT:
			break;
		case ENTITY_ENDERDRAGON_DEATH:
			break;
		case ENTITY_ENDERDRAGON_FIREBALL_EXPLODE:
			break;
		case ENTITY_ENDERDRAGON_FLAP:
			break;
		case ENTITY_ENDERDRAGON_GROWL:
			break;
		case ENTITY_ENDERDRAGON_HURT:
			break;
		case ENTITY_ENDERDRAGON_SHOOT:
			break;
		case ENTITY_ENDEREYE_LAUNCH:
			break;
		case ENTITY_ENDERMEN_AMBIENT:
			break;
		case ENTITY_ENDERMEN_DEATH:
			break;
		case ENTITY_ENDERMEN_HURT:
			break;
		case ENTITY_ENDERMEN_SCREAM:
			break;
		case ENTITY_ENDERMEN_STARE:
			break;
		case ENTITY_ENDERMEN_TELEPORT:
			return new EndermanTeleportSound(vector, pitch);
		case ENTITY_ENDERMITE_AMBIENT:
			break;
		case ENTITY_ENDERMITE_DEATH:
			break;
		case ENTITY_ENDERMITE_HURT:
			break;
		case ENTITY_ENDERMITE_STEP:
			break;
		case ENTITY_ENDERPEARL_THROW:
			break;
		case ENTITY_EXPERIENCE_BOTTLE_THROW:
			break;
		case ENTITY_EXPERIENCE_ORB_PICKUP:
			return new ExperienceOrbSound(vector, pitch);
		case ENTITY_FIREWORK_BLAST:
			break;
		case ENTITY_FIREWORK_BLAST_FAR:
			break;
		case ENTITY_FIREWORK_LARGE_BLAST:
			break;
		case ENTITY_FIREWORK_LARGE_BLAST_FAR:
			break;
		case ENTITY_FIREWORK_LAUNCH:
			break;
		case ENTITY_FIREWORK_SHOOT:
			break;
		case ENTITY_FIREWORK_TWINKLE:
			break;
		case ENTITY_FIREWORK_TWINKLE_FAR:
			break;
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
			break;
		case ENTITY_GHAST_DEATH:
			break;
		case ENTITY_GHAST_HURT:
			break;
		case ENTITY_GHAST_SCREAM:
			return new GhastShootSound(vector, pitch);
		case ENTITY_GHAST_SHOOT:
			break;
		case ENTITY_GHAST_WARN:
			return new GhastSound(vector, pitch);
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
		case ENTITY_IRONGOLEM_ATTACK:
			break;
		case ENTITY_IRONGOLEM_DEATH:
			break;
		case ENTITY_IRONGOLEM_HURT:
			break;
		case ENTITY_IRONGOLEM_STEP:
			break;
		case ENTITY_ITEMFRAME_ADD_ITEM:
			return new GenericSound(vector, LevelEventPacket.EVENT_SOUND_ITEM_FRAME_ITEM_ADDED, pitch);
		case ENTITY_ITEMFRAME_BREAK:
			return new GenericSound(vector, LevelEventPacket.EVENT_SOUND_ITEM_FRAME_REMOVED, pitch);
		case ENTITY_ITEMFRAME_PLACE:
			return new GenericSound(vector, LevelEventPacket.EVENT_SOUND_ITEM_FRAME_PLACED, pitch);
		case ENTITY_ITEMFRAME_REMOVE_ITEM:
			return new GenericSound(vector, LevelEventPacket.EVENT_SOUND_ITEM_FRAME_ITEM_REMOVED, pitch);
		case ENTITY_ITEMFRAME_ROTATE_ITEM:
			return new GenericSound(vector, LevelEventPacket.EVENT_SOUND_ITEM_FRAME_ITEM_ROTATED, pitch);
		case ENTITY_ITEM_BREAK:
			break;
		case ENTITY_ITEM_PICKUP:
			break;
		case ENTITY_LEASHKNOT_BREAK:
			break;
		case ENTITY_LEASHKNOT_PLACE:
			break;
		case ENTITY_LIGHTNING_IMPACT:
			break;
		case ENTITY_LIGHTNING_THUNDER:
			break;
		case ENTITY_LINGERINGPOTION_THROW:
			break;
		case ENTITY_MAGMACUBE_DEATH:
			break;
		case ENTITY_MAGMACUBE_HURT:
			break;
		case ENTITY_MAGMACUBE_JUMP:
			break;
		case ENTITY_MAGMACUBE_SQUISH:
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
		case ENTITY_POLAR_BEAR_BABY_AMBIENT:
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
			return new PopSound(vector, pitch);
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
		case ENTITY_SMALL_MAGMACUBE_DEATH:
			break;
		case ENTITY_SMALL_MAGMACUBE_HURT:
			break;
		case ENTITY_SMALL_MAGMACUBE_SQUISH:
			break;
		case ENTITY_SMALL_SLIME_DEATH:
			break;
		case ENTITY_SMALL_SLIME_HURT:
			break;
		case ENTITY_SMALL_SLIME_JUMP:
			break;
		case ENTITY_SMALL_SLIME_SQUISH:
			break;
		case ENTITY_SNOWBALL_THROW:
			break;
		case ENTITY_SNOWMAN_AMBIENT:
			break;
		case ENTITY_SNOWMAN_DEATH:
			break;
		case ENTITY_SNOWMAN_HURT:
			break;
		case ENTITY_SNOWMAN_SHOOT:
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
			return new TNTPrimeSound(vector, pitch);
		case ENTITY_VILLAGER_AMBIENT:
			break;
		case ENTITY_VILLAGER_DEATH:
			break;
		case ENTITY_VILLAGER_HURT:
			break;
		case ENTITY_VILLAGER_NO:
			break;
		case ENTITY_VILLAGER_TRADING:
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
		case ENTITY_ZOMBIE_ATTACK_DOOR_WOOD:
			return new DoorBumpSound(vector, pitch);
		case ENTITY_ZOMBIE_ATTACK_IRON_DOOR:
			break;
		case ENTITY_ZOMBIE_BREAK_DOOR_WOOD:
			return new DoorCrashSound(vector, pitch);
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
		// case ENTITY_ZOMBIE_INFECT:
		// 	return new ZombieInfectSound(vector, pitch);
		case ENTITY_ZOMBIE_PIG_AMBIENT:
			break;
		case ENTITY_ZOMBIE_PIG_ANGRY:
			break;
		case ENTITY_ZOMBIE_PIG_DEATH:
			break;
		case ENTITY_ZOMBIE_PIG_HURT:
			break;
		case ENTITY_ZOMBIE_STEP:
			break;
		case ENTITY_ZOMBIE_VILLAGER_AMBIENT:
			break;
		case ENTITY_ZOMBIE_VILLAGER_CONVERTED:
			break;
		// case ENTITY_ZOMBIE_VILLAGER_CURE:
		// 	return new ZombieInfectSound(vector, pitch);
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
			break;
		case MUSIC_CREDITS:
			break;
		case MUSIC_DRAGON:
			break;
		case MUSIC_END:
			break;
		case MUSIC_GAME:
			break;
		case MUSIC_MENU:
			break;
		case MUSIC_NETHER:
			break;
		case RECORD_11:
			break;
		case RECORD_13:
			break;
		case RECORD_BLOCKS:
			break;
		case RECORD_CAT:
			break;
		case RECORD_CHIRP:
			break;
		case RECORD_FAR:
			break;
		case RECORD_MALL:
			break;
		case RECORD_MELLOHI:
			break;
		case RECORD_STAL:
			break;
		case RECORD_STRAD:
			break;
		case RECORD_WAIT:
			break;
		case RECORD_WARD:
			break;
		case UI_BUTTON_CLICK:
			return new ClickSound(vector, pitch);
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
		case ENTITY_EVOCATION_FANGS_ATTACK:
			break;
		case ENTITY_EVOCATION_ILLAGER_AMBIENT:
			break;
		case ENTITY_EVOCATION_ILLAGER_CAST_SPELL:
			break;
		case ENTITY_EVOCATION_ILLAGER_DEATH:
			break;
		case ENTITY_EVOCATION_ILLAGER_HURT:
			break;
		case ENTITY_EVOCATION_ILLAGER_PREPARE_ATTACK:
			break;
		case ENTITY_EVOCATION_ILLAGER_PREPARE_SUMMON:
			break;
		case ENTITY_EVOCATION_ILLAGER_PREPARE_WOLOLO:
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
		case ENTITY_VINDICATION_ILLAGER_AMBIENT:
			break;
		case ENTITY_VINDICATION_ILLAGER_DEATH:
			break;
		case ENTITY_VINDICATION_ILLAGER_HURT:
			break;
		case ITEM_ARMOR_EQUIP_ELYTRA:
			break;
		case ITEM_BOTTLE_EMPTY:
			break;
		case ITEM_TOTEM_USE:
			break;
		default:
			break;
		}
		return null;
	}

}
