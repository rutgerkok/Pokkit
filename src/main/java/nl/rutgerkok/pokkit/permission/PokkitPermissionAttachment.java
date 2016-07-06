package nl.rutgerkok.pokkit.permission;

import java.util.Map;

import nl.rutgerkok.pokkit.Reflection;
import nl.rutgerkok.pokkit.plugin.PokkitPlugin;

import org.bukkit.permissions.PermissionAttachment;

/**
 * Converters between Bukkit and Nukkit PermissionAttachments, establishing a
 * magic link between the permission maps.
 *
 */
public final class PokkitPermissionAttachment {

    public static PermissionAttachment toBukkit(cn.nukkit.permission.PermissionAttachment nukkit) {
        if (nukkit == null) {
            return null;
        }

        PermissionAttachment bukkit = new PermissionAttachment(
                PokkitPlugin.toBukkit(nukkit.getPlugin()),
                PokkitPermissible
                        .toBukkit(Reflection.getValueInFieldOfType(nukkit, cn.nukkit.permission.Permissible.class)));

        // Create magic link (= share reference) between permission maps (this
        // keeps them in sync)
        Reflection.setValueInFieldOfType(bukkit, Map.class, nukkit.getPermissions());

        return bukkit;
    }

    public static cn.nukkit.permission.PermissionAttachment toNukkit(PermissionAttachment bukkit) {
        if (bukkit == null) {
            return null;
        }

        cn.nukkit.permission.PermissionAttachment nukkit = new cn.nukkit.permission.PermissionAttachment(
                PokkitPlugin.toNukkit(bukkit.getPlugin()),
                PokkitPermissible.toNukkit(bukkit.getPermissible()));

        // Create magic link (= share references) between permission maps (this
        // keeps them in sync)
        // We can't simply use bukkit.getPermissions(), as that method returns a
        // copy of the map, so we can't create a magic link
        @SuppressWarnings("unchecked")
        Map<String, Boolean> bukkitPermissions = Reflection.getValueInFieldOfType(bukkit, Map.class);
        nukkit.setPermissions(bukkitPermissions);

        return nukkit;
    }


}
