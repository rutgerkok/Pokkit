package nl.rutgerkok.pokkit.scheduler;

import java.util.Objects;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import cn.nukkit.scheduler.TaskHandler;

final class PokkitTask implements BukkitTask {

	private final TaskHandler nukkit;
	private final Plugin plugin;

	PokkitTask(TaskHandler nukkit, Plugin plugin) {
		this.nukkit = Objects.requireNonNull(nukkit, "nukkit");
		this.plugin = Objects.requireNonNull(plugin, "plugin");
	}

	@Override
	public void cancel() {
		nukkit.cancel();
	}

	@Override
	public Plugin getOwner() {
		return plugin;
	}

	@Override
	public int getTaskId() {
		return nukkit.getTaskId();
	}

	@Override
	public boolean isCancelled() {
		return nukkit.isCancelled();
	}

	@Override
	public boolean isSync() {
		return !nukkit.isAsynchronous();
	}
}
