package nl.rutgerkok.pokkit.material;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.bukkit.Material;
import org.bukkit.TreeSpecies;
import org.bukkit.material.MaterialData;
import org.bukkit.material.WoodenStep;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import cn.nukkit.item.Item;

@RunWith(Parameterized.class)
public class PokkitMaterialDataTest {

    @SuppressWarnings("deprecation")
    @Parameters(name = "{index}: {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { Item.get(Item.STONE), new MaterialData(Material.STONE) },
                { Item.get(Item.WOOD_SLAB, 2), new WoodenStep(TreeSpecies.BIRCH) },
                { Item.get(Item.PODZOL), new MaterialData(Material.DIRT, (byte) 2) }
        });
    }

    @Parameter(value = 0)
    public Item nukkit;
    @Parameter(value = 1)
    public MaterialData bukkit;

    @Test
    public void testBukkitToNukkit() {
        @SuppressWarnings("deprecation")
        int combinedNukkitId = PokkitMaterialData.bukkitToNukkit(bukkit.getItemType(), bukkit.getData());

        assertEquals(nukkit.getId(), PokkitMaterialData.getNukkitBlockId(combinedNukkitId));
        assertEquals(nukkit.getDamage(), PokkitMaterialData.getBlockData(combinedNukkitId));
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testNukkitToBukkit() {
        int combinedBukkitId = PokkitMaterialData.nukkitToBukkit(nukkit);

        assertEquals(bukkit.getItemType(), PokkitMaterialData.getMaterial(combinedBukkitId));
        assertEquals(bukkit.getData(), PokkitMaterialData.getBlockData(combinedBukkitId));
    }

}
