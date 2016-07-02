package nl.rutgerkok.pokkit.command;

import java.util.Objects;
import java.util.Set;

import nl.rutgerkok.pokkit.Pokkit;
import nl.rutgerkok.pokkit.PokkitPlayer;
import nl.rutgerkok.pokkit.plugin.PokkitPermission;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;

public class PokkitCommandSender implements CommandSender {

    public static CommandSender toBukkit(cn.nukkit.command.CommandSender nukkit) {
        if (nukkit instanceof cn.nukkit.Player) {
            // More specialized one to support instanceof checks
            return PokkitPlayer.toBukkit((cn.nukkit.Player) nukkit);
        }
        return new PokkitCommandSender(nukkit);
    }

    public static cn.nukkit.command.CommandSender toNukkit(CommandSender sender) {
        if (sender == null) {
            return null;
        }
        if (sender instanceof PokkitCommandSender) {
            return ((PokkitCommandSender) sender).nukkit;
        }
        if (sender instanceof Player) {
            return PokkitPlayer.toNukkit((Player) sender);
        }
        throw new IllegalArgumentException("Don't know how to convert " + sender.getClass());
    }

    private final cn.nukkit.command.CommandSender nukkit;

    private PokkitCommandSender(cn.nukkit.command.CommandSender nukkit) {
        this.nukkit = Objects.requireNonNull(nukkit);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin) {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, int ticks) {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value) {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, int ticks) {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);
    }

    @Override
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);
    }

    @Override
    public String getName() {
        return nukkit.getName();
    }

    @Override
    public Server getServer() {
        return Bukkit.getServer();
    }

    @Override
    public boolean hasPermission(Permission perm) {
        return nukkit.hasPermission(PokkitPermission.toNukkit(perm));
    }

    @Override
    public boolean hasPermission(String name) {
        return nukkit.hasPermission(name);
    }

    @Override
    public boolean isOp() {
        return nukkit.isOp();
    }

    @Override
    public boolean isPermissionSet(Permission perm) {
        return nukkit.isPermissionSet(PokkitPermission.toNukkit(perm));
    }

    @Override
    public boolean isPermissionSet(String name) {
        return nukkit.isPermissionSet(name);
    }

    @Override
    public void recalculatePermissions() {
        nukkit.recalculatePermissions();
    }

    @Override
    public void removeAttachment(PermissionAttachment attachment) {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);
    }

    @Override
    public void sendMessage(String message) {
        nukkit.sendMessage(message);
    }

    @Override
    public void sendMessage(String[] messages) {
        for (String message : messages) {
            nukkit.sendMessage(message);
        }
    }

    @Override
    public void setOp(boolean value) {
        nukkit.setOp(value);
    }

}
