package nl.rutgerkok.pokkit.metadata;

import org.bukkit.block.Block;
import org.bukkit.metadata.MetadataStore;
import org.bukkit.metadata.MetadataStoreBase;

/**
 * A BlockMetadataStore stores metadata values for {@link Block} objects.
 * <p>
 * Unlike the implementation written by Bukkit, this implementation supports all
 * worlds in one object.
 */
public final class BlockMetadataStore extends MetadataStoreBase<Block> implements MetadataStore<Block> {

    @Override
    protected String disambiguate(Block block, String metadataKey) {
        return block.getWorld().getName() + ":" + block.getX() + ":" + block.getY() + ":"
                + block.getZ() + ":" + metadataKey;
    }

}

