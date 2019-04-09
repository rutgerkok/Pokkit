package nl.rutgerkok.pokkit.blockdata;

import org.bukkit.Material;

import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;

/**
 * Note: this class does not yet take block data into account. For example,
 * Nukkit's WOOL is always converted to Bukkit's WHITE_WOOL. This is something
 * that should be corrected in the future.
 *
 */
@SuppressWarnings("deprecation")
final class BlockMap {

	private static Material[] nukkitToBukkit = new Material[500];
	private static char[] bukkitToNukkit = new char[Material.values().length];

	static {
		registerTwoWay(BlockID.AIR, Material.AIR);
		registerTwoWay(BlockID.STONE, Material.STONE);
		registerTwoWay(BlockID.GRASS, Material.GRASS_BLOCK);
		registerTwoWay(BlockID.DIRT, Material.DIRT);
		registerTwoWay(BlockID.COBBLESTONE, Material.COBBLESTONE);
		registerTwoWay(BlockID.PLANK, Material.OAK_PLANKS);
		registerTwoWay(BlockID.PLANKS, Material.OAK_PLANKS);
		registerTwoWay(BlockID.WOODEN_PLANK, Material.OAK_PLANKS);
		registerTwoWay(BlockID.WOODEN_PLANKS, Material.OAK_PLANKS);
		registerTwoWay(BlockID.SAPLING, Material.OAK_SAPLING);
		registerTwoWay(BlockID.SAPLINGS, Material.OAK_SAPLING);
		registerTwoWay(BlockID.BEDROCK, Material.BEDROCK);
		registerBukkitToBukkit(BlockID.WATER, Material.WATER);
		registerTwoWay(BlockID.STILL_WATER, Material.WATER);
		registerBukkitToBukkit(BlockID.LAVA, Material.LAVA);
		registerTwoWay(BlockID.STILL_LAVA, Material.LAVA);
		registerTwoWay(BlockID.SAND, Material.SAND);
		registerTwoWay(BlockID.GRAVEL, Material.GRAVEL);
		registerTwoWay(BlockID.GOLD_ORE, Material.GOLD_ORE);
		registerTwoWay(BlockID.IRON_ORE, Material.IRON_ORE);
		registerTwoWay(BlockID.COAL_ORE, Material.COAL_ORE);
		registerTwoWay(BlockID.LOG, Material.OAK_LOG);
		registerTwoWay(BlockID.LEAVES, Material.OAK_LEAVES);
		registerTwoWay(BlockID.SPONGE, Material.SPONGE);
		registerTwoWay(BlockID.GLASS, Material.GLASS);
		registerTwoWay(BlockID.LAPIS_ORE, Material.LAPIS_ORE);
		registerTwoWay(BlockID.LAPIS_BLOCK, Material.LAPIS_BLOCK);
		registerTwoWay(BlockID.DISPENSER, Material.DISPENSER);
		registerTwoWay(BlockID.SANDSTONE, Material.SANDSTONE);
		registerTwoWay(BlockID.NOTEBLOCK, Material.NOTE_BLOCK);
		registerTwoWay(BlockID.BED_BLOCK, Material.RED_BED);
		registerTwoWay(BlockID.POWERED_RAIL, Material.POWERED_RAIL);
		registerTwoWay(BlockID.DETECTOR_RAIL, Material.DETECTOR_RAIL);
		registerTwoWay(BlockID.STICKY_PISTON, Material.STICKY_PISTON);
		registerTwoWay(BlockID.COBWEB, Material.COBWEB);
		registerTwoWay(BlockID.TALL_GRASS, Material.TALL_GRASS);
		registerTwoWay(BlockID.BUSH, Material.FERN);
		registerTwoWay(BlockID.DEAD_BUSH, Material.DEAD_BUSH);
		registerTwoWay(BlockID.PISTON, Material.PISTON);
		registerTwoWay(BlockID.PISTON_HEAD, Material.PISTON_HEAD);
		registerTwoWay(BlockID.WOOL, Material.WHITE_WOOL);
		registerTwoWay(BlockID.DANDELION, Material.DANDELION);
		registerTwoWay(BlockID.POPPY, Material.POPPY);
		registerTwoWay(BlockID.BROWN_MUSHROOM, Material.BROWN_MUSHROOM);
		registerTwoWay(BlockID.RED_MUSHROOM, Material.RED_MUSHROOM);
		registerTwoWay(BlockID.GOLD_BLOCK, Material.GOLD_BLOCK);
		registerTwoWay(BlockID.IRON_BLOCK, Material.IRON_BLOCK);
		registerTwoWay(BlockID.STONE_SLAB, Material.STONE_SLAB);
		registerTwoWay(BlockID.BRICKS, Material.BRICKS);
		registerTwoWay(BlockID.TNT, Material.TNT);
		registerTwoWay(BlockID.BOOKSHELF, Material.BOOKSHELF);
		registerTwoWay(BlockID.MOSS_STONE, Material.MOSSY_COBBLESTONE);
		registerTwoWay(BlockID.OBSIDIAN, Material.OBSIDIAN);
		registerTwoWay(BlockID.TORCH, Material.TORCH);
		registerTwoWay(BlockID.FIRE, Material.FIRE);
		registerTwoWay(BlockID.MONSTER_SPAWNER, Material.SPAWNER);
		registerTwoWay(BlockID.OAK_WOODEN_STAIRS, Material.OAK_STAIRS);
		registerTwoWay(BlockID.CHEST, Material.CHEST);
		registerTwoWay(BlockID.REDSTONE_WIRE, Material.REDSTONE_WIRE);
		registerTwoWay(BlockID.DIAMOND_ORE, Material.DIAMOND_ORE);
		registerTwoWay(BlockID.DIAMOND_BLOCK, Material.DIAMOND_BLOCK);
		registerTwoWay(BlockID.CRAFTING_TABLE, Material.CRAFTING_TABLE);
		registerTwoWay(BlockID.WHEAT_BLOCK, Material.WHEAT);
		registerTwoWay(BlockID.FARMLAND, Material.FARMLAND);
		registerTwoWay(BlockID.FURNACE, Material.FURNACE);
		registerBukkitToBukkit(BlockID.BURNING_FURNACE, Material.FURNACE);
		registerTwoWay(BlockID.SIGN_POST, Material.SIGN);
		registerTwoWay(BlockID.DOOR_BLOCK, Material.OAK_DOOR);
		registerTwoWay(BlockID.LADDER, Material.LADDER);
		registerTwoWay(BlockID.RAIL, Material.RAIL);
		registerTwoWay(BlockID.COBBLESTONE_STAIRS, Material.COBBLESTONE_STAIRS);
		registerTwoWay(BlockID.WALL_SIGN, Material.WALL_SIGN);
		registerTwoWay(BlockID.LEVER, Material.LEVER);
		registerTwoWay(BlockID.STONE_PRESSURE_PLATE, Material.STONE_PRESSURE_PLATE);
		registerTwoWay(BlockID.IRON_DOOR_BLOCK, Material.IRON_DOOR);
		registerTwoWay(BlockID.WOODEN_PRESSURE_PLATE, Material.OAK_PRESSURE_PLATE);
		registerTwoWay(BlockID.REDSTONE_ORE, Material.REDSTONE_ORE);
		registerBukkitToBukkit(BlockID.GLOWING_REDSTONE_ORE, Material.REDSTONE_ORE);
		registerBukkitToBukkit(BlockID.UNLIT_REDSTONE_TORCH, Material.REDSTONE_TORCH);
		registerTwoWay(BlockID.REDSTONE_TORCH, Material.REDSTONE_TORCH);
		registerTwoWay(BlockID.STONE_BUTTON, Material.STONE_BUTTON);
		registerTwoWay(BlockID.SNOW, Material.SNOW);
		registerTwoWay(BlockID.ICE, Material.ICE);
		registerTwoWay(BlockID.SNOW_BLOCK, Material.SNOW_BLOCK);
		registerTwoWay(BlockID.CACTUS, Material.CACTUS);
		registerTwoWay(BlockID.CLAY_BLOCK, Material.CLAY);
		registerTwoWay(BlockID.SUGARCANE_BLOCK, Material.SUGAR_CANE);
		registerTwoWay(BlockID.JUKEBOX, Material.JUKEBOX);
		registerTwoWay(BlockID.FENCE, Material.OAK_FENCE);
		registerTwoWay(BlockID.PUMPKIN, Material.PUMPKIN);
		registerTwoWay(BlockID.NETHERRACK, Material.NETHERRACK);
		registerTwoWay(BlockID.SOUL_SAND, Material.SOUL_SAND);
		registerTwoWay(BlockID.GLOWSTONE, Material.GLOWSTONE);
		registerTwoWay(BlockID.NETHER_PORTAL, Material.NETHER_PORTAL);
		registerTwoWay(BlockID.JACK_O_LANTERN, Material.JACK_O_LANTERN);
		registerTwoWay(BlockID.CAKE_BLOCK, Material.CAKE);
		registerTwoWay(BlockID.UNPOWERED_REPEATER, Material.REPEATER);
		registerBukkitToBukkit(BlockID.POWERED_REPEATER, Material.REPEATER);
		registerTwoWay(BlockID.INVISIBLE_BEDROCK, Material.BARRIER);
		registerTwoWay(BlockID.TRAPDOOR, Material.OAK_TRAPDOOR);
		registerTwoWay(BlockID.MONSTER_EGG, Material.INFESTED_STONE);
		registerTwoWay(BlockID.STONE_BRICKS, Material.STONE_BRICKS);
		registerTwoWay(BlockID.BROWN_MUSHROOM_BLOCK, Material.BROWN_MUSHROOM_BLOCK);
		registerTwoWay(BlockID.RED_MUSHROOM_BLOCK, Material.RED_MUSHROOM_BLOCK);
		registerTwoWay(BlockID.IRON_BARS, Material.IRON_BARS);
		registerTwoWay(BlockID.GLASS_PANE, Material.GLASS_PANE);
		registerTwoWay(BlockID.MELON_BLOCK, Material.MELON);
		registerTwoWay(BlockID.PUMPKIN_STEM, Material.PUMPKIN_STEM);
		registerTwoWay(BlockID.MELON_STEM, Material.MELON_STEM);
		registerTwoWay(BlockID.VINE, Material.VINE);
		registerTwoWay(BlockID.FENCE_GATE_OAK, Material.OAK_FENCE_GATE);
		registerTwoWay(BlockID.BRICK_STAIRS, Material.BRICK_STAIRS);
		registerTwoWay(BlockID.STONE_BRICK_STAIRS, Material.STONE_BRICK_STAIRS);
		registerTwoWay(BlockID.MYCELIUM, Material.MYCELIUM);
		registerTwoWay(BlockID.LILY_PAD, Material.LILY_PAD);
		registerTwoWay(BlockID.NETHER_BRICKS, Material.NETHER_BRICKS);
		registerTwoWay(BlockID.NETHER_BRICK_FENCE, Material.NETHER_BRICK_FENCE);
		registerTwoWay(BlockID.NETHER_BRICKS_STAIRS, Material.NETHER_BRICK_STAIRS);
		registerTwoWay(BlockID.NETHER_WART_BLOCK, Material.NETHER_WART_BLOCK);
		registerTwoWay(BlockID.ENCHANTING_TABLE, Material.ENCHANTING_TABLE);
		registerTwoWay(BlockID.BREWING_STAND_BLOCK, Material.BREWING_STAND);
		registerTwoWay(BlockID.CAULDRON_BLOCK, Material.CAULDRON);
		registerTwoWay(BlockID.END_PORTAL, Material.END_PORTAL);
		registerTwoWay(BlockID.END_PORTAL_FRAME, Material.END_PORTAL_FRAME);
		registerTwoWay(BlockID.END_STONE, Material.END_STONE);
		registerTwoWay(BlockID.DRAGON_EGG, Material.DRAGON_EGG);
		registerTwoWay(BlockID.REDSTONE_LAMP, Material.REDSTONE_LAMP);
		registerBukkitToBukkit(BlockID.LIT_REDSTONE_LAMP, Material.REDSTONE_LAMP);
		registerTwoWay(BlockID.DROPPER, Material.DROPPER);
		registerTwoWay(BlockID.ACTIVATOR_RAIL, Material.ACTIVATOR_RAIL);
		registerTwoWay(BlockID.COCOA, Material.COCOA);
		registerTwoWay(BlockID.SANDSTONE_STAIRS, Material.SANDSTONE_STAIRS);
		registerTwoWay(BlockID.EMERALD_ORE, Material.EMERALD_ORE);
		registerTwoWay(BlockID.ENDER_CHEST, Material.ENDER_CHEST);
		registerTwoWay(BlockID.TRIPWIRE_HOOK, Material.TRIPWIRE_HOOK);
		registerTwoWay(BlockID.TRIPWIRE, Material.TRIPWIRE);
		registerTwoWay(BlockID.EMERALD_BLOCK, Material.EMERALD_BLOCK);
		registerTwoWay(BlockID.SPRUCE_WOOD_STAIRS, Material.SPRUCE_STAIRS);
		registerTwoWay(BlockID.BIRCH_WOOD_STAIRS, Material.BIRCH_STAIRS);
		registerTwoWay(BlockID.JUNGLE_WOOD_STAIRS, Material.JUNGLE_STAIRS);
		registerTwoWay(BlockID.BEACON, Material.BEACON);
		registerTwoWay(BlockID.COBBLESTONE_WALL, Material.COBBLESTONE_WALL);
		registerTwoWay(BlockID.FLOWER_POT_BLOCK, Material.FLOWER_POT);
		registerTwoWay(BlockID.CARROT_BLOCK, Material.CARROTS);
		registerTwoWay(BlockID.POTATO_BLOCK, Material.POTATOES);
		registerTwoWay(BlockID.WOODEN_BUTTON, Material.OAK_BUTTON);
		registerTwoWay(BlockID.SKULL_BLOCK, Material.SKELETON_SKULL);
		registerTwoWay(BlockID.ANVIL, Material.ANVIL);
		registerTwoWay(BlockID.TRAPPED_CHEST, Material.TRAPPED_CHEST);
		registerTwoWay(BlockID.LIGHT_WEIGHTED_PRESSURE_PLATE, Material.LIGHT_WEIGHTED_PRESSURE_PLATE);
		registerTwoWay(BlockID.HEAVY_WEIGHTED_PRESSURE_PLATE, Material.HEAVY_WEIGHTED_PRESSURE_PLATE);
		registerTwoWay(BlockID.UNPOWERED_COMPARATOR, Material.COMPARATOR);
		registerBukkitToBukkit(BlockID.POWERED_COMPARATOR, Material.COMPARATOR);
		registerTwoWay(BlockID.DAYLIGHT_DETECTOR, Material.DAYLIGHT_DETECTOR);
		registerTwoWay(BlockID.REDSTONE_BLOCK, Material.REDSTONE_BLOCK);
		registerTwoWay(BlockID.QUARTZ_ORE, Material.QUARTZ);
		registerTwoWay(BlockID.HOPPER_BLOCK, Material.HOPPER);
		registerTwoWay(BlockID.QUARTZ_BLOCK, Material.QUARTZ_BLOCK);
		registerTwoWay(BlockID.QUARTZ_STAIRS, Material.QUARTZ_STAIRS);
		registerBukkitToBukkit(BlockID.DOUBLE_WOOD_SLAB, Material.OAK_SLAB);
		registerTwoWay(BlockID.WOOD_SLAB, Material.OAK_SLAB);
		registerTwoWay(BlockID.STAINED_TERRACOTTA, Material.WHITE_GLAZED_TERRACOTTA);
		registerTwoWay(BlockID.STAINED_HARDENED_CLAY, Material.TERRACOTTA);
		registerTwoWay(BlockID.STAINED_GLASS_PANE, Material.WHITE_STAINED_GLASS_PANE);
		registerTwoWay(BlockID.LEAVES2, Material.ACACIA_LEAVES);
		registerTwoWay(BlockID.WOOD2, Material.ACACIA_LOG);
		registerTwoWay(BlockID.ACACIA_WOOD_STAIRS, Material.ACACIA_STAIRS);
		registerTwoWay(BlockID.DARK_OAK_WOOD_STAIRS, Material.DARK_OAK_STAIRS);
		registerTwoWay(BlockID.SLIME_BLOCK, Material.SLIME_BLOCK);
		registerTwoWay(BlockID.IRON_TRAPDOOR, Material.IRON_TRAPDOOR);
		registerTwoWay(BlockID.PRISMARINE, Material.PRISMARINE);
		registerTwoWay(BlockID.SEA_LANTERN, Material.SEA_LANTERN);
		registerTwoWay(BlockID.HAY_BALE, Material.HAY_BLOCK);
		registerTwoWay(BlockID.CARPET, Material.WHITE_CARPET);
		registerTwoWay(BlockID.TERRACOTTA, Material.TERRACOTTA);
		registerTwoWay(BlockID.COAL_BLOCK, Material.COAL_BLOCK);
		registerTwoWay(BlockID.PACKED_ICE, Material.PACKED_ICE);
		registerTwoWay(BlockID.DOUBLE_PLANT, Material.ROSE_BUSH);
		registerBukkitToBukkit(BlockID.DAYLIGHT_DETECTOR_INVERTED, Material.DAYLIGHT_DETECTOR);
		registerTwoWay(BlockID.RED_SANDSTONE, Material.RED_SANDSTONE);
		registerTwoWay(BlockID.RED_SANDSTONE_STAIRS, Material.RED_SANDSTONE_STAIRS);
		registerBukkitToBukkit(BlockID.DOUBLE_RED_SANDSTONE_SLAB, Material.RED_SANDSTONE_SLAB);
		registerTwoWay(BlockID.RED_SANDSTONE_SLAB, Material.RED_SANDSTONE_SLAB);
		registerTwoWay(BlockID.FENCE_GATE_SPRUCE, Material.SPRUCE_FENCE_GATE);
		registerTwoWay(BlockID.FENCE_GATE_BIRCH, Material.BIRCH_FENCE_GATE);
		registerTwoWay(BlockID.FENCE_GATE_JUNGLE, Material.JUNGLE_FENCE_GATE);
		registerTwoWay(BlockID.FENCE_GATE_DARK_OAK, Material.DARK_OAK_FENCE_GATE);
		registerTwoWay(BlockID.FENCE_GATE_ACACIA, Material.ACACIA_FENCE_GATE);
		registerTwoWay(BlockID.SPRUCE_DOOR_BLOCK, Material.SPRUCE_DOOR);
		registerTwoWay(BlockID.BIRCH_DOOR_BLOCK, Material.BIRCH_DOOR);
		registerTwoWay(BlockID.JUNGLE_DOOR_BLOCK, Material.JUNGLE_DOOR);
		registerTwoWay(BlockID.ACACIA_DOOR_BLOCK, Material.ACACIA_DOOR);
		registerTwoWay(BlockID.DARK_OAK_DOOR_BLOCK, Material.DARK_OAK_DOOR);
		registerTwoWay(BlockID.GRASS_PATH, Material.GRASS_PATH);
		registerTwoWay(BlockID.ITEM_FRAME_BLOCK, Material.ITEM_FRAME);
		registerTwoWay(BlockID.CHORUS_FLOWER, Material.CHORUS_FLOWER);
		registerTwoWay(BlockID.PURPUR_BLOCK, Material.PURPUR_BLOCK);
		registerTwoWay(BlockID.PURPUR_STAIRS, Material.PURPUR_STAIRS);
		registerTwoWay(BlockID.UNDYED_SHULKER_BOX, Material.SHULKER_BOX);
		registerTwoWay(BlockID.END_BRICKS, Material.END_STONE_BRICKS);
		registerTwoWay(BlockID.ICE_FROSTED, Material.FROSTED_ICE);
		registerTwoWay(BlockID.END_ROD, Material.END_ROD);
		registerTwoWay(BlockID.END_GATEWAY, Material.END_GATEWAY);
		registerTwoWay(BlockID.MAGMA, Material.MAGMA_BLOCK);
		registerTwoWay(BlockID.BLOCK_NETHER_WART_BLOCK, Material.NETHER_WART_BLOCK);
		registerTwoWay(BlockID.RED_NETHER_BRICK, Material.RED_NETHER_BRICKS);
		registerTwoWay(BlockID.BONE_BLOCK, Material.BONE_BLOCK);
		registerTwoWay(BlockID.SHULKER_BOX, Material.SHULKER_BOX);
		registerTwoWay(BlockID.PURPLE_GLAZED_TERRACOTTA, Material.PURPLE_GLAZED_TERRACOTTA);
		registerTwoWay(BlockID.WHITE_GLAZED_TERRACOTTA, Material.WHITE_GLAZED_TERRACOTTA);
		registerTwoWay(BlockID.ORANGE_GLAZED_TERRACOTTA, Material.ORANGE_GLAZED_TERRACOTTA);
		registerTwoWay(BlockID.MAGENTA_GLAZED_TERRACOTTA, Material.MAGENTA_GLAZED_TERRACOTTA);
		registerTwoWay(BlockID.LIGHT_BLUE_GLAZED_TERRACOTTA, Material.LIGHT_BLUE_GLAZED_TERRACOTTA);
		registerTwoWay(BlockID.YELLOW_GLAZED_TERRACOTTA, Material.YELLOW_GLAZED_TERRACOTTA);
		registerTwoWay(BlockID.LIME_GLAZED_TERRACOTTA, Material.LIME_GLAZED_TERRACOTTA);
		registerTwoWay(BlockID.PINK_GLAZED_TERRACOTTA, Material.PINK_GLAZED_TERRACOTTA);
		registerTwoWay(BlockID.GRAY_GLAZED_TERRACOTTA, Material.GRAY_GLAZED_TERRACOTTA);
		registerTwoWay(BlockID.SILVER_GLAZED_TERRACOTTA, Material.LIGHT_GRAY_GLAZED_TERRACOTTA);
		registerTwoWay(BlockID.CYAN_GLAZED_TERRACOTTA, Material.CYAN_GLAZED_TERRACOTTA);
		registerTwoWay(BlockID.BLUE_GLAZED_TERRACOTTA, Material.BLUE_GLAZED_TERRACOTTA);
		registerTwoWay(BlockID.BROWN_GLAZED_TERRACOTTA, Material.BROWN_GLAZED_TERRACOTTA);
		registerTwoWay(BlockID.GREEN_GLAZED_TERRACOTTA, Material.GREEN_GLAZED_TERRACOTTA);
		registerTwoWay(BlockID.RED_GLAZED_TERRACOTTA, Material.RED_GLAZED_TERRACOTTA);
		registerTwoWay(BlockID.BLACK_GLAZED_TERRACOTTA, Material.BLACK_GLAZED_TERRACOTTA);
		registerTwoWay(BlockID.CONCRETE, Material.WHITE_CONCRETE);
		registerTwoWay(BlockID.CONCRETE_POWDER, Material.WHITE_CONCRETE_POWDER);
		registerTwoWay(BlockID.CHORUS_PLANT, Material.CHORUS_PLANT);
		registerTwoWay(BlockID.STAINED_GLASS, Material.WHITE_STAINED_GLASS);
		registerTwoWay(BlockID.PODZOL, Material.PODZOL);
		registerTwoWay(BlockID.BEETROOT_BLOCK, Material.BEETROOTS);
		registerBukkitToBukkit(BlockID.STONECUTTER, Material.COBBLESTONE);
		registerBukkitToBukkit(BlockID.GLOWING_OBSIDIAN, Material.OBSIDIAN);
		registerBukkitToBukkit(BlockID.NETHER_REACTOR, Material.RED_NETHER_BRICKS);
		registerTwoWay(BlockID.PISTON_EXTENSION, Material.MOVING_PISTON);
		registerTwoWay(BlockID.OBSERVER, Material.OBSERVER);

		// Legacy materials
		registerBukkitToNukkit(BlockID.AIR, Material.LEGACY_AIR);
		registerBukkitToNukkit(BlockID.STONE, Material.LEGACY_STONE);
		registerBukkitToNukkit(BlockID.GRASS, Material.LEGACY_GRASS);
		registerBukkitToNukkit(BlockID.DIRT, Material.LEGACY_DIRT);
		registerBukkitToNukkit(BlockID.COBBLESTONE, Material.LEGACY_COBBLESTONE);
		registerBukkitToNukkit(BlockID.WOOD, Material.LEGACY_WOOD);
		registerBukkitToNukkit(BlockID.SAPLING, Material.LEGACY_SAPLING);
		registerBukkitToNukkit(BlockID.BEDROCK, Material.LEGACY_BEDROCK);
		registerBukkitToNukkit(BlockID.WATER, Material.LEGACY_WATER);
		registerBukkitToNukkit(BlockID.STILL_WATER, Material.LEGACY_STATIONARY_WATER);
		registerBukkitToNukkit(BlockID.LAVA, Material.LEGACY_LAVA);
		registerBukkitToNukkit(BlockID.STILL_LAVA, Material.LEGACY_STATIONARY_LAVA);
		registerBukkitToNukkit(BlockID.SAND, Material.LEGACY_SAND);
		registerBukkitToNukkit(BlockID.GRAVEL, Material.LEGACY_GRAVEL);
		registerBukkitToNukkit(BlockID.GOLD_ORE, Material.LEGACY_GOLD_ORE);
		registerBukkitToNukkit(BlockID.IRON_ORE, Material.LEGACY_IRON_ORE);
		registerBukkitToNukkit(BlockID.COAL_ORE, Material.LEGACY_COAL_ORE);
		registerBukkitToNukkit(BlockID.LOG, Material.LEGACY_LOG);
		registerBukkitToNukkit(BlockID.LEAVES, Material.LEGACY_LEAVES);
		registerBukkitToNukkit(BlockID.SPONGE, Material.LEGACY_SPONGE);
		registerBukkitToNukkit(BlockID.GLASS, Material.LEGACY_GLASS);
		registerBukkitToNukkit(BlockID.LAPIS_ORE, Material.LEGACY_LAPIS_ORE);
		registerBukkitToNukkit(BlockID.LAPIS_BLOCK, Material.LEGACY_LAPIS_BLOCK);
		registerBukkitToNukkit(BlockID.DISPENSER, Material.LEGACY_DISPENSER);
		registerBukkitToNukkit(BlockID.SANDSTONE, Material.LEGACY_SANDSTONE);
		registerBukkitToNukkit(BlockID.NOTEBLOCK, Material.LEGACY_NOTE_BLOCK);
		registerBukkitToNukkit(BlockID.BED_BLOCK, Material.LEGACY_BED_BLOCK);
		registerBukkitToNukkit(BlockID.POWERED_RAIL, Material.LEGACY_POWERED_RAIL);
		registerBukkitToNukkit(BlockID.DETECTOR_RAIL, Material.LEGACY_DETECTOR_RAIL);
		registerBukkitToNukkit(BlockID.STICKY_PISTON, Material.LEGACY_PISTON_STICKY_BASE);
		registerBukkitToNukkit(BlockID.COBWEB, Material.LEGACY_WEB);
		registerBukkitToNukkit(BlockID.TALL_GRASS, Material.LEGACY_LONG_GRASS);
		registerBukkitToNukkit(BlockID.DEAD_BUSH, Material.LEGACY_DEAD_BUSH);
		registerBukkitToNukkit(BlockID.PISTON, Material.LEGACY_PISTON_BASE);
		registerBukkitToNukkit(BlockID.PISTON_EXTENSION, Material.LEGACY_PISTON_EXTENSION);
		registerBukkitToNukkit(BlockID.WOOL, Material.LEGACY_WOOL);
		registerBukkitToNukkit(BlockID.PISTON_HEAD, Material.LEGACY_PISTON_MOVING_PIECE);
		registerBukkitToNukkit(BlockID.FLOWER, Material.LEGACY_YELLOW_FLOWER);
		registerBukkitToNukkit(BlockID.RED_FLOWER, Material.LEGACY_RED_ROSE);
		registerBukkitToNukkit(BlockID.BROWN_MUSHROOM, Material.LEGACY_BROWN_MUSHROOM);
		registerBukkitToNukkit(BlockID.RED_MUSHROOM, Material.LEGACY_RED_MUSHROOM);
		registerBukkitToNukkit(BlockID.GOLD_BLOCK, Material.LEGACY_GOLD_BLOCK);
		registerBukkitToNukkit(BlockID.IRON_BLOCK, Material.LEGACY_IRON_BLOCK);
		registerBukkitToNukkit(BlockID.DOUBLE_STONE_SLAB, Material.LEGACY_DOUBLE_STEP);
		registerBukkitToNukkit(BlockID.STONE_SLAB, Material.LEGACY_STEP);
		registerBukkitToNukkit(BlockID.BRICKS, Material.LEGACY_BRICK);
		registerBukkitToNukkit(BlockID.TNT, Material.LEGACY_TNT);
		registerBukkitToNukkit(BlockID.BOOKSHELF, Material.LEGACY_BOOKSHELF);
		registerBukkitToNukkit(BlockID.MOSS_STONE, Material.LEGACY_MOSSY_COBBLESTONE);
		registerBukkitToNukkit(BlockID.OBSIDIAN, Material.LEGACY_OBSIDIAN);
		registerBukkitToNukkit(BlockID.TORCH, Material.LEGACY_TORCH);
		registerBukkitToNukkit(BlockID.FIRE, Material.LEGACY_FIRE);
		registerBukkitToNukkit(BlockID.MONSTER_SPAWNER, Material.LEGACY_MOB_SPAWNER);
		registerBukkitToNukkit(BlockID.WOOD_STAIRS, Material.LEGACY_WOOD_STAIRS);
		registerBukkitToNukkit(BlockID.CHEST, Material.LEGACY_CHEST);
		registerBukkitToNukkit(BlockID.REDSTONE_WIRE, Material.LEGACY_REDSTONE_WIRE);
		registerBukkitToNukkit(BlockID.DIAMOND_ORE, Material.LEGACY_DIAMOND_ORE);
		registerBukkitToNukkit(BlockID.DIAMOND_BLOCK, Material.LEGACY_DIAMOND_BLOCK);
		registerBukkitToNukkit(BlockID.WORKBENCH, Material.LEGACY_WORKBENCH);
		registerBukkitToNukkit(BlockID.WHEAT_BLOCK, Material.LEGACY_CROPS);
		registerBukkitToNukkit(BlockID.FARMLAND, Material.LEGACY_SOIL);
		registerBukkitToNukkit(BlockID.FURNACE, Material.LEGACY_FURNACE);
		registerBukkitToNukkit(BlockID.BURNING_FURNACE, Material.LEGACY_BURNING_FURNACE);
		registerBukkitToNukkit(BlockID.SIGN_POST, Material.LEGACY_SIGN_POST);
		registerBukkitToNukkit(BlockID.DOOR_BLOCK, Material.LEGACY_WOODEN_DOOR);
		registerBukkitToNukkit(BlockID.LADDER, Material.LEGACY_LADDER);
		registerBukkitToNukkit(BlockID.RAIL, Material.LEGACY_RAILS);
		registerBukkitToNukkit(BlockID.COBBLESTONE_STAIRS, Material.LEGACY_COBBLESTONE_STAIRS);
		registerBukkitToNukkit(BlockID.WALL_SIGN, Material.LEGACY_WALL_SIGN);
		registerBukkitToNukkit(BlockID.LEVER, Material.LEGACY_LEVER);
		registerBukkitToNukkit(BlockID.STONE_PRESSURE_PLATE, Material.LEGACY_STONE_PLATE);
		registerBukkitToNukkit(BlockID.IRON_DOOR_BLOCK, Material.LEGACY_IRON_DOOR_BLOCK);
		registerBukkitToNukkit(BlockID.WOODEN_PRESSURE_PLATE, Material.LEGACY_WOOD_PLATE);
		registerBukkitToNukkit(BlockID.REDSTONE_ORE, Material.LEGACY_REDSTONE_ORE);
		registerBukkitToNukkit(BlockID.GLOWING_REDSTONE_ORE, Material.LEGACY_GLOWING_REDSTONE_ORE);
		registerBukkitToNukkit(BlockID.UNLIT_REDSTONE_TORCH, Material.LEGACY_REDSTONE_TORCH_OFF);
		registerBukkitToNukkit(BlockID.REDSTONE_TORCH, Material.LEGACY_REDSTONE_TORCH_ON);
		registerBukkitToNukkit(BlockID.STONE_BUTTON, Material.LEGACY_STONE_BUTTON);
		registerBukkitToNukkit(BlockID.SNOW, Material.LEGACY_SNOW);
		registerBukkitToNukkit(BlockID.ICE, Material.LEGACY_ICE);
		registerBukkitToNukkit(BlockID.SNOW_BLOCK, Material.LEGACY_SNOW_BLOCK);
		registerBukkitToNukkit(BlockID.CACTUS, Material.LEGACY_CACTUS);
		registerBukkitToNukkit(BlockID.CLAY_BLOCK, Material.LEGACY_CLAY);
		registerBukkitToNukkit(BlockID.SUGARCANE_BLOCK, Material.LEGACY_SUGAR_CANE_BLOCK);
		registerBukkitToNukkit(BlockID.JUKEBOX, Material.LEGACY_JUKEBOX);
		registerBukkitToNukkit(BlockID.FENCE, Material.LEGACY_FENCE);
		registerBukkitToNukkit(BlockID.PUMPKIN, Material.LEGACY_PUMPKIN);
		registerBukkitToNukkit(BlockID.NETHERRACK, Material.LEGACY_NETHERRACK);
		registerBukkitToNukkit(BlockID.SOUL_SAND, Material.LEGACY_SOUL_SAND);
		registerBukkitToNukkit(BlockID.GLOWSTONE, Material.LEGACY_GLOWSTONE);
		registerBukkitToNukkit(BlockID.NETHER_PORTAL, Material.LEGACY_PORTAL);
		registerBukkitToNukkit(BlockID.JACK_O_LANTERN, Material.LEGACY_JACK_O_LANTERN);
		registerBukkitToNukkit(BlockID.CAKE_BLOCK, Material.LEGACY_CAKE_BLOCK);
		registerBukkitToNukkit(BlockID.UNPOWERED_REPEATER, Material.LEGACY_DIODE_BLOCK_OFF);
		registerBukkitToNukkit(BlockID.POWERED_REPEATER, Material.LEGACY_DIODE_BLOCK_ON);
		registerBukkitToNukkit(BlockID.STAINED_GLASS, Material.LEGACY_STAINED_GLASS);
		registerBukkitToNukkit(BlockID.TRAPDOOR, Material.LEGACY_TRAP_DOOR);
		registerBukkitToNukkit(BlockID.MONSTER_EGG, Material.LEGACY_MONSTER_EGGS);
		registerBukkitToNukkit(BlockID.STONE_BRICK, Material.LEGACY_SMOOTH_BRICK);
		registerBukkitToNukkit(BlockID.BROWN_MUSHROOM_BLOCK, Material.LEGACY_HUGE_MUSHROOM_1);
		registerBukkitToNukkit(BlockID.RED_MUSHROOM_BLOCK, Material.LEGACY_HUGE_MUSHROOM_2);
		registerBukkitToNukkit(BlockID.IRON_BAR, Material.LEGACY_IRON_FENCE);
		registerBukkitToNukkit(BlockID.GLASS_PANE, Material.LEGACY_THIN_GLASS);
		registerBukkitToNukkit(BlockID.MELON_BLOCK, Material.LEGACY_MELON_BLOCK);
		registerBukkitToNukkit(BlockID.PUMPKIN_STEM, Material.LEGACY_PUMPKIN_STEM);
		registerBukkitToNukkit(BlockID.MELON_STEM, Material.LEGACY_MELON_STEM);
		registerBukkitToNukkit(BlockID.VINE, Material.LEGACY_VINE);
		registerBukkitToNukkit(BlockID.FENCE_GATE, Material.LEGACY_FENCE_GATE);
		registerBukkitToNukkit(BlockID.BRICK_STAIRS, Material.LEGACY_BRICK_STAIRS);
		registerBukkitToNukkit(BlockID.STONE_BRICK_STAIRS, Material.LEGACY_SMOOTH_STAIRS);
		registerBukkitToNukkit(BlockID.MYCELIUM, Material.LEGACY_MYCEL);
		registerBukkitToNukkit(BlockID.WATER_LILY, Material.LEGACY_WATER_LILY);
		registerBukkitToNukkit(BlockID.NETHER_BRICKS, Material.LEGACY_NETHER_BRICK);
		registerBukkitToNukkit(BlockID.NETHER_BRICK_FENCE, Material.LEGACY_NETHER_FENCE);
		registerBukkitToNukkit(BlockID.NETHER_BRICKS_STAIRS, Material.LEGACY_NETHER_BRICK_STAIRS);
		registerBukkitToNukkit(BlockID.NETHER_WART_BLOCK, Material.LEGACY_NETHER_WARTS);
		registerBukkitToNukkit(BlockID.ENCHANTMENT_TABLE, Material.LEGACY_ENCHANTMENT_TABLE);
		registerBukkitToNukkit(BlockID.BREWING_STAND_BLOCK, Material.LEGACY_BREWING_STAND);
		registerBukkitToNukkit(BlockID.CAULDRON_BLOCK, Material.LEGACY_CAULDRON);
		registerBukkitToNukkit(BlockID.END_PORTAL, Material.LEGACY_ENDER_PORTAL);
		registerBukkitToNukkit(BlockID.END_PORTAL_FRAME, Material.LEGACY_ENDER_PORTAL_FRAME);
		registerBukkitToNukkit(BlockID.END_STONE, Material.LEGACY_ENDER_STONE);
		registerBukkitToNukkit(BlockID.DRAGON_EGG, Material.LEGACY_DRAGON_EGG);
		registerBukkitToNukkit(BlockID.LIT_REDSTONE_LAMP, Material.LEGACY_REDSTONE_LAMP_OFF);
		registerBukkitToNukkit(BlockID.REDSTONE_LAMP, Material.LEGACY_REDSTONE_LAMP_ON);
		registerBukkitToNukkit(BlockID.DOUBLE_WOOD_SLAB, Material.LEGACY_WOOD_DOUBLE_STEP);
		registerBukkitToNukkit(BlockID.WOOD_SLAB, Material.LEGACY_WOOD_STEP);
		registerBukkitToNukkit(BlockID.COCOA, Material.LEGACY_COCOA);
		registerBukkitToNukkit(BlockID.SANDSTONE_STAIRS, Material.LEGACY_SANDSTONE_STAIRS);
		registerBukkitToNukkit(BlockID.EMERALD_ORE, Material.LEGACY_EMERALD_ORE);
		registerBukkitToNukkit(BlockID.ENDER_CHEST, Material.LEGACY_ENDER_CHEST);
		registerBukkitToNukkit(BlockID.TRIPWIRE_HOOK, Material.LEGACY_TRIPWIRE_HOOK);
		registerBukkitToNukkit(BlockID.TRIPWIRE, Material.LEGACY_TRIPWIRE);
		registerBukkitToNukkit(BlockID.EMERALD_BLOCK, Material.LEGACY_EMERALD_BLOCK);
		registerBukkitToNukkit(BlockID.SPRUCE_WOOD_STAIRS, Material.LEGACY_SPRUCE_WOOD_STAIRS);
		registerBukkitToNukkit(BlockID.BIRCH_WOOD_STAIRS, Material.LEGACY_BIRCH_WOOD_STAIRS);
		registerBukkitToNukkit(BlockID.JUNGLE_WOOD_STAIRS, Material.LEGACY_JUNGLE_WOOD_STAIRS);
		registerBukkitToNukkit(BlockID.BEACON, Material.LEGACY_BEACON);
		registerBukkitToNukkit(BlockID.COBBLE_WALL, Material.LEGACY_COBBLE_WALL);
		registerBukkitToNukkit(BlockID.FLOWER_POT_BLOCK, Material.LEGACY_FLOWER_POT);
		registerBukkitToNukkit(BlockID.CARROT_BLOCK, Material.LEGACY_CARROT);
		registerBukkitToNukkit(BlockID.POTATO_BLOCK, Material.LEGACY_POTATO);
		registerBukkitToNukkit(BlockID.WOODEN_BUTTON, Material.LEGACY_WOOD_BUTTON);
		registerBukkitToNukkit(BlockID.SKULL_BLOCK, Material.LEGACY_SKULL);
		registerBukkitToNukkit(BlockID.ANVIL, Material.LEGACY_ANVIL);
		registerBukkitToNukkit(BlockID.TRAPPED_CHEST, Material.LEGACY_TRAPPED_CHEST);
		registerBukkitToNukkit(BlockID.LIGHT_WEIGHTED_PRESSURE_PLATE, Material.LEGACY_GOLD_PLATE);
		registerBukkitToNukkit(BlockID.HEAVY_WEIGHTED_PRESSURE_PLATE, Material.LEGACY_IRON_PLATE);
		registerBukkitToNukkit(BlockID.UNPOWERED_COMPARATOR, Material.LEGACY_REDSTONE_COMPARATOR_OFF);
		registerBukkitToNukkit(BlockID.POWERED_COMPARATOR, Material.LEGACY_REDSTONE_COMPARATOR_ON);
		registerBukkitToNukkit(BlockID.DAYLIGHT_DETECTOR, Material.LEGACY_DAYLIGHT_DETECTOR);
		registerBukkitToNukkit(BlockID.REDSTONE_BLOCK, Material.LEGACY_REDSTONE_BLOCK);
		registerBukkitToNukkit(BlockID.QUARTZ_ORE, Material.LEGACY_QUARTZ_ORE);
		registerBukkitToNukkit(BlockID.HOPPER_BLOCK, Material.LEGACY_HOPPER);
		registerBukkitToNukkit(BlockID.QUARTZ_BLOCK, Material.LEGACY_QUARTZ_BLOCK);
		registerBukkitToNukkit(BlockID.QUARTZ_STAIRS, Material.LEGACY_QUARTZ_STAIRS);
		registerBukkitToNukkit(BlockID.ACTIVATOR_RAIL, Material.LEGACY_ACTIVATOR_RAIL);
		registerBukkitToNukkit(BlockID.DROPPER, Material.LEGACY_DROPPER);
		registerBukkitToNukkit(BlockID.STAINED_HARDENED_CLAY, Material.LEGACY_STAINED_CLAY);
		registerBukkitToNukkit(BlockID.STAINED_GLASS_PANE, Material.LEGACY_STAINED_GLASS_PANE);
		registerBukkitToNukkit(BlockID.LEAVES2, Material.LEGACY_LEAVES_2);
		registerBukkitToNukkit(BlockID.LOG2, Material.LEGACY_LOG_2);
		registerBukkitToNukkit(BlockID.ACACIA_WOODEN_STAIRS, Material.LEGACY_ACACIA_STAIRS);
		registerBukkitToNukkit(BlockID.DARK_OAK_WOODEN_STAIRS, Material.LEGACY_DARK_OAK_STAIRS);
		registerBukkitToNukkit(BlockID.SLIME_BLOCK, Material.LEGACY_SLIME_BLOCK);
		registerBukkitToNukkit(BlockID.INVISIBLE_BEDROCK, Material.LEGACY_BARRIER);
		registerBukkitToNukkit(BlockID.IRON_TRAPDOOR, Material.LEGACY_IRON_TRAPDOOR);
		registerBukkitToNukkit(BlockID.PRISMARINE, Material.LEGACY_PRISMARINE);
		registerBukkitToNukkit(BlockID.SEA_LANTERN, Material.LEGACY_SEA_LANTERN);
		registerBukkitToNukkit(BlockID.HAY_BALE, Material.LEGACY_HAY_BLOCK);
		registerBukkitToNukkit(BlockID.CARPET, Material.LEGACY_CARPET);
		registerBukkitToNukkit(BlockID.TERRACOTTA, Material.LEGACY_HARD_CLAY);
		registerBukkitToNukkit(BlockID.COAL_BLOCK, Material.LEGACY_COAL_BLOCK);
		registerBukkitToNukkit(BlockID.PACKED_ICE, Material.LEGACY_PACKED_ICE);
		registerBukkitToNukkit(BlockID.DOUBLE_PLANT, Material.LEGACY_DOUBLE_PLANT);
		registerBukkitToNukkit(BlockID.DAYLIGHT_DETECTOR_INVERTED, Material.LEGACY_DAYLIGHT_DETECTOR_INVERTED);
		registerBukkitToNukkit(BlockID.RED_SANDSTONE, Material.LEGACY_RED_SANDSTONE);
		registerBukkitToNukkit(BlockID.RED_SANDSTONE_STAIRS, Material.LEGACY_RED_SANDSTONE_STAIRS);
		registerBukkitToNukkit(BlockID.DOUBLE_STONE_SLAB, Material.LEGACY_DOUBLE_STONE_SLAB2);
		registerBukkitToNukkit(BlockID.STONE_SLAB, Material.LEGACY_STONE_SLAB2);
		registerBukkitToNukkit(BlockID.FENCE_GATE_SPRUCE, Material.LEGACY_SPRUCE_FENCE_GATE);
		registerBukkitToNukkit(BlockID.FENCE_GATE_BIRCH, Material.LEGACY_BIRCH_FENCE_GATE);
		registerBukkitToNukkit(BlockID.FENCE_GATE_JUNGLE, Material.LEGACY_JUNGLE_FENCE_GATE);
		registerBukkitToNukkit(BlockID.FENCE_GATE_DARK_OAK, Material.LEGACY_DARK_OAK_FENCE_GATE);
		registerBukkitToNukkit(BlockID.FENCE_GATE_ACACIA, Material.LEGACY_ACACIA_FENCE_GATE);
		registerBukkitToNukkit(BlockID.FENCE, Material.LEGACY_SPRUCE_FENCE);
		registerBukkitToNukkit(BlockID.FENCE, Material.LEGACY_BIRCH_FENCE);
		registerBukkitToNukkit(BlockID.FENCE, Material.LEGACY_JUNGLE_FENCE);
		registerBukkitToNukkit(BlockID.FENCE, Material.LEGACY_DARK_OAK_FENCE);
		registerBukkitToNukkit(BlockID.FENCE, Material.LEGACY_ACACIA_FENCE);
		registerBukkitToNukkit(BlockID.SPRUCE_DOOR_BLOCK, Material.LEGACY_SPRUCE_DOOR);
		registerBukkitToNukkit(BlockID.BIRCH_DOOR_BLOCK, Material.LEGACY_BIRCH_DOOR);
		registerBukkitToNukkit(BlockID.JUNGLE_DOOR_BLOCK, Material.LEGACY_JUNGLE_DOOR);
		registerBukkitToNukkit(BlockID.ACACIA_DOOR_BLOCK, Material.LEGACY_ACACIA_DOOR);
		registerBukkitToNukkit(BlockID.DARK_OAK_DOOR_BLOCK, Material.LEGACY_DARK_OAK_DOOR);
		registerBukkitToNukkit(BlockID.END_ROD, Material.LEGACY_END_ROD);
		registerBukkitToNukkit(BlockID.CHORUS_PLANT, Material.LEGACY_CHORUS_PLANT);
		registerBukkitToNukkit(BlockID.CHORUS_FLOWER, Material.LEGACY_CHORUS_FLOWER);
		registerBukkitToNukkit(BlockID.PURPUR_BLOCK, Material.LEGACY_PURPUR_BLOCK);
		registerBukkitToNukkit(202, Material.LEGACY_PURPUR_PILLAR);
		registerBukkitToNukkit(BlockID.PURPUR_STAIRS, Material.LEGACY_PURPUR_STAIRS);
		registerBukkitToNukkit(BlockID.END_BRICKS, Material.LEGACY_END_BRICKS);
		registerBukkitToNukkit(BlockID.BEETROOT_BLOCK, Material.LEGACY_BEETROOT_BLOCK);
		registerBukkitToNukkit(BlockID.GRASS_PATH, Material.LEGACY_GRASS_PATH);
		registerBukkitToNukkit(BlockID.END_GATEWAY, Material.LEGACY_END_GATEWAY);
		registerBukkitToNukkit(BlockID.ICE_FROSTED, Material.LEGACY_FROSTED_ICE);
		registerBukkitToNukkit(BlockID.MAGMA, Material.LEGACY_MAGMA);
		registerBukkitToNukkit(BlockID.NETHER_WART_BLOCK, Material.LEGACY_NETHER_WART_BLOCK);
		registerBukkitToNukkit(BlockID.RED_NETHER_BRICK, Material.LEGACY_RED_NETHER_BRICK);
		registerBukkitToNukkit(BlockID.BONE_BLOCK, Material.LEGACY_BONE_BLOCK);
		registerBukkitToNukkit(BlockID.AIR, Material.LEGACY_STRUCTURE_VOID);
		registerBukkitToNukkit(BlockID.OBSERVER, Material.LEGACY_OBSERVER);
		registerBukkitToNukkit(BlockID.SHULKER_BOX, Material.LEGACY_WHITE_SHULKER_BOX);
		registerBukkitToNukkit(BlockID.SHULKER_BOX, Material.LEGACY_ORANGE_SHULKER_BOX);
		registerBukkitToNukkit(BlockID.SHULKER_BOX, Material.LEGACY_MAGENTA_SHULKER_BOX);
		registerBukkitToNukkit(BlockID.SHULKER_BOX, Material.LEGACY_LIGHT_BLUE_SHULKER_BOX);
		registerBukkitToNukkit(BlockID.SHULKER_BOX, Material.LEGACY_YELLOW_SHULKER_BOX);
		registerBukkitToNukkit(BlockID.SHULKER_BOX, Material.LEGACY_LIME_SHULKER_BOX);
		registerBukkitToNukkit(BlockID.SHULKER_BOX, Material.LEGACY_PINK_SHULKER_BOX);
		registerBukkitToNukkit(BlockID.SHULKER_BOX, Material.LEGACY_GRAY_SHULKER_BOX);
		registerBukkitToNukkit(BlockID.SHULKER_BOX, Material.LEGACY_SILVER_SHULKER_BOX);
		registerBukkitToNukkit(BlockID.SHULKER_BOX, Material.LEGACY_CYAN_SHULKER_BOX);
		registerBukkitToNukkit(BlockID.SHULKER_BOX, Material.LEGACY_PURPLE_SHULKER_BOX);
		registerBukkitToNukkit(BlockID.SHULKER_BOX, Material.LEGACY_BLUE_SHULKER_BOX);
		registerBukkitToNukkit(BlockID.SHULKER_BOX, Material.LEGACY_BROWN_SHULKER_BOX);
		registerBukkitToNukkit(BlockID.SHULKER_BOX, Material.LEGACY_GREEN_SHULKER_BOX);
		registerBukkitToNukkit(BlockID.SHULKER_BOX, Material.LEGACY_RED_SHULKER_BOX);
		registerBukkitToNukkit(BlockID.SHULKER_BOX, Material.LEGACY_BLACK_SHULKER_BOX);
		registerBukkitToNukkit(BlockID.WHITE_GLAZED_TERRACOTTA, Material.LEGACY_WHITE_GLAZED_TERRACOTTA);
		registerBukkitToNukkit(BlockID.ORANGE_GLAZED_TERRACOTTA, Material.LEGACY_ORANGE_GLAZED_TERRACOTTA);
		registerBukkitToNukkit(BlockID.MAGENTA_GLAZED_TERRACOTTA, Material.LEGACY_MAGENTA_GLAZED_TERRACOTTA);
		registerBukkitToNukkit(BlockID.LIGHT_BLUE_GLAZED_TERRACOTTA, Material.LEGACY_LIGHT_BLUE_GLAZED_TERRACOTTA);
		registerBukkitToNukkit(BlockID.YELLOW_GLAZED_TERRACOTTA, Material.LEGACY_YELLOW_GLAZED_TERRACOTTA);
		registerBukkitToNukkit(BlockID.LIME_GLAZED_TERRACOTTA, Material.LEGACY_LIME_GLAZED_TERRACOTTA);
		registerBukkitToNukkit(BlockID.PINK_GLAZED_TERRACOTTA, Material.LEGACY_PINK_GLAZED_TERRACOTTA);
		registerBukkitToNukkit(BlockID.GRAY_GLAZED_TERRACOTTA, Material.LEGACY_GRAY_GLAZED_TERRACOTTA);
		registerBukkitToNukkit(BlockID.SILVER_GLAZED_TERRACOTTA, Material.LEGACY_SILVER_GLAZED_TERRACOTTA);
		registerBukkitToNukkit(BlockID.CYAN_GLAZED_TERRACOTTA, Material.LEGACY_CYAN_GLAZED_TERRACOTTA);
		registerBukkitToNukkit(BlockID.PURPLE_GLAZED_TERRACOTTA, Material.LEGACY_PURPLE_GLAZED_TERRACOTTA);
		registerBukkitToNukkit(BlockID.BLUE_GLAZED_TERRACOTTA, Material.LEGACY_BLUE_GLAZED_TERRACOTTA);
		registerBukkitToNukkit(BlockID.BROWN_GLAZED_TERRACOTTA, Material.LEGACY_BROWN_GLAZED_TERRACOTTA);
		registerBukkitToNukkit(BlockID.GREEN_GLAZED_TERRACOTTA, Material.LEGACY_GREEN_GLAZED_TERRACOTTA);
		registerBukkitToNukkit(BlockID.RED_GLAZED_TERRACOTTA, Material.LEGACY_RED_GLAZED_TERRACOTTA);
		registerBukkitToNukkit(BlockID.BLACK_GLAZED_TERRACOTTA, Material.LEGACY_BLACK_GLAZED_TERRACOTTA);
		registerBukkitToNukkit(BlockID.CONCRETE, Material.LEGACY_CONCRETE);
		registerBukkitToNukkit(BlockID.CONCRETE_POWDER, Material.LEGACY_CONCRETE_POWDER);
		registerBukkitToNukkit(BlockID.AIR, Material.LEGACY_STRUCTURE_BLOCK);
	}

