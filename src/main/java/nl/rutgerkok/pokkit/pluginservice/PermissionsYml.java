package nl.rutgerkok.pokkit.pluginservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;
import org.yaml.snakeyaml.error.MarkedYAMLException;

import cn.nukkit.plugin.PluginBase;

/**
 * Service to load the permissions.yml file.
 *
 */
public final class PermissionsYml implements PokkitService {

	private Logger getLogger() {
		return Bukkit.getLogger();
	}

	/**
	 * Based on CraftBukkit code.
	 *
	 * @param file
	 *            The custom permissions file.
	 */
	private void loadCustomPermissions(File file) {
		Yaml yaml = new Yaml(new SafeConstructor());

		try (FileInputStream stream = new FileInputStream(file)) {
			@SuppressWarnings("unchecked")
			Map<String, Map<String, Object>> perms = (Map<String, Map<String, Object>>) yaml.load(stream);

			if (perms == null) {
				getLogger().log(Level.INFO, "Server permissions file " + file + " is empty, ignoring it");
				return;
			}

			List<Permission> permsList = Permission.loadPermissions(perms,
					"Permission node '%s' in " + file + " is invalid", Permission.DEFAULT_PERMISSION);

			for (Permission perm : permsList) {
				try {
					Bukkit.getPluginManager().addPermission(perm);
				} catch (IllegalArgumentException ex) {
					getLogger().log(Level.SEVERE, "Permission in " + file + " was already defined", ex);
				}
			}
		} catch (FileNotFoundException ex) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				getLogger().log(Level.SEVERE, "Cannot create server permisisons file " + file, e);
			}
		} catch (MarkedYAMLException ex) {
			getLogger().log(Level.WARNING, "Server permissions file " + file + " is not valid YAML: " + ex.toString());
		} catch (Throwable ex) {
			getLogger().log(Level.WARNING, "Server permissions file " + file + " is not valid YAML.", ex);
		}
	}

	@Override
	public void onLoad(PluginBase pokkit) {

		File file = new File(pokkit.getDataFolder(), "permissions.yml");
		loadCustomPermissions(file);

	}
}
