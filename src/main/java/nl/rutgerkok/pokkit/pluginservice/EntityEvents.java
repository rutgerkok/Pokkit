package nl.rutgerkok.pokkit.pluginservice;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.entity.Creeper;
import org.bukkit.event.entity.CreeperPowerEvent;
import org.bukkit.event.entity.CreeperPowerEvent.PowerCause;
import org.bukkit.event.entity.EntityCombustByBlockEvent;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.EntityTeleportEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import cn.nukkit.entity.EntityLiving;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.block.ItemFrameDropItemEvent;
import cn.nukkit.item.Item;
import nl.rutgerkok.pokkit.PokkitLocation;
import nl.rutgerkok.pokkit.entity.PokkitDamageCause;
import nl.rutgerkok.pokkit.entity.PokkitEntity;
import nl.rutgerkok.pokkit.entity.PokkitItemFrameEntity;
import nl.rutgerkok.pokkit.entity.PokkitLivingEntity;
import nl.rutgerkok.pokkit.world.PokkitBlock;
import nl.rutgerkok.pokkit.world.item.PokkitItemStack;

public final class EntityEvents extends EventTranslator {
    @EventHandler(ignoreCancelled = false)
    public void onItemDrop(ItemFrameDropItemEvent event) {
        // Bukkit uses EntityDamageByEntityEvent when someone hits an Item Frame
        // However Nukkit uses ItemFrameDropItemEvent, so we are going to trigger an
        // EntityDamageByEntityEvent
        
        // TODO: Do this the proper way... not the "let's use deprecated methods!" way
        EntityDamageByEntityEvent bukkitEvent = new EntityDamageByEntityEvent(PokkitEntity.toBukkit(event.getPlayer()), new PokkitItemFrameEntity(event.getItemFrame()), EntityDamageEvent.DamageCause.ENTITY_ATTACK, 999D);

        callCancellable(event, bukkitEvent);
    }
    
	@EventHandler(ignoreCancelled = false)
	public void onCreeperPower(cn.nukkit.event.entity.CreeperPowerEvent event) {
		if (canIgnore(PlayerInteractEvent.getHandlerList())) {
			return;
		}

		PowerCause cause = PowerCause.valueOf(event.getCause().name()); // Dirty and fast conversion
		
		CreeperPowerEvent bukkitEvent = new CreeperPowerEvent((Creeper) PokkitEntity.toBukkit(event.getEntity()), cause);

		callCancellable(event, bukkitEvent);
	}
	
	@EventHandler(ignoreCancelled = false)
	public void onBlockCombust(cn.nukkit.event.entity.EntityCombustByBlockEvent event) {
		if (canIgnore(PlayerInteractEvent.getHandlerList())) {
			return;
		}
		
		EntityCombustByBlockEvent bukkitEvent = new EntityCombustByBlockEvent(PokkitBlock.toBukkit(event.getCombuster()), PokkitEntity.toBukkit(event.getEntity()), event.getDuration());

		callCancellable(event, bukkitEvent);
	}
	
	@EventHandler(ignoreCancelled = false)
	public void onEntityCombust(cn.nukkit.event.entity.EntityCombustByEntityEvent event) {
		if (canIgnore(PlayerInteractEvent.getHandlerList())) {
			return;
		}
		
		EntityCombustByEntityEvent bukkitEvent = new EntityCombustByEntityEvent(PokkitEntity.toBukkit(event.getCombuster()), PokkitEntity.toBukkit(event.getEntity()), event.getDuration());

		callCancellable(event, bukkitEvent);
	}
	
	@EventHandler(ignoreCancelled = false)
	public void onCombust(cn.nukkit.event.entity.EntityCombustEvent event) {
		if (canIgnore(PlayerInteractEvent.getHandlerList())) {
			return;
		}
		
		EntityCombustEvent bukkitEvent = new EntityCombustEvent(PokkitEntity.toBukkit(event.getEntity()), event.getDuration());

		callCancellable(event, bukkitEvent);
	}
	
	@EventHandler(ignoreCancelled = false)
	public void onBlockDamage(cn.nukkit.event.entity.EntityDamageByBlockEvent event) {
		if (canIgnore(PlayerInteractEvent.getHandlerList())) {
			return;
		}

		// TODO: Do this the proper way... not the "let's use deprecated methods!" way
		EntityDamageByBlockEvent bukkitEvent = new EntityDamageByBlockEvent(PokkitBlock.toBukkit(event.getDamager()), PokkitEntity.toBukkit(event.getEntity()), PokkitDamageCause.toBukkit(event.getCause()), event.getDamage());

		callCancellable(event, bukkitEvent);
	}
	
	@EventHandler(ignoreCancelled = false)
	public void onEntityDamageByEntity(cn.nukkit.event.entity.EntityDamageByEntityEvent event) {
		if (canIgnore(PlayerInteractEvent.getHandlerList())) {
			return;
		}

		// TODO: Do this the proper way... not the "let's use deprecated methods!" way
		EntityDamageByEntityEvent bukkitEvent = new EntityDamageByEntityEvent(PokkitEntity.toBukkit(event.getDamager()), PokkitEntity.toBukkit(event.getEntity()), PokkitDamageCause.toBukkit(event.getCause()), event.getDamage());

		callCancellable(event, bukkitEvent);
	}
	
