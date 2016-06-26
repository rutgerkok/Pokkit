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

import nl.rutgerkok.pokkit.plugin.PokkitPluginManager;
import nl.rutgerkok.pokkit.scheduler.PokkitScheduler;

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
import org.bukkit.command.SimpleCommandMap;
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
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.util.CachedServerIcon;

import com.avaje.ebean.config.ServerConfig;

@SuppressWarnings("deprecation")
public final class PokkitServer implements Server {

    public static cn.nukkit.Server toNukkit(Server server) {
        return ((PokkitServer)server).nukkitServer;
    }
    private final cn.nukkit.Server nukkitServer;
    private final PokkitScheduler scheduler;
    private final SimplePluginManager pluginManager;
    private final File pluginFolder;
    private final SimpleCommandMap commandMap;
    private Logger logger;

    public PokkitServer(cn.nukkit.Server nukkitServer, Logger logger, File pluginFolder) {
        this.nukkitServer = Objects.requireNonNull(nukkitServer, "nukkitServer");
        this.pluginFolder = Objects.requireNonNull(pluginFolder, "pluginFolder");
        this.logger = Objects.requireNonNull(logger, "logger");

        this.scheduler = new PokkitScheduler(nukkitServer.getScheduler());
        this.commandMap = new SimpleCommandMap(this);
        this.pluginManager = PokkitPluginManager.create(this, commandMap, pluginFolder);
    }

    @Override
    public Player[] _INVALID_getOnlinePlayers() {
        return nukkitServer.getOnlinePlayers()
                .values()
                .stream()
                .map(this::toBukkitPlayer)
                .toArray(Player[]::new);
    }

    @Override
    public boolean addRecipe(Recipe arg0) {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public void banIP(String arg0) {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public int broadcast(String arg0, String arg1) {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public int broadcastMessage(String arg0) {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public void clearRecipes() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public void configureDbConfig(ServerConfig arg0) {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public BossBar createBossBar(String arg0, BarColor arg1, BarStyle arg2, BarFlag... arg3) {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public ChunkData createChunkData(World arg0) {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public Inventory createInventory(InventoryHolder arg0, int arg1) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public Inventory createInventory(InventoryHolder arg0, int arg1, String arg2) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public Inventory createInventory(InventoryHolder arg0, InventoryType arg1) {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public Inventory createInventory(InventoryHolder arg0, InventoryType arg1, String arg2) {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public MapView createMap(World arg0) {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public World createWorld(WorldCreator arg0) {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public boolean dispatchCommand(CommandSender arg0, String arg1) throws CommandException {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public boolean getAllowEnd() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public boolean getAllowFlight() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public boolean getAllowNether() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public int getAmbientSpawnLimit() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public int getAnimalSpawnLimit() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public BanList getBanList(Type arg0) {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public Set<OfflinePlayer> getBannedPlayers() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public String getBukkitVersion() {
        return Pokkit.VERSION;
    }

    @Override
    public Map<String, String[]> getCommandAliases() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public long getConnectionThrottle() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public ConsoleCommandSender getConsoleSender() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public GameMode getDefaultGameMode() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public boolean getGenerateStructures() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public HelpMap getHelpMap() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public int getIdleTimeout() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public String getIp() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public Set<String> getIPBans() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public ItemFactory getItemFactory() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

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
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public int getMaxPlayers() {
        return nukkitServer.getMaxPlayers();
    }

    @Override
    public Messenger getMessenger() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public int getMonsterSpawnLimit() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public String getMotd() {
        return nukkitServer.getMotd();
    }

    @Override
    public String getName() {
        return nukkitServer.getName();
    }

    @Override
    public OfflinePlayer getOfflinePlayer(String arg0) {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public OfflinePlayer getOfflinePlayer(UUID arg0) {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public OfflinePlayer[] getOfflinePlayers() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public boolean getOnlineMode() {
        // Pretend we are running in online mode - this should stop plugins from
        // complaining
        return true;
    }

    @Override
    public Collection<? extends Player> getOnlinePlayers() {
        return nukkitServer.getOnlinePlayers()
                .values()
                .stream()
                .map(this::toBukkitPlayer)
                .collect(Collectors.toList());
    }

    @Override
    public Set<OfflinePlayer> getOperators() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public Player getPlayer(String name) {
        return toBukkitPlayer(nukkitServer.getPlayer(name));
    }

    @Override
    public Player getPlayer(UUID uuid) {
        return toBukkitPlayer(nukkitServer.getOnlinePlayers().get(uuid));
    }

    @Override
    public Player getPlayerExact(String name) {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public PluginCommand getPluginCommand(String arg0) {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public PluginManager getPluginManager() {
        return pluginManager;
    }

    @Override
    public int getPort() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public List<Recipe> getRecipesFor(ItemStack arg0) {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public BukkitScheduler getScheduler() {
        return scheduler;
    }

    @Override
    public ScoreboardManager getScoreboardManager() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public CachedServerIcon getServerIcon() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public String getServerId() {
        return nukkitServer.getServerUniqueId().toString();
    }

    @Override
    public String getServerName() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public ServicesManager getServicesManager() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public String getShutdownMessage() {
        return "Server closed";
    }

    @Override
    public int getSpawnRadius() {
        return nukkitServer.getSpawnRadius();
    }

    @Override
    public int getTicksPerAnimalSpawns() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public int getTicksPerMonsterSpawns() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public UnsafeValues getUnsafe() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

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
        return nukkitServer.getVersion();
    }

    @Override
    public int getViewDistance() {
        return nukkitServer.getViewDistance();
    }

    @Override
    public WarningState getWarningState() {
        return WarningState.DEFAULT;
    }

    @Override
    public int getWaterAnimalSpawnLimit() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public Set<OfflinePlayer> getWhitelistedPlayers() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public World getWorld(String arg0) {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public World getWorld(UUID arg0) {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public File getWorldContainer() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public List<World> getWorlds() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public String getWorldType() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public boolean hasWhitelist() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public boolean isHardcore() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public boolean isPrimaryThread() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    /**
     * Loads the Bukkit plugins. Must be called only once.
     */
    public void loadPlugins() {
        PokkitPluginManager.loadPlugins(pluginManager, pluginFolder);
    }

    @Override
    public CachedServerIcon loadServerIcon(BufferedImage arg0) throws IllegalArgumentException, Exception {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public CachedServerIcon loadServerIcon(File arg0) throws IllegalArgumentException, Exception {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public List<Player> matchPlayer(String partialName) {
        return Arrays.stream(nukkitServer.matchPlayer(partialName))
                .map(this::toBukkitPlayer)
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Recipe> recipeIterator() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public void reload() {
        nukkitServer.reload();
    }

    @Override
    public void reloadWhitelist() {
        nukkitServer.reloadWhitelist();
    }

    @Override
    public void resetRecipes() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public void savePlayers() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public void sendPluginMessage(Plugin arg0, String arg1, byte[] arg2) {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public void setDefaultGameMode(GameMode arg0) {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public void setIdleTimeout(int arg0) {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public void setSpawnRadius(int arg0) {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);
    }

    @Override
    public void setWhitelist(boolean arg0) {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public void shutdown() {
        nukkitServer.shutdown();
    }

    @Override
    public Spigot spigot() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    private Player toBukkitPlayer(cn.nukkit.Player player) {
        return player == null ? null : new PokkitPlayer(player);
    }

    @Override
    public void unbanIP(String arg0) {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public boolean unloadWorld(String arg0, boolean arg1) {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public boolean unloadWorld(World arg0, boolean arg1) {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

    @Override
    public boolean useExactLoginLocation() {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);

    }

}
