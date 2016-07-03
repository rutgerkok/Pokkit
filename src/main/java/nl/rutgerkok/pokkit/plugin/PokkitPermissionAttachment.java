package nl.rutgerkok.pokkit.plugin;

import java.util.Map;
import java.util.Objects;

import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionRemovedExecutor;

/**
 * Wrapper around a Nukkit permission attachment, so that remove listeners still
 * work.
 *
 */
public final class PokkitPermissionAttachment extends PermissionAttachment {

    public static PermissionAttachment toBukkit(cn.nukkit.permission.PermissionAttachment nukkit,
            Permissible permissible) {
        return new PokkitPermissionAttachment(nukkit, permissible);
    }

    public static cn.nukkit.permission.PermissionAttachment toNukkit(PermissionAttachment attachment) {
        if (attachment == null) {
            return null;
        }

        // Try to unwrap
        if (attachment instanceof PokkitPermissionAttachment) {
            return ((PokkitPermissionAttachment) attachment).nukkit;
        }

        throw new IllegalArgumentException("Cannot convert " + attachment);
    }

    private final cn.nukkit.permission.PermissionAttachment nukkit;
    private final Permissible permissible;

    private PokkitPermissionAttachment(cn.nukkit.permission.PermissionAttachment attachment, Permissible permissible) {
        super(PokkitPlugin.toBukkit((PokkitPlugin) attachment.getPlugin()), permissible);
        this.nukkit = Objects.requireNonNull(attachment, "attachment");
        this.permissible = Objects.requireNonNull(permissible, "permissible");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PokkitPermissionAttachment other = (PokkitPermissionAttachment) obj;
        return nukkit.equals(other.nukkit);
    }

    @Override
    public Permissible getPermissible() {
        return permissible;
    }

    @Override
    public Map<String, Boolean> getPermissions() {
        return nukkit.getPermissions();
    }

    @Override
    public PermissionRemovedExecutor getRemovalCallback() {
        return attachment -> nukkit.getRemovalCallback().attachmentRemoved(nukkit);
    }

    @Override
    public int hashCode() {
        return nukkit.hashCode();
    }

    @Override
    public boolean remove() {
        nukkit.remove();
        return true;
    }

    @Override
    public void setPermission(Permission perm, boolean value) {
        nukkit.setPermission(PokkitPermission.toNukkit(perm), value);
    }

    @Override
    public void setPermission(String name, boolean value) {
        nukkit.setPermission(name, value);
    }

    @Override
    public void setRemovalCallback(PermissionRemovedExecutor executor) {
        nukkit.setRemovalCallback(attachment -> executor.attachmentRemoved(this));
    }

    @Override
    public void unsetPermission(Permission perm) {
        unsetPermission(perm.getName());
    }

    @Override
    public void unsetPermission(String name) {
        nukkit.unsetPermission(name, false);
    }
}