	@EventHandler(ignoreCancelled = false)
	public void onEntityDamage(cn.nukkit.event.entity.EntityDamageEvent event) {
		if (canIgnore(PlayerInteractEvent.getHandlerList())) {
			return;
		}

		// TODO: Do this the proper way... not the "let's use deprecated methods!" way
		EntityDamageEvent bukkitEvent = new EntityDamageEvent(PokkitEntity.toBukkit(event.getEntity()), PokkitDamageCause.toBukkit(event.getCause()), event.getCause());

		callCancellable(event, bukkitEvent);
	}
	
	@EventHandler(ignoreCancelled = false)
	public void onEntityDeath(cn.nukkit.event.entity.EntityDeathEvent event) {
		if (canIgnore(PlayerInteractEvent.getHandlerList())) {
			return;
		}

		List<ItemStack> drops = new ArrayList<ItemStack>();
		
		for (Item item : event.getDrops()) {
			drops.add(PokkitItemStack.toBukkitCopy(item));
		}

		EntityDeathEvent bukkitEvent = new EntityDeathEvent(PokkitLivingEntity.toBukkit((EntityLiving) event.getEntity()), drops);

		callUncancellable(bukkitEvent);
	}
	
	@EventHandler(ignoreCancelled = false)
	public void onEntityExplode(cn.nukkit.event.entity.EntityExplodeEvent event) {
		if (canIgnore(PlayerInteractEvent.getHandlerList())) {
			return;
		}

		List<Block> blocks = new ArrayList<Block>();
		
		for (cn.nukkit.block.Block nukkitBlock : event.getBlockList()) {
			blocks.add(PokkitBlock.toBukkit(nukkitBlock));
		}
		
		EntityExplodeEvent bukkitEvent = new EntityExplodeEvent(PokkitEntity.toBukkit(event.getEntity()), PokkitLocation.toBukkit(event.getPosition()), blocks, (float) event.getYield());

		callCancellable(event, bukkitEvent);
	}
	
	@EventHandler(ignoreCancelled = false)
	public void onEntityRegainHealth(cn.nukkit.event.entity.EntityRegainHealthEvent event) {
		if (canIgnore(PlayerInteractEvent.getHandlerList())) {
			return;
		}
		
		int reason = event.getRegainReason();
		RegainReason bukkitReason = RegainReason.EATING;
		
		switch (reason) {
		case cn.nukkit.event.entity.EntityRegainHealthEvent.CAUSE_MAGIC:
			bukkitReason = RegainReason.MAGIC;
			break;
		case cn.nukkit.event.entity.EntityRegainHealthEvent.CAUSE_CUSTOM:
			bukkitReason = RegainReason.CUSTOM;
			break;
		case cn.nukkit.event.entity.EntityRegainHealthEvent.CAUSE_EATING:
			bukkitReason = RegainReason.EATING;
			break;
		case cn.nukkit.event.entity.EntityRegainHealthEvent.CAUSE_REGEN:
			bukkitReason = RegainReason.REGEN;
			break;
		}
		
		EntityRegainHealthEvent bukkitEvent = new EntityRegainHealthEvent(PokkitEntity.toBukkit(event.getEntity()), (double) event.getAmount(), bukkitReason);

		callCancellable(event, bukkitEvent);
	}
	
	@EventHandler(ignoreCancelled = false)
	public void onSpawn(cn.nukkit.event.entity.EntitySpawnEvent event) {
		if (canIgnore(PlayerInteractEvent.getHandlerList())) {
			return;
		}
		EntitySpawnEvent bukkitEvent = new EntitySpawnEvent(PokkitEntity.toBukkit(event.getEntity()));

		callUncancellable(bukkitEvent);
	}
	
	@EventHandler(ignoreCancelled = false)
	public void onTeleport(cn.nukkit.event.entity.EntityTeleportEvent event) {
		if (canIgnore(PlayerInteractEvent.getHandlerList())) {
			return;
		}
		
		EntityTeleportEvent bukkitEvent = new EntityTeleportEvent(PokkitEntity.toBukkit(event.getEntity()), PokkitLocation.toBukkit(event.getFrom()), PokkitLocation.toBukkit(event.getTo()));

		callCancellable(event, bukkitEvent);
	}
	
	@EventHandler(ignoreCancelled = false)
	public void onExplosion(cn.nukkit.event.entity.ExplosionPrimeEvent event) {
		if (canIgnore(PlayerInteractEvent.getHandlerList())) {
			return;
		}
		
		ExplosionPrimeEvent bukkitEvent = new ExplosionPrimeEvent(PokkitEntity.toBukkit(event.getEntity()), (float) event.getForce(), true); // Always true because Nukkit doesn't have that boolean

		callCancellable(event, bukkitEvent);
	}
}
