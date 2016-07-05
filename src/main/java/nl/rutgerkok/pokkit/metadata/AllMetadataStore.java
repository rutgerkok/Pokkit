package nl.rutgerkok.pokkit.metadata;

/**
 * Overview of all meta data on a server.
 *
 */
public final class AllMetadataStore {

    private final PlayerMetadataStore playerMeta = new PlayerMetadataStore();
    private final WorldMetadataStore worldMeta = new WorldMetadataStore();

    public PlayerMetadataStore getPlayerMetadata() {
        return playerMeta;
    }

    public WorldMetadataStore getWorldMetadata() {
        return worldMeta;
    }

}
