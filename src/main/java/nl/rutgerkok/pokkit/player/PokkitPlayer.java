package nl.rutgerkok.pokkit.player;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import nl.rutgerkok.pokkit.Pokkit;
import nl.rutgerkok.pokkit.PokkitGameMode;
import nl.rutgerkok.pokkit.PokkitLocation;
import nl.rutgerkok.pokkit.PokkitServer;
import nl.rutgerkok.pokkit.UniqueIdConversion;
import nl.rutgerkok.pokkit.metadata.PlayerMetadataStore;
import nl.rutgerkok.pokkit.permission.PokkitPermission;
import nl.rutgerkok.pokkit.permission.PokkitPermissionAttachment;
import nl.rutgerkok.pokkit.permission.PokkitPermissionAttachmentInfo;
import nl.rutgerkok.pokkit.plugin.PokkitPlugin;
import nl.rutgerkok.pokkit.world.PokkitWorld;

import org.apache.commons.lang.Validate;
import org.bukkit.Achievement;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.GameMode;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.Particle;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Villager;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.InventoryView.Property;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MainHand;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.map.MapView;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.Vector;

import net.md_5.bungee.api.chat.BaseComponent;

@DelegateDeserialization(PokkitOfflinePlayer.class)
public class PokkitPlayer extends Player.Spigot implements Player {

    public static Player toBukkit(cn.nukkit.Player nukkit) {
        if (nukkit == null) {
            return null;
        }
        return ((PokkitServer) Bukkit.getServer()).getOnlinePlayerData().getPlayer(nukkit);
    }

    public static cn.nukkit.Player toNukkit(Player player) {
        if (player == null) {
            return null;
        }
        return ((PokkitPlayer) player).nukkit;
    }

    private final cn.nukkit.Player nukkit;
    private Scoreboard scoreboard;

    PokkitPlayer(cn.nukkit.Player player) {
        this.nukkit = Objects.requireNonNull(player);
        this.scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
    }

