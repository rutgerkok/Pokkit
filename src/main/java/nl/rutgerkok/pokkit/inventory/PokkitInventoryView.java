package nl.rutgerkok.pokkit.inventory;

import java.util.Objects;

import org.bukkit.GameMode;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

public final class PokkitInventoryView extends InventoryView {

	private final Inventory topInventory;
	private final HumanEntity player;

	public PokkitInventoryView(Inventory top, HumanEntity player) {
		this.topInventory = Objects.requireNonNull(top, "top");
		this.player = Objects.requireNonNull(player, "player");
	}

	@Override
	public Inventory getBottomInventory() {
		return player.getInventory();
	}

	@Override
	public HumanEntity getPlayer() {
		return player;
	}

	@Override
	public Inventory getTopInventory() {
		return topInventory;
	}

	@Override
	public InventoryType getType() {
		// Copied from CraftBukkit
		InventoryType type = topInventory.getType();
		if (type == InventoryType.CRAFTING && player.getGameMode() == GameMode.CREATIVE) {
			return InventoryType.CREATIVE;
		}
		return type;
	}

}
