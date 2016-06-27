package nl.rutgerkok.pokkit.plugin;

import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

/**
 * Converts between {@link Permission} and
 * {@link cn.nukkit.permission.Permission}.
 *
 */
public class PokkitPermission {

    public static Permission toBukkit(cn.nukkit.permission.Permission nukkit) {
        PermissionDefault defaultValue = PermissionDefault.getByName(nukkit.getDefault());
        return new Permission(nukkit.getName(), nukkit.getDescription(), defaultValue, nukkit.getChildren());
    }

    public static cn.nukkit.permission.Permission toNukkit(Permission permission) {
        String defaultValue = toNukkitPermissionDefault(permission.getDefault());
        return new cn.nukkit.permission.Permission(permission.getName(), permission.getDescription(), defaultValue,
                permission.getChildren());
    }

    private static String toNukkitPermissionDefault(PermissionDefault permissionDefault) {
        switch (permissionDefault) {
            case FALSE:
                return cn.nukkit.permission.Permission.DEFAULT_FALSE;
            case NOT_OP:
                return cn.nukkit.permission.Permission.DEFAULT_NOT_OP;
            case OP:
                return cn.nukkit.permission.Permission.DEFAULT_OP;
            case TRUE:
                return cn.nukkit.permission.Permission.DEFAULT_TRUE;
            default:
                throw new UnsupportedOperationException("Unknown permission default: " + permissionDefault);
        }
    }
}
