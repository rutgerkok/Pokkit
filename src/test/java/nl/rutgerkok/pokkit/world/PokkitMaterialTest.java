package nl.rutgerkok.pokkit.world;

import static org.junit.Assert.assertEquals;

import org.bukkit.Material;
import org.junit.BeforeClass;
import org.junit.Test;

import nl.rutgerkok.pokkit.blockdata.PokkitBlockData;

import cn.nukkit.block.Block;

public class PokkitMaterialTest {

	@BeforeClass
	public static void init() {
		Block.init();
	}

	@Test
	public void testAir() {
		assertEquals(Block.AIR, PokkitBlockData.createBlockData(Material.AIR, 0).getNukkitId());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testDamage() {
		// Damage value must be kept at 3
		assertEquals(3, PokkitBlockData.createBlockData(Material.LEGACY_WOOL, 3).getNukkitData());
	}

	@Test
	public void testIdRemap() {
		// GRASS_PATH has a different block id in Bukkit and Nukkit
		assertEquals(Block.GRASS_PATH, PokkitBlockData.createBlockData(Material.GRASS_PATH, 0).getNukkitId());
	}

	@Test
	public void testSameIdInBukkitAndNukkit() {
		assertEquals(Block.STONE, PokkitBlockData.createBlockData(Material.STONE, 0).getNukkitId());
		assertEquals(Block.GRASS, PokkitBlockData.createBlockData(Material.GRASS_BLOCK, 0).getNukkitId());
	}
}
