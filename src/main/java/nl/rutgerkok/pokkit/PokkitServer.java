package nl.rutgerkok.pokkit;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import nl.rutgerkok.pokkit.command.PokkitCommandMap;
import nl.rutgerkok.pokkit.command.PokkitCommandSender;
import nl.rutgerkok.pokkit.player.OnlinePlayerData;
import nl.rutgerkok.pokkit.player.PokkitOfflinePlayer;
import nl.rutgerkok.pokkit.player.PokkitPlayer;
import nl.rutgerkok.pokkit.plugin.PokkitPluginManager;
import nl.rutgerkok.pokkit.scheduler.PokkitScheduler;
import nl.rutgerkok.pokkit.scoreboard.PokkitScoreboardManager;
import nl.rutgerkok.pokkit.world.PokkitWorld;

import org.bukkit.BanList;
import org.bukkit.BanList.Type;
import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.UnsafeValues;
import org.bukkit.Warning.WarningState;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.generator.ChunkGenerator.ChunkData;
import org.bukkit.help.HelpMap;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.map.MapView;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.SimpleServicesManager;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.util.CachedServerIcon;

import com.avaje.ebean.config.ServerConfig;
import com.google.common.collect.ImmutableMap;

@SuppressWarnings("deprecation")
public final class PokkitServer implements Server {

    public static cn.nukkit.Server toNukkit(Server server) {
        return ((PokkitServer) server).nukkit;
    }

    private final cn.nukkit.Server nukkit;
    private final PokkitScheduler scheduler;
    private final PokkitPluginManager pluginManager;
    private final PokkitCommandMap commandMap;
    private final File pluginFolder;
    private final SimpleServicesManager servicesManager;
    private final ScoreboardManager scoreboardManager;
    private final OnlinePlayerData onlinePlayerData;
    private Logger logger;

    public PokkitServer(cn.nukkit.Server nukkitServer, Logger logger, File pluginFolder) {
        this.nukkit = Objects.requireNonNull(nukkitServer, "nukkitServer");
        this.pluginFolder = Objects.requireNonNull(pluginFolder, "pluginFolder");
        this.logger = Objects.requireNonNull(logger, "logger");

        this.scheduler = new PokkitScheduler(nukkitServer.getScheduler());
        this.pluginManager = new PokkitPluginManager(nukkitServer.getPluginManager());
        this.servicesManager = new SimpleServicesManager();
        this.commandMap = new PokkitCommandMap(nukkitServer::getPluginCommand);
        this.scoreboardManager = new PokkitScoreboardManager();
        this.onlinePlayerData = new OnlinePlayerData();
    }

    @Override
    public Player[] _INVALID_getOnlinePlayers() {
        return nukkit.getOnlinePlayers()
                .values()
                .stream()
                .map(PokkitPlayer::toBukkit)
                .toArray(Player[]::new);
    }

