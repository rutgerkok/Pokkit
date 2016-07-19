package nl.rutgerkok.pokkit.world;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import cn.nukkit.block.BlockAir;
import cn.nukkit.item.ItemBlock;
import nl.rutgerkok.pokkit.Pokkit;
import nl.rutgerkok.pokkit.PokkitServer;
import nl.rutgerkok.pokkit.blockstate.PokkitBlockState;
import nl.rutgerkok.pokkit.material.PokkitMaterialData;
import nl.rutgerkok.pokkit.metadata.BlockMetadataStore;
import nl.rutgerkok.pokkit.world.item.PokkitItemStack;

/**
 * Converts between Nukkit and Bukkit blocks.
 *
 */
public final class PokkitBlock implements Block {

    public static final PokkitBlock toBukkit(cn.nukkit.block.Block nukkit) {
        if (nukkit == null) {
            return null;
        }
        return new PokkitBlock(nukkit);
    }

    public static final cn.nukkit.block.Block toNukkit(Block block) {
        if (block == null) {
            return null;
        }
        return ((PokkitBlock) block).nukkit;
    }

    private cn.nukkit.block.Block nukkit;

    /**
     * Allows for changed drops.
     */
    private List<ItemStack> drops;

    private PokkitBlock(cn.nukkit.block.Block nukkit) {
        this.nukkit = Objects.requireNonNull(nukkit, "nukkit");
    }

    @Override
    public boolean breakNaturally() {
        return nukkit.level.useBreakOn(nukkit) != null;
    }

    @Override
    public boolean breakNaturally(ItemStack tool) {
        return nukkit.level.useBreakOn(nukkit, PokkitItemStack.toNukkitCopy(tool)) != null;
    }

    @Override
    public Biome getBiome() {
        throw Pokkit.unsupported();

    }

    private BlockMetadataStore getBlockMetadata() {
        return ((PokkitServer) Bukkit.getServer()).getMetadata().getBlockMetadata();
    }

    @Override
    public int getBlockPower() {
        throw Pokkit.unsupported();

    }

    @Override
    public int getBlockPower(BlockFace face) {
        throw Pokkit.unsupported();

    }

    @Override
    public Chunk getChunk() {
        return PokkitChunk.of(this);
    }

    @Override
    public byte getData() {
        return (byte) PokkitMaterialData.getBlockData(PokkitMaterialData.nukkitToBukkit(nukkit));
    }

    @Override
    public Collection<ItemStack> getDrops() {
        return getDrops0(null);
    }

    @Override
    public Collection<ItemStack> getDrops(ItemStack tool) {
        return getDrops0(PokkitItemStack.toNukkitCopy(tool));
    }

    private Collection<ItemStack> getDrops0(cn.nukkit.item.Item item) {
        if (this.drops != null) {
            return this.drops;
        }

        if (item == null) {
            item = new ItemBlock(new BlockAir(), 0, 0);
        }

        int[][] drops = nukkit.getDrops(item);
        List<ItemStack> result = new ArrayList<>();
        for (int[] drop : drops) {
            int bukkitCombinedId = PokkitMaterialData.nukkitToBukkit(drop[0], drop[1]);
            ItemStack stack = new ItemStack(PokkitMaterialData.getMaterial(bukkitCombinedId), drop[2],
                    (short) PokkitMaterialData.getBlockData(bukkitCombinedId));
            result.add(stack);
        }
        this.drops = result;
        return result;
    }

    @Override
    public BlockFace getFace(Block block) {
        for (BlockFace face : BlockFace.values()) {
            if (block.getX() == this.getX() + face.getModX()
                    && block.getY() == this.getY() + face.getModY()
                    && block.getZ() == this.getZ() + face.getModZ()) {
                return face;
            }
        }
        return null;
    }

    @Override
    public double getHumidity() {
        int biomeId = nukkit.getLevel().getBiomeId((int) nukkit.x, (int) nukkit.z);
        cn.nukkit.level.generator.biome.Biome biome = cn.nukkit.level.generator.biome.Biome.getBiome(biomeId);
        return biome.getRainfall();
    }

    @Override
    public byte getLightFromBlocks() {
        throw Pokkit.unsupported();

    }

    @Override
    public byte getLightFromSky() {
        throw Pokkit.unsupported();

    }

    @Override
    public byte getLightLevel() {
        return (byte) nukkit.getLightLevel();
    }

    @Override
    public Location getLocation() {
        return new Location(PokkitWorld.toBukkit(nukkit.level), nukkit.x, nukkit.y, nukkit.z);
    }

    @Override
    public Location getLocation(Location loc) {
        loc.setWorld(PokkitWorld.toBukkit(nukkit.level));
        loc.setX(nukkit.x);
        loc.setY(nukkit.y);
        loc.setZ(nukkit.z);
        loc.setYaw(0f);
        loc.setPitch(0f);
        return loc;
    }

    @Override
    public List<MetadataValue> getMetadata(String metadataKey) {
        return getBlockMetadata().getMetadata(this, metadataKey);
    }

    @Override
    public PistonMoveReaction getPistonMoveReaction() {
        throw Pokkit.unsupported();

    }

    @Override
    public Block getRelative(BlockFace face) {
        return getRelative(face.getModX(), face.getModY(), face.getModZ());
    }

