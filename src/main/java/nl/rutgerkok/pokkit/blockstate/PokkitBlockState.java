package nl.rutgerkok.pokkit.blockstate;

import java.util.List;
import java.util.Objects;

import nl.rutgerkok.pokkit.world.PokkitBlock;
import nl.rutgerkok.pokkit.world.PokkitWorld;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import cn.nukkit.blockentity.BlockEntity;
import cn.nukkit.blockentity.BlockEntitySign;
import cn.nukkit.math.Vector3;

public abstract class PokkitBlockState implements BlockState {

    public static PokkitBlockState getBlockState(PokkitBlock block) {
        BlockEntity blockEntity = PokkitWorld.toNukkit(block.getWorld()).getBlockEntity(PokkitBlock.toNukkit(block));
        MaterialData materialData = block.getTypeData();
        Location location = block.getLocation();

        if (blockEntity instanceof BlockEntitySign) {
            return new SignBlockState(location, materialData, ((BlockEntitySign) blockEntity).getText());
        }
        return new PlainBlockState(location, materialData);
    }

    private PokkitWorld worldOrNull;
    private final int x, y, z;
    private MaterialData materialData;

    protected PokkitBlockState(Location locationOrNull, MaterialData materialData) {
        if (locationOrNull == null) {
            this.worldOrNull = null;
            this.x = 0;
            this.y = 0;
            this.z = 0;
        } else {
            this.worldOrNull = (PokkitWorld) locationOrNull.getWorld();
            this.x = locationOrNull.getBlockX();
            this.y = locationOrNull.getBlockY();
            this.z = locationOrNull.getBlockZ();
        }

        this.materialData = Objects.requireNonNull(materialData);
    }

    @Override
    public PokkitBlock getBlock() {
        return getWorld().getBlockAt(x, y, z);
    }

    /**
     * Gets the Nukkit block entity.
     * 
     * @return The Nukkit block entity.
     */
    public BlockEntity getBlockEntity() {
        return PokkitWorld.toNukkit(getWorld()).getBlockEntity(new Vector3(x, y, z));
    }

    @Override
    public Chunk getChunk() {
        return getWorld().getChunkAt(getLocation());
    }

    @Override
    public MaterialData getData() {
        return materialData.clone();
    }

    @Override
    public byte getLightLevel() {
        return getBlock().getLightLevel();
    }

    @Override
    public Location getLocation() {
        return new Location(getWorld(), x, y, z);
    }

    @Override
    public Location getLocation(Location loc) {
        loc.setWorld(getWorld());
        loc.setX(x);
        loc.setY(y);
        loc.setZ(z);
        loc.setYaw(0);
        loc.setPitch(0);
        return loc;
    }

    @Override
    public List<MetadataValue> getMetadata(String metadataKey) {
        return getBlock().getMetadata(metadataKey);
    }

    @Override
    @Deprecated
    public byte getRawData() {
        return materialData.getData();
    }

    @Override
    public Material getType() {
        return materialData.getItemType();
    }

    @Override
    @Deprecated
    public int getTypeId() {
        return getType().getId();
    }

    @Override
    public PokkitWorld getWorld() {
        PokkitWorld world = this.worldOrNull;
        if (world == null) {
            throw new IllegalStateException("block state is not placed in the world");
        }
        return world;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getZ() {
        return z;
    }

    @Override
    public boolean hasMetadata(String metadataKey) {
        return getBlock().hasMetadata(metadataKey);
    }

    @Override
    public boolean isPlaced() {
        return this.worldOrNull != null;
    }

    /**
     * Called when the update is method is called successfully. Use this to
     * update other block properties, like the text on the sign.
     */
    protected abstract void onUpdate();

    @Override
    public void removeMetadata(String metadataKey, Plugin owningPlugin) {
        getBlock().removeMetadata(metadataKey, owningPlugin);
    }

    @Override
    public void setData(MaterialData data) {
        this.materialData = Objects.requireNonNull(data, "data");
    }

    @Override
    public void setMetadata(String metadataKey, MetadataValue newMetadataValue) {
        getBlock().setMetadata(metadataKey, newMetadataValue);
    }

    @Override
    @Deprecated
    public void setRawData(byte data) {
        this.materialData.setData(data);
    }

    @Override
    public void setType(Material type) {
        @SuppressWarnings("deprecation")
        MaterialData withNewType = type.getNewData(getRawData());
        this.materialData = withNewType;
    }

    @Override
    @Deprecated
    public boolean setTypeId(int typeId) {
        Material material = Material.getMaterial(typeId);
        if (material == null) {
            return false;
        }
        setType(material);
        return true;
    }

    @Override
    public final boolean update() {
        return update(false);
    }

    @Override
    public final boolean update(boolean force) {
        return update(force, true);
    }

    @Override
    public final boolean update(boolean force, boolean applyPhysics) {
        PokkitBlock block = getBlock();
        if (force || block.getType() == materialData.getItemType()) {
            block.setTypeAndData(materialData, applyPhysics);
            onUpdate();
            return true;
        }
        return false;
    }

}
