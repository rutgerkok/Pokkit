package nl.rutgerkok.pokkit;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.help.HelpMap;
import org.bukkit.help.HelpTopic;
import org.bukkit.help.HelpTopicFactory;

import com.google.common.collect.ImmutableList;

/**
 * Help topic registry. For now, there is no way to display these, to the
 * methods in this class don't do anything interesting.
 *
 */
final class PokkitHelpMap implements HelpMap {

    private final Map<String, HelpTopic> helpTopics = new HashMap<>();

    @Override
    public void addTopic(HelpTopic topic) {
        helpTopics.put(topic.getName().toLowerCase(), topic);
    }

    @Override
    public void clear() {
        helpTopics.clear();
    }

    @Override
    public HelpTopic getHelpTopic(String topicName) {
        return helpTopics.get(topicName.toLowerCase());
    }

    @Override
    public Collection<HelpTopic> getHelpTopics() {
        return ImmutableList.copyOf(helpTopics.values());
    }

    @Override
    public List<String> getIgnoredPlugins() {
        return Collections.emptyList();
    }

    @Override
    public void registerHelpTopicFactory(Class<?> commandClass, HelpTopicFactory<?> factory) {
        // Help topics are not implemented yet
    }

}
