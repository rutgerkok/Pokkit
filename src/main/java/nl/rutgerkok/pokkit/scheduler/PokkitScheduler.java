package nl.rutgerkok.pokkit.scheduler;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scheduler.BukkitWorker;

import nl.rutgerkok.pokkit.Pokkit;
import nl.rutgerkok.pokkit.plugin.PokkitPlugin;

import cn.nukkit.scheduler.ServerScheduler;

/**
 * Scheduler that forwards to Nukkit.
 *
 * <p>
 * Nukkit lacks a way to schedule delayed/repeating async tasks, so we have to
 * use a sync task for the delay/repetition. This sync task then schedules an
 * async task.
 *
 */
public final class PokkitScheduler implements BukkitScheduler {

	private final ServerScheduler nukkit;

	public PokkitScheduler(ServerScheduler nukkit) {
		this.nukkit = Objects.requireNonNull(nukkit);
	}

	@Override
	public <T> Future<T> callSyncMethod(Plugin plugin, Callable<T> callable) {
		CompletableFuture<T> future = new CompletableFuture<>();
		nukkit.scheduleTask(PokkitPlugin.toNukkit(plugin), () -> {
			try {
				future.complete(callable.call());
			} catch (Throwable t) {
				future.completeExceptionally(t);
			}
		}, false);
		return future;
	}

	@Override
	public void cancelTask(int taskId) {
		nukkit.cancelTask(taskId);
	}

	@Override
	public void cancelTasks(Plugin plugin) {
		nukkit.cancelTask(PokkitPlugin.toNukkit(plugin));
	}

	@Override
	public List<BukkitWorker> getActiveWorkers() {
		throw Pokkit.unsupported();

	}

	@Override
	public List<BukkitTask> getPendingTasks() {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean isCurrentlyRunning(int arg0) {
		throw Pokkit.unsupported();

	}

	@Override
	public boolean isQueued(int taskId) {
		return nukkit.isQueued(taskId);
	}

	@Override
	public BukkitTask runTask(Plugin plugin, BukkitRunnable runnable) throws IllegalArgumentException {
		Objects.requireNonNull(plugin, "plugin");
		return new PokkitTask(nukkit.scheduleTask(PokkitPlugin.toNukkit(plugin), runnable, false), plugin);
	}

	@Override
	public BukkitTask runTask(Plugin plugin, Runnable runnable) throws IllegalArgumentException {
		Objects.requireNonNull(plugin, "plugin");
		return new PokkitTask(nukkit.scheduleTask(PokkitPlugin.toNukkit(plugin), runnable, false), plugin);
	}

	@Override
	public BukkitTask runTaskAsynchronously(Plugin plugin, BukkitRunnable runnable) throws IllegalArgumentException {
		Objects.requireNonNull(plugin, "plugin");
		return new PokkitTask(nukkit.scheduleTask(PokkitPlugin.toNukkit(plugin), runnable, true), plugin);
	}

	@Override
	public BukkitTask runTaskAsynchronously(Plugin plugin, Runnable runnable) throws IllegalArgumentException {
		Objects.requireNonNull(plugin, "plugin");
		return new PokkitTask(nukkit.scheduleTask(PokkitPlugin.toNukkit(plugin), runnable, true), plugin);
	}

	@Override
	public BukkitTask runTaskLater(Plugin plugin, BukkitRunnable runnable, long delay) throws IllegalArgumentException {
		Objects.requireNonNull(plugin, "plugin");
		return new PokkitTask(nukkit.scheduleDelayedTask(PokkitPlugin.toNukkit(plugin), runnable, (int) delay), plugin);
	}

	@Override
	public BukkitTask runTaskLater(Plugin plugin, Runnable task, long delay) throws IllegalArgumentException {
		Objects.requireNonNull(plugin, "plugin");
		return new PokkitTask(nukkit.scheduleDelayedTask(PokkitPlugin.toNukkit(plugin), task, (int) delay), plugin);
	}

	@Override
	public BukkitTask runTaskLaterAsynchronously(Plugin plugin, BukkitRunnable task, long delay)
			throws IllegalArgumentException {
		return runTaskLater(plugin, () -> {
			runTaskAsynchronously(plugin, task);
		}, delay);
	}

	@Override
	public BukkitTask runTaskLaterAsynchronously(Plugin plugin, Runnable task, long delay)
			throws IllegalArgumentException {
		return runTaskLater(plugin, () -> {
			runTaskAsynchronously(plugin, task);
		}, delay);
	}

	@Override
	public BukkitTask runTaskTimer(Plugin plugin, BukkitRunnable task, long delay, long period)
			throws IllegalArgumentException {
		return new PokkitTask(
				nukkit.scheduleDelayedRepeatingTask(PokkitPlugin.toNukkit(plugin), task, (int) delay, (int) period),
				plugin);
	}

	@Override
	public BukkitTask runTaskTimer(Plugin plugin, Runnable task, long delay, long period)
			throws IllegalArgumentException {
		return new PokkitTask(
				nukkit.scheduleDelayedRepeatingTask(PokkitPlugin.toNukkit(plugin), task, (int) delay, (int) period),
				plugin);
	}

	@Override
	public BukkitTask runTaskTimerAsynchronously(Plugin plugin, BukkitRunnable task, long delay, long period)
			throws IllegalArgumentException {
		return runTaskTimer(plugin, () -> {
			runTaskAsynchronously(plugin, task);
		}, delay, period);
	}

	@Override
	public BukkitTask runTaskTimerAsynchronously(Plugin plugin, Runnable task, long delay, long period)
			throws IllegalArgumentException {
		return runTaskTimer(plugin, () -> {
			runTaskAsynchronously(plugin, task);
		}, delay, period);
	}

	@Override
	public int scheduleAsyncDelayedTask(Plugin plugin, Runnable task) {
		return this.runTaskAsynchronously(plugin, task).getTaskId();
	}

	@Override
	public int scheduleAsyncDelayedTask(Plugin plugin, Runnable task, long delay) {
		return this.runTaskLaterAsynchronously(plugin, task, delay).getTaskId();
	}

	@Override
	public int scheduleAsyncRepeatingTask(Plugin plugin, Runnable task, long delay, long period) {
		return this.runTaskTimerAsynchronously(plugin, task, delay, period).getTaskId();
	}

	@Override
	public int scheduleSyncDelayedTask(Plugin plugin, BukkitRunnable task) {
		return this.runTask(plugin, task).getTaskId();
	}

	@Override
	public int scheduleSyncDelayedTask(Plugin plugin, BukkitRunnable task, long delay) {
		return this.runTaskLater(plugin, task, delay).getTaskId();
	}

	@Override
	public int scheduleSyncDelayedTask(Plugin plugin, Runnable task) {
		return this.runTask(plugin, task).getTaskId();
	}

	@Override
	public int scheduleSyncDelayedTask(Plugin plugin, Runnable task, long delay) {
		return this.runTaskLater(plugin, task, delay).getTaskId();
	}

	@Override
	public int scheduleSyncRepeatingTask(Plugin plugin, BukkitRunnable task, long delay, long period) {
		return this.runTaskTimer(plugin, task, delay, period).getTaskId();
	}

	@Override
	public int scheduleSyncRepeatingTask(Plugin plugin, Runnable task, long delay, long period) {
		return this.runTaskTimer(plugin, task, delay, period).getTaskId();
	}

}