    @Override
    public boolean addRecipe(Recipe arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void banIP(String arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public int broadcast(String arg0, String arg1) {
        throw Pokkit.unsupported();

    }

    @Override
    public int broadcastMessage(String arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void clearRecipes() {
        throw Pokkit.unsupported();

    }

    @Override
    public void configureDbConfig(ServerConfig arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public BossBar createBossBar(String arg0, BarColor arg1, BarStyle arg2, BarFlag... arg3) {
        throw Pokkit.unsupported();

    }

    @Override
    public ChunkData createChunkData(World arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public Inventory createInventory(InventoryHolder arg0, int arg1) throws IllegalArgumentException {
        throw Pokkit.unsupported();

    }

    @Override
    public Inventory createInventory(InventoryHolder arg0, int arg1, String arg2) throws IllegalArgumentException {
        throw Pokkit.unsupported();

    }

    @Override
    public Inventory createInventory(InventoryHolder arg0, InventoryType arg1) {
        throw Pokkit.unsupported();

    }

    @Override
    public Inventory createInventory(InventoryHolder arg0, InventoryType arg1, String arg2) {
        throw Pokkit.unsupported();

    }

    @Override
    public MapView createMap(World arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public World createWorld(WorldCreator arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean dispatchCommand(CommandSender sender, String command) throws CommandException {
        return nukkit.dispatchCommand(PokkitCommandSender.toNukkit(sender), command);
    }

    @Override
    public boolean getAllowEnd() {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean getAllowFlight() {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean getAllowNether() {
        throw Pokkit.unsupported();

    }

    @Override
    public int getAmbientSpawnLimit() {
        throw Pokkit.unsupported();

    }

    @Override
    public int getAnimalSpawnLimit() {
        throw Pokkit.unsupported();

    }

    @Override
    public BanList getBanList(Type arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public Set<OfflinePlayer> getBannedPlayers() {
        throw Pokkit.unsupported();

    }

    @Override
    public String getBukkitVersion() {
        return Pokkit.VERSION;
    }

    @Override
    public Map<String, String[]> getCommandAliases() {
        ImmutableMap.Builder<String, String[]> allAliases = ImmutableMap.builder();
        nukkit.getCommandAliases().forEach(
                (command, aliasList) -> allAliases.put(command, aliasList.toArray(new String[0])));
        return allAliases.build();
    }

    @Override
    public long getConnectionThrottle() {
        throw Pokkit.unsupported();

    }

    @Override
    public ConsoleCommandSender getConsoleSender() {
        return (ConsoleCommandSender) PokkitCommandSender.toBukkit(nukkit.getConsoleSender());
    }

    @Override
    public GameMode getDefaultGameMode() {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean getGenerateStructures() {
        throw Pokkit.unsupported();

    }

    @Override
    public HelpMap getHelpMap() {
        throw Pokkit.unsupported();

    }

    @Override
    public int getIdleTimeout() {
        throw Pokkit.unsupported();

    }

    @Override
    public String getIp() {
        throw Pokkit.unsupported();

    }

    @Override
    public Set<String> getIPBans() {
        throw Pokkit.unsupported();

    }

    @Override
    public ItemFactory getItemFactory() {
        throw Pokkit.unsupported();

    }

    @Override
    public Set<String> getListeningPluginChannels() {
        return Collections.emptySet();
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

    @Override
    public MapView getMap(short arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public int getMaxPlayers() {
        return nukkit.getMaxPlayers();
    }

    @Override
    public Messenger getMessenger() {
        throw Pokkit.unsupported();

    }

    @Override
    public int getMonsterSpawnLimit() {
        throw Pokkit.unsupported();

    }

    @Override
    public String getMotd() {
        return nukkit.getMotd();
    }

    @Override
    public String getName() {
        return nukkit.getName();
    }

    @Override
    public OfflinePlayer getOfflinePlayer(String name) {
        return PokkitOfflinePlayer.toBukkit(nukkit.getOfflinePlayer(name));
    }

    @Override
    public OfflinePlayer getOfflinePlayer(UUID uuid) {
        return getOfflinePlayer(UniqueIdConversion.playerIdToName(uuid));
    }

    @Override
    public OfflinePlayer[] getOfflinePlayers() {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean getOnlineMode() {
        // Pretend we are running in online mode - this should stop plugins from
        // complaining
        return true;
    }

    /**
     * Gets the data of all online players.
     * @return The online player data.
     */
    public OnlinePlayerData getOnlinePlayerData() {
        return onlinePlayerData;
    }

    @Override
    public Collection<? extends Player> getOnlinePlayers() {
        return nukkit.getOnlinePlayers()
                .values()
                .stream()
                .map(PokkitPlayer::toBukkit)
                .collect(Collectors.toList());
    }

    @Override
    public Set<OfflinePlayer> getOperators() {
        throw Pokkit.unsupported();

    }

    @Override
    public Player getPlayer(String name) {
        return PokkitPlayer.toBukkit(nukkit.getPlayer(name));
    }

    @Override
    public Player getPlayer(UUID uuid) {
        return PokkitPlayer.toBukkit(nukkit.getOnlinePlayers().get(uuid));
    }

    @Override
    public Player getPlayerExact(String name) {
        return PokkitPlayer.toBukkit(nukkit.getPlayer(name));
    }

    @Override
    public PluginCommand getPluginCommand(String name) {
        return commandMap.getPluginCommand(name);
    }

    @Override
    public PluginManager getPluginManager() {
        return pluginManager;
    }

    @Override
    public int getPort() {
        throw Pokkit.unsupported();

    }

    @Override
    public List<Recipe> getRecipesFor(ItemStack arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public BukkitScheduler getScheduler() {
        return scheduler;
    }

    @Override
    public ScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }

    @Override
    public CachedServerIcon getServerIcon() {
        throw Pokkit.unsupported();

    }

    @Override
    public String getServerId() {
        return nukkit.getServerUniqueId().toString();
    }

    @Override
    public String getServerName() {
        throw Pokkit.unsupported();

    }

    @Override
    public ServicesManager getServicesManager() {
        return servicesManager;
    }

    @Override
    public String getShutdownMessage() {
        return "Server closed";
    }

    @Override
    public int getSpawnRadius() {
        return nukkit.getSpawnRadius();
    }

    @Override
    public int getTicksPerAnimalSpawns() {
        throw Pokkit.unsupported();

    }

    @Override
    public int getTicksPerMonsterSpawns() {
        throw Pokkit.unsupported();

    }

    @Override
    public UnsafeValues getUnsafe() {
        throw Pokkit.unsupported();

    }

    @Override
    public String getUpdateFolder() {
        return "update";
    }

    @Override
    public File getUpdateFolderFile() {
        return new File(this.pluginFolder, this.getUpdateFolder());
    }

    @Override
    public String getVersion() {
        return nukkit.getVersion();
    }

    @Override
    public int getViewDistance() {
        return nukkit.getViewDistance();
    }

    @Override
    public WarningState getWarningState() {
        return WarningState.DEFAULT;
    }

    @Override
    public int getWaterAnimalSpawnLimit() {
        throw Pokkit.unsupported();

    }

    @Override
    public Set<OfflinePlayer> getWhitelistedPlayers() {
        throw Pokkit.unsupported();

    }

    @Override
    public World getWorld(String worldName) {
        return PokkitWorld.toBukkit(nukkit.getLevelByName(worldName));
    }

    @Override
    public World getWorld(UUID uuid) {
        return PokkitWorld.toBukkit(nukkit.getLevel(UniqueIdConversion.levelIdToIndex(uuid)));
    }

    @Override
    public File getWorldContainer() {
        throw Pokkit.unsupported();

    }

    @Override
    public List<World> getWorlds() {
        return nukkit.getLevels()
                .values()
                .stream()
                .map(PokkitWorld::toBukkit)
                .collect(Collectors.toList());
    }

    @Override
    public String getWorldType() {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean hasWhitelist() {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean isHardcore() {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean isPrimaryThread() {
        String threadName = Thread.currentThread().getName();
        if (threadName.contains("Async") || threadName.contains("RakNet")) {
            return false;
        }
        return true;
    }

    /**
     * Loads the Bukkit plugins. Must be called only once.
     * 
     * @return The plugins that were loaded.
     */
    public Plugin[] loadPlugins() {
        return this.pluginManager.loadPlugins(pluginFolder);
    }

    @Override
    public CachedServerIcon loadServerIcon(BufferedImage arg0) throws IllegalArgumentException, Exception {
        throw Pokkit.unsupported();

    }

    @Override
    public CachedServerIcon loadServerIcon(File arg0) throws IllegalArgumentException, Exception {
        throw Pokkit.unsupported();

    }

    @Override
    public List<Player> matchPlayer(String partialName) {
        return Arrays.stream(nukkit.matchPlayer(partialName))
                .map(PokkitPlayer::toBukkit)
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Recipe> recipeIterator() {
        throw Pokkit.unsupported();

    }

    @Override
    public void reload() {
        nukkit.reload();
    }

    @Override
    public void reloadWhitelist() {
        nukkit.reloadWhitelist();
    }

    @Override
    public void resetRecipes() {
        throw Pokkit.unsupported();

    }

    @Override
    public void savePlayers() {
        throw Pokkit.unsupported();

    }

    @Override
    public void sendPluginMessage(Plugin arg0, String arg1, byte[] arg2) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setDefaultGameMode(GameMode arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setIdleTimeout(int arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setSpawnRadius(int arg0) {
        throw Pokkit.unsupported();
    }

    @Override
    public void setWhitelist(boolean arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void shutdown() {
        nukkit.shutdown();
    }

    @Override
    public Spigot spigot() {
        throw Pokkit.unsupported();

    }

    @Override
    public void unbanIP(String arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean unloadWorld(String arg0, boolean arg1) {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean unloadWorld(World arg0, boolean arg1) {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean useExactLoginLocation() {
        throw Pokkit.unsupported();

    }

}
