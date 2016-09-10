package nl.rutgerkok.pokkit.startup;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginClassLoader;

public final class NukkitHook extends PluginBase {

	/**
	 * The Pokkit object, loaded using another class loader. (so no
	 * communication is possible, except using reflection).
	 */
	private Object pokkit;

	@Override
	public void onDisable() {
		try {
			pokkit.getClass().getMethod("onDisable", PluginBase.class).invoke(pokkit, this);
		} catch (Throwable t) {
			this.getLogger().error("Error disabling plugin", t);
		}
	}

	@Override
	public void onEnable() {
		try {
			pokkit.getClass().getMethod("onEnable", PluginBase.class).invoke(pokkit, this);
		} catch (Throwable t) {
			this.getLogger().error("Error disabling plugin", t);
		}
	}

	@Override
	public void onLoad() {
		try {
			PluginClassLoader nukkitLoader = (PluginClassLoader) getClass().getClassLoader();
			@SuppressWarnings("resource")
			UrlFirstClassLoader classLoader = new UrlFirstClassLoader(nukkitLoader.getURLs(), nukkitLoader.getParent());

			pokkit = classLoader.loadClass("nl.rutgerkok.pokkit.Pokkit").newInstance();
			pokkit.getClass().getMethod("onLoad", PluginBase.class).invoke(pokkit, this);
		} catch (Throwable t) {
			this.getLogger().error("Error loading plugin", t);
		}
	}
}
