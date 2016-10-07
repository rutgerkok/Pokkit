package nl.rutgerkok.pokkit.inventory;

import java.util.Objects;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

public final class PokkitInventoryView extends InventoryView {

	private final Inventory topInventory;
	private final Inventory bottomInventory;
	private final HumanEntity player;
	private final InventoryType type;

	public PokkitInventoryView(Inventory top, Inventory bottom, HumanEntity player, InventoryType type) {
		this.topInventory = Objects.requireNonNull(top, "top");
		this.bottomInventory = Objects.requireNonNull(bottom, "bottom");
		this.player = Objects.requireNonNull(player, "player");
		this.type = Objects.requireNonNull(type, "type");
	}

	@Override
	public Inventory getBottomInventory() {
		return bottomInventory;
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
		return type;
	}

}
