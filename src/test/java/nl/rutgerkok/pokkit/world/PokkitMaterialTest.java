package nl.rutgerkok.pokkit.world;

import static org.junit.Assert.assertEquals;

import nl.rutgerkok.pokkit.material.PokkitMaterialData;

import org.bukkit.Material;
import org.junit.Ignore;
import org.junit.Test;

import cn.nukkit.block.Block;

public class PokkitMaterialTest {

	@Test
	public void testAir() {
		assertEquals(Block.AIR, PokkitMaterialData.fromBukkit(Material.AIR, 0).getNukkitId());
	}

	@Test
	public void testDamage() {
		// Damage value must be kept at 3
		assertEquals(3, PokkitMaterialData.fromBukkit(Material.WOOL, 3).getNukkitDamage());
	}

	@Test
	@Ignore // no remap support yet
	public void testIdRemap() {
		// GRASS_PATH has a different block id in Bukkit and Nukkit
		assertEquals(Block.GRASS_PATH, PokkitMaterialData.fromBukkit(Material.GRASS_PATH, 0).getNukkitId());
	}

	@Test
	public void testSameIdInBukkitAndNukkit() {
		assertEquals(Block.STONE, PokkitMaterialData.fromBukkit(Material.STONE, 0).getNukkitId());
		assertEquals(Block.GRASS, PokkitMaterialData.fromBukkit(Material.GRASS, 0).getNukkitId());
	}
}
