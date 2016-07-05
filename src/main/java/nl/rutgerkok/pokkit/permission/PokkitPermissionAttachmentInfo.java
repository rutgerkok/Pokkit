package nl.rutgerkok.pokkit.permission;

import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.PermissionAttachmentInfo;

/**
 * Converts {@link PermissionAttachmentInfo}.
 *
 */
public final class PokkitPermissionAttachmentInfo {

    public static PermissionAttachmentInfo toBukkit(cn.nukkit.permission.PermissionAttachmentInfo nukkit) {
        Permissible bukkitPermissible = PokkitPermissible.toBukkit(nukkit.getPermissible());
        return new PermissionAttachmentInfo(
                bukkitPermissible,
                nukkit.getPermission(),
                PokkitPermissionAttachment.toBukkit(nukkit.getAttachment(), bukkitPermissible),
                nukkit.getValue());
    }
}