    @Override
    public Block getRelative(BlockFace face, int distance) {
        return getRelative(face.getModX() * distance, face.getModY() * distance, face.getModZ() * distance);
    }

    @Override
    public Block getRelative(int modX, int modY, int modZ) {
        return getWorld().getBlockAt(getX() + modX, getY() + modY, getZ() + modZ);
    }

    @Override
    public BlockState getState() {
        return PokkitBlockState.getBlockState(this);
    }

    @Override
    public double getTemperature() {
        int biomeId = nukkit.getLevel().getBiomeId((int) nukkit.x, (int) nukkit.z);
        cn.nukkit.level.generator.biome.Biome biome = cn.nukkit.level.generator.biome.Biome.getBiome(biomeId);
        return biome.getTemperature();
    }

    @Override
    public Material getType() {
        int combinedBukkitId = PokkitMaterialData.nukkitToBukkit(nukkit);
        return PokkitMaterialData.getMaterial(combinedBukkitId);
    }

    /**
     * Gets the material data in one call.
     * @return The material data.
     */
    @SuppressWarnings("deprecation")
    public MaterialData getTypeData() {
        int combinedBukkitId = PokkitMaterialData.nukkitToBukkit(nukkit);
        byte bukkitBlockData = (byte) PokkitMaterialData.getBlockData(combinedBukkitId);
        return PokkitMaterialData.getMaterial(combinedBukkitId).getNewData(bukkitBlockData);
    }

    @Override
    public int getTypeId() {
        @SuppressWarnings("deprecation")
        int typeId = getType().getId();
        return typeId;
    }

    @Override
    public PokkitWorld getWorld() {
        return PokkitWorld.toBukkit(nukkit.level);
    }

    @Override
    public int getX() {
        return (int) nukkit.x;
    }

    @Override
    public int getY() {
        return (int) nukkit.y;
    }

    @Override
    public int getZ() {
        return (int) nukkit.z;
    }

    @Override
    public boolean hasMetadata(String metadataKey) {
        return getBlockMetadata().hasMetadata(this, metadataKey);
    }

    @Override
    public boolean isBlockFaceIndirectlyPowered(BlockFace face) {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean isBlockFacePowered(BlockFace face) {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean isBlockIndirectlyPowered() {
        return nukkit.isNeighborPowered();
    }

    @Override
    public boolean isBlockPowered() {
        return nukkit.isPowered();
    }

    @Override
    public boolean isEmpty() {
        return nukkit.getId() == cn.nukkit.block.Block.AIR;
    }

    @Override
    public boolean isLiquid() {
        return nukkit instanceof cn.nukkit.block.BlockLiquid;
    }

    @Override
    public void removeMetadata(String metadataKey, Plugin owningPlugin) {
        getBlockMetadata().removeMetadata(this, metadataKey, owningPlugin);
    }

    @Override
    public void setBiome(Biome bio) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setData(byte data) {
        setData(data, true);
    }

    @Override
    public void setData(byte data, boolean applyPhysics) {
        if (data == getData()) {
            return;
        }

        int combinedNukkitId = PokkitMaterialData.bukkitToNukkit(getType(), data);
        setType0(combinedNukkitId, applyPhysics);
    }

    @Override
    public void setMetadata(String metadataKey, MetadataValue newMetadataValue) {
        getBlockMetadata().setMetadata(this, metadataKey, newMetadataValue);
    }

    @Override
    public void setType(Material type) {
        setType(type, true);
    }

    @Override
    public void setType(Material type, boolean applyPhysics) {
        if (type == getType()) {
            return;
        }
        if (type == null) {
            type = Material.AIR;
        }

        int nukkitCombinedId = PokkitMaterialData.bukkitToNukkit(type, getData());
        setType0(nukkitCombinedId, applyPhysics);
    }

    private void setType0(int nukkitCombinedId, boolean applyPhysics) {
        int nukkitId = PokkitMaterialData.getNukkitBlockId(nukkitCombinedId);
        int nukkitData = PokkitMaterialData.getBlockData(nukkitCombinedId);
        nukkit.level.setBlock(nukkit, cn.nukkit.block.Block.get(nukkitId, nukkitData), false, applyPhysics);

        // Update block reference
        nukkit = nukkit.level.getBlock(nukkit);
    }

    /**
     * Sets a material and data at the same time.
     * 
     * @param materialData
     *            The material and data.
     * @param applyPhysics
     *            Whether a physics update must be performed.
     */
    public void setTypeAndData(MaterialData materialData, boolean applyPhysics) {
        int nukkitCombinedId = PokkitMaterialData.bukkitToNukkit(materialData);
        setType0(nukkitCombinedId, applyPhysics);
    }

    @Override
    @Deprecated
    public boolean setTypeId(int typeId) {
        return setTypeId(typeId, true);
    }

    @Override
    @Deprecated
    public boolean setTypeId(int typeId, boolean applyPhysics) {
        Material material = Material.getMaterial(typeId);
        if (material == null) {
            return false;
        }
        setType(material, applyPhysics);
        return true;
    }

    @Override
    @Deprecated
    public boolean setTypeIdAndData(int typeId, byte data, boolean applyPhysics) {
        Material type = Material.getMaterial(typeId);
        if (type == null) {
            return false;
        }

        int nukkitCombinedId = PokkitMaterialData.bukkitToNukkit(type, data);
        setType0(nukkitCombinedId, applyPhysics);
        return true;
    }

}