	/**
	 * Gets the Bukkit (non-legacy) material for the given Nukkit block id.
	 *
	 * @param nukkitId
	 *            The Nukkit block id.
	 * @return The material, or AIR for negative Nukkit block ids, or STONE if
	 *         unknown.
	 */
	public static Material getMaterial(int nukkitId) {
		if (nukkitId < 0) {
			return Material.AIR;
		}
		if (nukkitId >= nukkitToBukkit.length) {
			return Material.STONE;
		}
		Material material = nukkitToBukkit[nukkitId];
		if (material == null) {
			return Material.STONE;
		}
		return material;
	}

	/**
	 * Gets the Nukkit block for the Bukkit material. The Bukkit material may be
	 * a legacy material.
	 *
	 * @param material
	 *            The material.
	 * @param blockData
	 *            The block data, 0 by default.
	 * @return The block.
	 */
	public static Block getNukkitBlock(Material material, int blockData) {
		int nukkit = bukkitToNukkit[material.ordinal()];
		return cn.nukkit.block.Block.get(nukkit, blockData);
	}

	private static void registerBukkitToBukkit(int nukkit, Material bukkit) {
		nukkitToBukkit[nukkit] = bukkit;
	}

	private static void registerBukkitToNukkit(int nukkit, Material bukkit) {
		bukkitToNukkit[bukkit.ordinal()] = (char) nukkit;
	}

	private static void registerTwoWay(int nukkit, Material bukkit) {
		registerBukkitToNukkit(nukkit, bukkit);
		registerBukkitToBukkit(nukkit, bukkit);
	}
}
