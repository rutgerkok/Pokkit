package nl.rutgerkok.pokkit.plugin;

import java.io.File;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import com.google.common.base.Preconditions;

import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginDescription;

/**
 * Plugin loader for Nukkit that loads Bukkit plugins.
 *
 * <p>
 * Nukkit requires the instance to be created using reflection, which makes it
 * difficult for us to obtain the instance. We work around this by letting the
 * constructor upload the instance to a global variable, so that it can then be
 * retrieved exactly once.
 */
public final class PokkitPluginLoader implements cn.nukkit.plugin.PluginLoader {

	private static PokkitPluginLoader temporaryInstance;

	static PokkitPluginLoader getInstanceBack() {
		Preconditions.checkState(temporaryInstance != null, "No temporary instance available");
		PokkitPluginLoader returnValue = temporaryInstance;
		temporaryInstance = null;
		return returnValue;
	}

	private JavaPluginLoader bukkit;

	public PokkitPluginLoader(cn.nukkit.Server server) {
		// Required for instantiation
		temporaryInstance = this;
	}

	@Override
	public void disablePlugin(Plugin plugin) {
		getPluginLoader().disablePlugin(PokkitPlugin.toBukkit(plugin));
	}

	@Override
	public void enablePlugin(Plugin plugin) {
		getPluginLoader().enablePlugin(PokkitPlugin.toBukkit(plugin));
	}

	@Override
	public PluginDescription getPluginDescription(File file) {
		try (JarFile jar = new JarFile(file)) {
			JarEntry entry = jar.getJarEntry("plugin.yml");
			if (entry == null) {
				return null;
			}

			InputStream stream = jar.getInputStream(entry);
			return new PokkitPluginDescription(stream);
		} catch (Exception e) {
			Bukkit.getServer().getLogger().log(Level.SEVERE, "Could not load plugin " + file.getName(), e);
			return null;
		}
	}

	@Override
	public PluginDescription getPluginDescription(String fileName) {
		return getPluginDescription(new File(fileName));
	}

	@Override
	public Pattern[] getPluginFilters() {
		return new Pattern[] { Pattern.compile("^.+\\.jar$") };
	}

	@SuppressWarnings("deprecation")
	private JavaPluginLoader getPluginLoader() {
		if (bukkit == null) {
			bukkit = new JavaPluginLoader(Bukkit.getServer());
		}
		return bukkit;
	}

	@Override
	public Plugin loadPlugin(File file) throws Exception {
		JavaPlugin bukkitPlugin = (JavaPlugin) getPluginLoader().loadPlugin(file);
		return new PokkitPlugin(bukkitPlugin, getPluginDescription(file), this);
	}

	@Override
	public Plugin loadPlugin(String filename) throws Exception {
		return loadPlugin(new File(filename));
	}

}
