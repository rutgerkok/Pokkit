package nl.rutgerkok.pokkit.inventory.custom;

import java.io.IOException;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Objects;

import cn.nukkit.Player;
import cn.nukkit.inventory.BaseInventory;
import cn.nukkit.inventory.InventoryType;
import cn.nukkit.item.Item;
import cn.nukkit.level.GlobalBlockPalette;
import cn.nukkit.math.Vector3;
import cn.nukkit.nbt.NBTIO;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.network.protocol.BlockEntityDataPacket;
import cn.nukkit.network.protocol.ContainerClosePacket;
import cn.nukkit.network.protocol.ContainerOpenPacket;
import cn.nukkit.network.protocol.UpdateBlockPacket;

/**
 * A class that opens a custom inventory for a player. Based on <a href=
 * "https://gist.github.com/Creeperface01/2340f5ebf8fc48705086bdfc26d49606">this
 * class</a> by Creeperface01. Only supports chests for now, no other
 * inventories.
 *
 */
public class NukkitCustomInventory extends BaseInventory {

	private HashMap<String, Vector3> spawnedFakeChestBlocks = new HashMap<>();

	public final String customName;

	public NukkitCustomInventory(String customName, org.bukkit.inventory.InventoryHolder holder) {
		super(new BukkitToNukkitInventoryHolder(holder), InventoryType.CHEST);
		getHolder().setInventory(this);
		this.customName = Objects.requireNonNull(customName, "customName");
	}

	@Override
	public BukkitToNukkitInventoryHolder getHolder() {
		return (BukkitToNukkitInventoryHolder) this.holder;
	}

	private CompoundTag getSpawnCompound(Vector3 position) {
		CompoundTag tag = new CompoundTag();
		tag.putString("id", "Chest");
		tag.putInt("x", (int) position.x);
		tag.putInt("y", (int) position.y);
		tag.putInt("z", (int) position.z);
		tag.putString("CustomName", this.customName);

		return tag;
	}

	@Override
	public void onClose(Player player) {
		// Remove the fake chest block
		ContainerClosePacket closePacket = new ContainerClosePacket();
		closePacket.windowId = (byte) player.getWindowId(this);
		player.dataPacket(closePacket);

		Vector3 v = spawnedFakeChestBlocks.get(player.getName().toLowerCase());

		if (v != null) {
			player.getLevel().sendBlocks(new Player[] { player }, new Vector3[] { v });
		}

		spawnedFakeChestBlocks.remove(player.getName().toLowerCase());

		super.onClose(player);
	}

	@Override
    public void onOpen(Player who) {
        super.onOpen(who);

        UpdateBlockPacket updateBlockPacket = new UpdateBlockPacket();

		// Place a chest block
        Vector3 v = new Vector3(who.getFloorX(), who.getFloorY() + 5, who.getFloorZ());
		spawnedFakeChestBlocks.put(who.getName().toLowerCase(), v.clone());
        updateBlockPacket.x = (int) v.x;
        updateBlockPacket.y = (int) v.y;
        updateBlockPacket.z = (int) v.z;
		updateBlockPacket.blockRuntimeId = GlobalBlockPalette.getOrCreateRuntimeId(Item.CHEST, 0);
        updateBlockPacket.flags = UpdateBlockPacket.FLAG_ALL_PRIORITY;
        who.dataPacket(updateBlockPacket);

		// Place chest data packet
        BlockEntityDataPacket blockEntityDataPacket = new BlockEntityDataPacket();
        blockEntityDataPacket.x = (int) v.x;
        blockEntityDataPacket.y = (int) v.y;
        blockEntityDataPacket.z = (int) v.z;

        try {
            blockEntityDataPacket.namedTag = NBTIO.write(getSpawnCompound(v), ByteOrder.LITTLE_ENDIAN);
        } catch (IOException e) {
			throw new RuntimeException(e);
        }

        who.dataPacket(blockEntityDataPacket);

		// Open the chest
        ContainerOpenPacket containerOpenPacket = new ContainerOpenPacket();
        containerOpenPacket.windowId = (byte) who.getWindowId(this);
        containerOpenPacket.type = (byte) this.getType().getNetworkType();

        containerOpenPacket.x = (int) v.x;
        containerOpenPacket.y = (int) v.y;
        containerOpenPacket.z = (int) v.z;

        who.dataPacket(containerOpenPacket);

        this.sendContents(who);
    }
}