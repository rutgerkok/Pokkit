package nl.rutgerkok.pokkit.metadata;

import java.util.List;

import org.bukkit.metadata.MetadataValue;

import cn.nukkit.level.Level;

/**
 * Overview of all meta data on a server.
 *
 */
public final class AllMetadataStore {

	private final PlayerMetadataStore playerMeta = new PlayerMetadataStore();
	private final WorldMetadataStore worldMeta = new WorldMetadataStore();
	private final BlockMetadataStore blockMeta = new BlockMetadataStore();

	public BlockMetadataStore getBlockMetadata() {
		return blockMeta;
	}

	public List<MetadataValue> getBlockMetadata(Level level) {
		// TODO Auto-generated method stub
		return null;
	}

	public PlayerMetadataStore getPlayerMetadata() {
		return playerMeta;
	}

	public WorldMetadataStore getWorldMetadata() {
		return worldMeta;
	}

}
