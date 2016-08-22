package nl.rutgerkok.pokkit.world;

import org.bukkit.WorldType;

import cn.nukkit.level.generator.Generator;

public final class PokkitWorldType {

    /**
     * Converts the world type.
     * 
     * @param worldType
     *            The Bukkit world type.
     * @return The Nukkit world type.
     * @throws IllegalArgumentException
     *             When the Bukkit world type cannot be translated.
     */
    public static Class<? extends Generator> toNukkit(WorldType worldType) throws IllegalArgumentException {
        switch (worldType) {
            case FLAT:
                return Generator.getGenerator(Generator.TYPE_FLAT);
            case NORMAL:
                return Generator.getGenerator(Generator.TYPE_INFINITE);
            case VERSION_1_1:
                return Generator.getGenerator(Generator.TYPE_OLD);
            default:
                throw new IllegalArgumentException("World type does not exist on Pocket Edition: " + worldType);
        }
    }
}