    @Override
    public void _INVALID_damage(int arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void _INVALID_damage(int arg0, Entity arg1) {
        throw Pokkit.unsupported();

    }

    @Override
    public int _INVALID_getHealth() {
        throw Pokkit.unsupported();

    }

    @Override
    public int _INVALID_getLastDamage() {
        throw Pokkit.unsupported();

    }

    @Override
    public int _INVALID_getMaxHealth() {
        throw Pokkit.unsupported();

    }

    @Override
    public void _INVALID_setHealth(int arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void _INVALID_setLastDamage(int arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void _INVALID_setMaxHealth(int arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void abandonConversation(Conversation arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void abandonConversation(Conversation arg0, ConversationAbandonedEvent arg1) {
        throw Pokkit.unsupported();

    }

    @Override
    public void acceptConversationInput(String arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin) {
        return PokkitPermissionAttachment.toBukkit(
                nukkit.addAttachment(PokkitPlugin.toNukkit(plugin)), this);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, int ticks) {
        cn.nukkit.plugin.Plugin nukkitPlugin = PokkitPlugin.toNukkit(plugin);
        cn.nukkit.permission.PermissionAttachment nukkitAttachment = nukkit.addAttachment(nukkitPlugin);
        nukkit.getServer().getScheduler().scheduleDelayedTask(() -> {
            nukkit.removeAttachment(nukkitAttachment);
        }, ticks);

        return PokkitPermissionAttachment.toBukkit(nukkitAttachment, this);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value) {
        return PokkitPermissionAttachment.toBukkit(
                nukkit.addAttachment(PokkitPlugin.toNukkit(plugin), name, value), this);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, int ticks) {
        return PokkitPermissionAttachment.toBukkit(
                nukkit.addAttachment(PokkitPlugin.toNukkit(plugin), name, value), this);
    }

    @Override
    public boolean addPotionEffect(PotionEffect arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean addPotionEffect(PotionEffect arg0, boolean arg1) {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean addPotionEffects(Collection<PotionEffect> arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void awardAchievement(Achievement arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean beginConversation(Conversation arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean canSee(Player arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void chat(String arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void closeInventory() {
        throw Pokkit.unsupported();

    }

    @Override
    public void damage(double arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void damage(double arg0, Entity arg1) {
        throw Pokkit.unsupported();

    }

    @Override
    public void decrementStatistic(Statistic arg0) throws IllegalArgumentException {
        throw Pokkit.unsupported();

    }

    @Override
    public void decrementStatistic(Statistic arg0, EntityType arg1) throws IllegalArgumentException {
        throw Pokkit.unsupported();

    }

    @Override
    public void decrementStatistic(Statistic arg0, EntityType arg1, int arg2) {
        throw Pokkit.unsupported();

    }

    @Override
    public void decrementStatistic(Statistic arg0, int arg1) throws IllegalArgumentException {
        throw Pokkit.unsupported();

    }

    @Override
    public void decrementStatistic(Statistic arg0, Material arg1) throws IllegalArgumentException {
        throw Pokkit.unsupported();

    }

    @Override
    public void decrementStatistic(Statistic arg0, Material arg1, int arg2) throws IllegalArgumentException {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean eject() {
        throw Pokkit.unsupported();

    }

    @Override
    public Collection<PotionEffect> getActivePotionEffects() {
        throw Pokkit.unsupported();

    }

    @Override
    public InetSocketAddress getAddress() {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean getAllowFlight() {
        throw Pokkit.unsupported();

    }

    @Override
    public AttributeInstance getAttribute(Attribute arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public Location getBedSpawnLocation() {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean getCanPickupItems() {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean getCollidesWithEntities() {
        throw Pokkit.unsupported();
    }

    @Override
    public Location getCompassTarget() {
        throw Pokkit.unsupported();

    }

    @Override
    public String getCustomName() {
        throw Pokkit.unsupported();

    }

    @Override
    public String getDisplayName() {
        return nukkit.getDisplayName();
    }

    @Override
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return nukkit.getEffectivePermissions()
                .values()
                .stream()
                .map(PokkitPermissionAttachmentInfo::toBukkit)
                .collect(Collectors.toSet());
    }

    @Override
    public Inventory getEnderChest() {
        throw Pokkit.unsupported();

    }

    @Override
    public int getEntityId() {
        throw Pokkit.unsupported();

    }

    @Override
    public EntityEquipment getEquipment() {
        throw Pokkit.unsupported();

    }

    @Override
    public float getExhaustion() {
        throw Pokkit.unsupported();

    }

    @Override
    public float getExp() {
        throw Pokkit.unsupported();

    }

    @Override
    public int getExpToLevel() {
        throw Pokkit.unsupported();

    }

    @Override
    public double getEyeHeight() {
        throw Pokkit.unsupported();

    }

    @Override
    public double getEyeHeight(boolean arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public Location getEyeLocation() {
        throw Pokkit.unsupported();

    }

    @Override
    public float getFallDistance() {
        throw Pokkit.unsupported();

    }

    @Override
    public int getFireTicks() {
        throw Pokkit.unsupported();

    }

    @Override
    public long getFirstPlayed() {
        Long firstPlayed = nukkit.getFirstPlayed();
        if (firstPlayed == null) {
            return 0;
        }
        return firstPlayed;
    }

    @Override
    public float getFlySpeed() {
        throw Pokkit.unsupported();

    }

    @Override
    public int getFoodLevel() {
        throw Pokkit.unsupported();

    }

    @Override
    public GameMode getGameMode() {
        return PokkitGameMode.toBukkit(nukkit.getGamemode());
    }

    @Override
    public double getHealth() {
        return nukkit.getHealth();
    }

    @Override
    public double getHealthScale() {
        throw Pokkit.unsupported();

    }

    @Override
    public Set<Player> getHiddenPlayers() {
        throw Pokkit.unsupported();
    }

    @Override
    public PlayerInventory getInventory() {
        throw Pokkit.unsupported();

    }

    @Override
    public ItemStack getItemInHand() {
        throw Pokkit.unsupported();

    }

    @Override
    public ItemStack getItemOnCursor() {
        throw Pokkit.unsupported();

    }

    @Override
    public Player getKiller() {
        throw Pokkit.unsupported();

    }

    @Override
    public double getLastDamage() {
        throw Pokkit.unsupported();

    }

    @Override
    public EntityDamageEvent getLastDamageCause() {
        throw Pokkit.unsupported();

    }

    @Override
    public long getLastPlayed() {
        Long lastPlayed = nukkit.getLastPlayed();
        if (lastPlayed == null) {
            return 0;
        }
        return lastPlayed;
    }

    @Override
    public List<Block> getLastTwoTargetBlocks(HashSet<Byte> arg0, int arg1) {
        throw Pokkit.unsupported();

    }

    @Override
    public List<Block> getLastTwoTargetBlocks(Set<Material> arg0, int arg1) {
        throw Pokkit.unsupported();

    }

    @Override
    public Entity getLeashHolder() throws IllegalStateException {
        throw Pokkit.unsupported();

    }

    @Override
    public int getLevel() {
        throw Pokkit.unsupported();

    }

    @Override
    public List<Block> getLineOfSight(HashSet<Byte> arg0, int arg1) {
        throw Pokkit.unsupported();

    }

    @Override
    public List<Block> getLineOfSight(Set<Material> arg0, int arg1) {
        throw Pokkit.unsupported();

    }

    @Override
    public Set<String> getListeningPluginChannels() {
        throw Pokkit.unsupported();

    }

    @Override
    public String getLocale() {
        throw Pokkit.unsupported();
    }

    @Override
    public Location getLocation() {
        return PokkitLocation.toBukkit(nukkit.getLocation());
    }

    @Override
    public Location getLocation(Location toOverwrite) {
        return PokkitLocation.toBukkit(nukkit.getLocation(), toOverwrite);
    }

    @Override
    public MainHand getMainHand() {
        return MainHand.LEFT;
    }

    @Override
    public int getMaxFireTicks() {
        throw Pokkit.unsupported();

    }

    @Override
    public double getMaxHealth() {
        return nukkit.getMaxHealth();
    }

    @Override
    public int getMaximumAir() {
        throw Pokkit.unsupported();

    }

    @Override
    public int getMaximumNoDamageTicks() {
        throw Pokkit.unsupported();

    }

    @Override
    public List<MetadataValue> getMetadata(String metadataKey) {
        return getMetadataStore().getMetadata(this, metadataKey);
    }

    private PlayerMetadataStore getMetadataStore() {
        return ((PokkitServer) Bukkit.getServer()).getMetadata().getPlayerMetadata();
    }

    @Override
    public String getName() {
        return nukkit.getName();
    }

    @Override
    public List<Entity> getNearbyEntities(double arg0, double arg1, double arg2) {
        throw Pokkit.unsupported();

    }

    @Override
    public int getNoDamageTicks() {
        throw Pokkit.unsupported();

    }

    @Override
    public InventoryView getOpenInventory() {
        throw Pokkit.unsupported();

    }

    @Override
    public Entity getPassenger() {
        throw Pokkit.unsupported();

    }

    @Override
    public Player getPlayer() {
        return this;
    }

    @Override
    public String getPlayerListName() {
        throw Pokkit.unsupported();

    }

    @Override
    public long getPlayerTime() {
        throw Pokkit.unsupported();

    }

    @Override
    public long getPlayerTimeOffset() {
        throw Pokkit.unsupported();

    }

    @Override
    public WeatherType getPlayerWeather() {
        throw Pokkit.unsupported();

    }

    @Override
    public InetSocketAddress getRawAddress() {
        return InetSocketAddress.createUnresolved(nukkit.getAddress(), nukkit.getPort());
    }

    @Override
    public int getRemainingAir() {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean getRemoveWhenFarAway() {
        throw Pokkit.unsupported();

    }

    @Override
    public float getSaturation() {
        throw Pokkit.unsupported();

    }

    @Override
    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    @Override
    public Server getServer() {
        return Bukkit.getServer();
    }

    @Override
    public int getSleepTicks() {
        throw Pokkit.unsupported();

    }

    @Override
    public Entity getSpectatorTarget() {
        throw Pokkit.unsupported();

    }

    @Override
    public int getStatistic(Statistic arg0) throws IllegalArgumentException {
        throw Pokkit.unsupported();

    }

    @Override
    public int getStatistic(Statistic arg0, EntityType arg1) throws IllegalArgumentException {
        throw Pokkit.unsupported();

    }

    @Override
    public int getStatistic(Statistic arg0, Material arg1) throws IllegalArgumentException {
        throw Pokkit.unsupported();

    }

    @Override
    public Block getTargetBlock(HashSet<Byte> arg0, int arg1) {
        throw Pokkit.unsupported();

    }

    @Override
    public Block getTargetBlock(Set<Material> arg0, int arg1) {
        throw Pokkit.unsupported();

    }

    @Override
    public int getTicksLived() {
        throw Pokkit.unsupported();

    }

    @Override
    public int getTotalExperience() {
        throw Pokkit.unsupported();

    }

    @Override
    public EntityType getType() {
        return EntityType.PLAYER;
    }

    @Override
    public UUID getUniqueId() {
        return UniqueIdConversion.playerNameToId(getName());
    }

    @Override
    public Entity getVehicle() {
        throw Pokkit.unsupported();

    }

    @Override
    public Vector getVelocity() {
        throw Pokkit.unsupported();

    }

    @Override
    public float getWalkSpeed() {
        throw Pokkit.unsupported();

    }

    @Override
    public World getWorld() {
        return PokkitWorld.toBukkit(nukkit.getLevel());
    }

    @Override
    public void giveExp(int arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void giveExpLevels(int arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean hasAchievement(Achievement arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean hasAI() {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean hasGravity() {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean hasLineOfSight(Entity arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean hasMetadata(String metadataKey) {
        return getMetadataStore().hasMetadata(this, metadataKey);
    }

    @Override
    public boolean hasPermission(Permission permission) {
        return nukkit.hasPermission(PokkitPermission.toNukkit(permission));
    }

    @Override
    public boolean hasPermission(String permission) {
        return nukkit.hasPermission(permission);
    }

    @Override
    public boolean hasPlayedBefore() {
        return nukkit.hasPlayedBefore();
    }

    @Override
    public boolean hasPotionEffect(PotionEffectType arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void hidePlayer(Player arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void incrementStatistic(Statistic arg0) throws IllegalArgumentException {
        throw Pokkit.unsupported();

    }

    @Override
    public void incrementStatistic(Statistic arg0, EntityType arg1) throws IllegalArgumentException {
        throw Pokkit.unsupported();

    }

    @Override
    public void incrementStatistic(Statistic arg0, EntityType arg1, int arg2) throws IllegalArgumentException {
        throw Pokkit.unsupported();

    }

    @Override
    public void incrementStatistic(Statistic arg0, int arg1) throws IllegalArgumentException {
        throw Pokkit.unsupported();

    }

    @Override
    public void incrementStatistic(Statistic arg0, Material arg1) throws IllegalArgumentException {
        throw Pokkit.unsupported();

    }

    @Override
    public void incrementStatistic(Statistic arg0, Material arg1, int arg2) throws IllegalArgumentException {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean isBanned() {
        return nukkit.isBanned();
    }

    @Override
    public boolean isBlocking() {
        return false;
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    public boolean isConversing() {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean isCustomNameVisible() {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean isDead() {
        return nukkit.getHealth() <= 0;
    }

    @Override
    public boolean isEmpty() {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean isFlying() {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean isGliding() {
        return false;
    }

    @Override
    public boolean isGlowing() {
        return false;
    }

    @Override
    public boolean isHealthScaled() {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean isInsideVehicle() {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean isInvulnerable() {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean isLeashed() {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean isOnGround() {
        return nukkit.isOnGround();
    }

    @Override
    public boolean isOnline() {
        return nukkit.isOnline();
    }

    @Override
    public boolean isOp() {
        return nukkit.isOp();
    }

    @Override
    public boolean isPermissionSet(Permission permission) {
        return nukkit.isPermissionSet(PokkitPermission.toNukkit(permission));
    }

    @Override
    public boolean isPermissionSet(String permission) {
        return nukkit.isPermissionSet(permission);
    }

    @Override
    public boolean isPlayerTimeRelative() {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean isSilent() {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean isSleeping() {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean isSleepingIgnored() {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean isSneaking() {
        return nukkit.isSneaking();
    }

    @Override
    public boolean isSprinting() {
        return nukkit.isSprinting();
    }

    @Override
    public boolean isValid() {
        return isOnline() && !isDead();
    }

    @Override
    public boolean isWhitelisted() {
        return nukkit.isWhitelisted();
    }

    @Override
    public void kickPlayer(String reason) {
        nukkit.kick(reason);
    }

    @Override
    public <T extends Projectile> T launchProjectile(Class<? extends T> arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public <T extends Projectile> T launchProjectile(Class<? extends T> arg0, Vector arg1) {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean leaveVehicle() {
        throw Pokkit.unsupported();

    }

    @Override
    public void loadData() {
        throw Pokkit.unsupported();

    }

    @Override
    public InventoryView openEnchanting(Location arg0, boolean arg1) {
        throw Pokkit.unsupported();

    }

    @Override
    public InventoryView openInventory(Inventory arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void openInventory(InventoryView arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public InventoryView openMerchant(Villager arg0, boolean arg1) {
        throw Pokkit.unsupported();

    }

    @Override
    public InventoryView openWorkbench(Location arg0, boolean arg1) {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean performCommand(String arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void playEffect(EntityEffect arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void playEffect(Location arg0, Effect arg1, int arg2) {
        throw Pokkit.unsupported();

    }

    @Override
    public void playEffect(Location location, Effect effect, int id, int data, float offsetX, float offsetY,
            float offsetZ, float speed, int particleCount, int radius) {
        throw Pokkit.unsupported();
    }

    @Override
    public <T> void playEffect(Location arg0, Effect arg1, T arg2) {
        throw Pokkit.unsupported();

    }

    @Override
    public void playNote(Location arg0, byte arg1, byte arg2) {
        throw Pokkit.unsupported();

    }

    @Override
    public void playNote(Location arg0, Instrument arg1, Note arg2) {
        throw Pokkit.unsupported();

    }

    @Override
    public void playSound(Location arg0, Sound arg1, float arg2, float arg3) {
        throw Pokkit.unsupported();

    }

    @Override
    public void playSound(Location arg0, String arg1, float arg2, float arg3) {
        throw Pokkit.unsupported();

    }

    @Override
    public void recalculatePermissions() {
        nukkit.recalculatePermissions();
    }

    @Override
    public void remove() {
        throw Pokkit.unsupported();

    }

    @Override
    public void removeAchievement(Achievement arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void removeAttachment(PermissionAttachment attachment) {
        nukkit.removeAttachment(PokkitPermissionAttachment.toNukkit(attachment));
    }


    @Override
    public void removeMetadata(String metadataKey, Plugin owningPlugin) {
        getMetadataStore().removeMetadata(this, metadataKey, owningPlugin);
    }

    @Override
    public void removePotionEffect(PotionEffectType arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void resetMaxHealth() {
        throw Pokkit.unsupported();

    }

    @Override
    public void resetPlayerTime() {
        throw Pokkit.unsupported();

    }

    @Override
    public void resetPlayerWeather() {
        throw Pokkit.unsupported();

    }

    @Override
    public void resetTitle() {
        throw Pokkit.unsupported();

    }

    @Override
    public void respawn() {
        throw Pokkit.unsupported();
    }

    @Override
    public void saveData() {
        throw Pokkit.unsupported();

    }

    @Override
    public void sendBlockChange(Location arg0, int arg1, byte arg2) {
        throw Pokkit.unsupported();

    }

    @Override
    public void sendBlockChange(Location arg0, Material arg1, byte arg2) {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean sendChunkChange(Location arg0, int arg1, int arg2, int arg3, byte[] arg4) {
        throw Pokkit.unsupported();

    }

    @Override
    public void sendMap(MapView arg0) {
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
    public void sendPluginMessage(Plugin arg0, String arg1, byte[] arg2) {
        throw Pokkit.unsupported();

    }

    @Override
    public void sendRawMessage(String arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void sendSignChange(Location arg0, String[] arg1) throws IllegalArgumentException {
        throw Pokkit.unsupported();

    }

    @Override
    public void sendTitle(String arg0, String arg1) {
        throw Pokkit.unsupported();

    }

    @Override
    public Map<String, Object> serialize() {
        throw Pokkit.unsupported();

    }

    @Override
    public void setAI(boolean arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setAllowFlight(boolean value) {
        nukkit.setAllowFlight(value);
    }

    @Override
    public void setBanned(boolean value) {
        nukkit.setBanned(value);
    }

    @Override
    public void setBedSpawnLocation(Location arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setBedSpawnLocation(Location arg0, boolean arg1) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setCanPickupItems(boolean arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setCollidable(boolean arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setCollidesWithEntities(boolean collides) {
        throw Pokkit.unsupported();
    }

    @Override
    public void setCompassTarget(Location arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setCustomName(String arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setCustomNameVisible(boolean arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setDisplayName(String displayName) {
        nukkit.setDisplayName(displayName);
    }

    @Override
    public void setExhaustion(float arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setExp(float arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setFallDistance(float arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setFireTicks(int arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setFlying(boolean arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setFlySpeed(float arg0) throws IllegalArgumentException {
        throw Pokkit.unsupported();

    }

    @Override
    public void setFoodLevel(int arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setGameMode(GameMode gamemode) {
        nukkit.setGamemode(PokkitGameMode.toNukkit(gamemode));
    }

    @Override
    public void setGliding(boolean arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setGlowing(boolean arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setGravity(boolean arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setHealth(double arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setHealthScale(double arg0) throws IllegalArgumentException {
        throw Pokkit.unsupported();

    }

    @Override
    public void setHealthScaled(boolean arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setInvulnerable(boolean arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setItemInHand(ItemStack arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setItemOnCursor(ItemStack arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setLastDamage(double arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setLastDamageCause(EntityDamageEvent arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean setLeashHolder(Entity arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setLevel(int arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setMaxHealth(double maxHealth) {
        nukkit.setMaxHealth((int) Math.ceil(maxHealth));
    }

    @Override
    public void setMaximumAir(int arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setMaximumNoDamageTicks(int arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setMetadata(String metadataKey, MetadataValue newMetadataValue) {
        getMetadataStore().setMetadata(this, metadataKey, newMetadataValue);
    }

    @Override
    public void setNoDamageTicks(int arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setOp(boolean value) {
        nukkit.setOp(value);
    }

    @Override
    public boolean setPassenger(Entity arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setPlayerListName(String arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setPlayerTime(long arg0, boolean arg1) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setPlayerWeather(WeatherType arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setRemainingAir(int arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setRemoveWhenFarAway(boolean arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setResourcePack(String arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setSaturation(float arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setScoreboard(Scoreboard scoreboard) throws IllegalArgumentException, IllegalStateException {
        Validate.notNull(scoreboard);
        this.scoreboard = scoreboard;
    }

    @Override
    public void setSilent(boolean arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setSleepingIgnored(boolean arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setSneaking(boolean value) {
        nukkit.setSneaking(value);
    }

    @Override
    public void setSpectatorTarget(Entity arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setSprinting(boolean value) {
        nukkit.setSprinting(value);
    }

    @Override
    public void setStatistic(Statistic arg0, EntityType arg1, int arg2) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setStatistic(Statistic arg0, int arg1) throws IllegalArgumentException {
        throw Pokkit.unsupported();

    }

    @Override
    public void setStatistic(Statistic arg0, Material arg1, int arg2) throws IllegalArgumentException {
        throw Pokkit.unsupported();

    }

    @Override
    public void setTexturePack(String arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setTicksLived(int arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setTotalExperience(int arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setVelocity(Vector arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void setWalkSpeed(float arg0) throws IllegalArgumentException {
        throw Pokkit.unsupported();

    }

    @Override
    public void setWhitelisted(boolean arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean setWindowProperty(Property arg0, int arg1) {
        throw Pokkit.unsupported();

    }

    @Override
    public void showPlayer(Player arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void spawnParticle(Particle arg0, double arg1, double arg2, double arg3, int arg4) {
        throw Pokkit.unsupported();

    }

    @Override
    public void spawnParticle(Particle arg0, double arg1, double arg2, double arg3, int arg4, double arg5, double arg6,
            double arg7) {
        throw Pokkit.unsupported();

    }

    @Override
    public void spawnParticle(Particle arg0, double arg1, double arg2, double arg3, int arg4, double arg5, double arg6,
            double arg7, double arg8) {
        throw Pokkit.unsupported();

    }

    @Override
    public <T> void spawnParticle(Particle arg0, double arg1, double arg2, double arg3, int arg4, double arg5,
            double arg6, double arg7, double arg8, T arg9) {
        throw Pokkit.unsupported();

    }

    @Override
    public <T> void spawnParticle(Particle arg0, double arg1, double arg2, double arg3, int arg4, double arg5,
            double arg6, double arg7, T arg8) {
        throw Pokkit.unsupported();

    }

    @Override
    public <T> void spawnParticle(Particle arg0, double arg1, double arg2, double arg3, int arg4, T arg5) {
        throw Pokkit.unsupported();

    }

    @Override
    public void spawnParticle(Particle arg0, Location arg1, int arg2) {
        throw Pokkit.unsupported();

    }

    @Override
    public void spawnParticle(Particle arg0, Location arg1, int arg2, double arg3, double arg4, double arg5) {
        throw Pokkit.unsupported();

    }

    @Override
    public void spawnParticle(Particle arg0, Location arg1, int arg2, double arg3, double arg4, double arg5,
            double arg6) {
        throw Pokkit.unsupported();

    }

    @Override
    public <T> void spawnParticle(Particle arg0, Location arg1, int arg2, double arg3, double arg4, double arg5,
            double arg6, T arg7) {
        throw Pokkit.unsupported();

    }

    @Override
    public <T> void spawnParticle(Particle arg0, Location arg1, int arg2, double arg3, double arg4, double arg5,
            T arg6) {
        throw Pokkit.unsupported();

    }

    @Override
    public <T> void spawnParticle(Particle arg0, Location arg1, int arg2, T arg3) {
        throw Pokkit.unsupported();

    }

    @Override
    public Spigot spigot() {
        return this;
    }

    @Override
    public void stopSound(Sound arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public void stopSound(String arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean teleport(Entity arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean teleport(Entity entity, TeleportCause cause) {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean teleport(Location arg0) {
        throw Pokkit.unsupported();

    }

    @Override
    public boolean teleport(Location arg0, TeleportCause arg1) {
        throw Pokkit.unsupported();

    }

    @Override
    public void updateInventory() {
        throw Pokkit.unsupported();

    }

}
