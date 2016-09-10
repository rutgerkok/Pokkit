package nl.rutgerkok.pokkit.plugin;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.bukkit.plugin.PluginAwareness;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.AbstractConstruct;
import org.yaml.snakeyaml.constructor.SafeConstructor;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.Tag;

import cn.nukkit.plugin.PluginDescription;
import cn.nukkit.utils.Utils;

/**
 * Variant of {@link PluginDescription} that handles the Bukkit plugin.yml
 * format.
 *
 */
final class PokkitPluginDescription extends PluginDescription {

	private static Yaml getYaml() {
		return new Yaml(new SafeConstructor() {
			{
				yamlConstructors.put(null, new AbstractConstruct() {
					@Override
					public Object construct(final Node node) {
						if (!node.getTag().startsWith("!@")) {
							// Unknown tag - will fail
							return SafeConstructor.undefinedConstructor.construct(node);
						}
						// Unknown awareness - provide a graceful substitution
						return new PluginAwareness() {
							@Override
							public String toString() {
								return node.toString();
							}
						};
					}
				});
				for (final PluginAwareness.Flags flag : PluginAwareness.Flags.values()) {
					yamlConstructors.put(new Tag("!@" + flag.name()), new AbstractConstruct() {
						@Override
						public PluginAwareness.Flags construct(final Node node) {
							return flag;
						}
					});
				}
			}
		});
	}

	private static Map<String, Object> readStream(InputStream stream) throws IOException {
		Yaml yaml = getYaml();
		@SuppressWarnings("unchecked")
		Map<String, Object> input = (Map<String, Object>) yaml.load(Utils.readFile(stream));

		// Nukkit requires startup to be in UPPERCASE
		if (input.get("load") instanceof String) {
			input.put("load", input.get("load").toString().toUpperCase(Locale.ENGLISH));
		}
		// Nukkit requires version to be a string (and not a double like 0.1)
		input.put("version", String.valueOf(input.get("version")));

		return input;
	}

	/**
	 * Creates a new plugin description of a Bukkit plugin.
	 * 
	 * @param stream
	 *            The UTF-8 stream to read from.
	 * @throws IOException
	 *             If an IO error occurs.
	 */
	PokkitPluginDescription(InputStream stream) throws IOException {
		super(readStream(stream));
	}

	@Override
	public List<String> getCompatibleAPIs() {
		return Arrays.asList("1.0.0");
	}

}
