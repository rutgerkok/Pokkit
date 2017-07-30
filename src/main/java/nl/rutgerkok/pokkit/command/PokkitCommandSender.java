package nl.rutgerkok.pokkit.command;

import java.util.Objects;
import java.util.Set;

import nl.rutgerkok.pokkit.Pokkit;
import nl.rutgerkok.pokkit.permission.PokkitPermission;
import nl.rutgerkok.pokkit.player.PokkitPlayer;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.chat.BaseComponent;

public class PokkitCommandSender extends CommandSender.Spigot implements CommandSender {

	public static CommandSender toBukkit(cn.nukkit.command.CommandSender nukkit) {
		// More specialized ones to support instanceof checks
		if (nukkit instanceof cn.nukkit.Player) {
			return PokkitPlayer.toBukkit((cn.nukkit.Player) nukkit);
		}
		if (nukkit instanceof cn.nukkit.command.ConsoleCommandSender) {
			return new PokkitConsoleCommandSender((cn.nukkit.command.ConsoleCommandSender) nukkit);
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

	protected PokkitCommandSender(cn.nukkit.command.CommandSender nukkit) {
		this.nukkit = Objects.requireNonNull(nukkit);
	}

	@Override
	public PermissionAttachment addAttachment(Plugin plugin) {
		throw Pokkit.unsupported();
	}

	@Override
	public PermissionAttachment addAttachment(Plugin plugin, int ticks) {
		throw Pokkit.unsupported();
	}

	@Override
	public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value) {
		throw Pokkit.unsupported();
	}

	@Override
	public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, int ticks) {
		throw Pokkit.unsupported();
	}

	@Override
	public Set<PermissionAttachmentInfo> getEffectivePermissions() {
		throw Pokkit.unsupported();
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
		throw Pokkit.unsupported();
	}

	@Override
	public void sendMessage(BaseComponent component) {
		sendMessage(component.toLegacyText());
	}

	@Override
	public void sendMessage(BaseComponent... components) {
		StringBuilder text = new StringBuilder();
		for (BaseComponent component : components) {
			text.append(component.toLegacyText());
		}
		sendMessage(text.toString());
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

	@Override
	public Spigot spigot() {
		return this;
	}

}
