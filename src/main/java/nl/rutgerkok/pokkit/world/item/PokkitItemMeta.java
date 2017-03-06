package nl.rutgerkok.pokkit.world.item;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import nl.rutgerkok.pokkit.Pokkit;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;

import com.google.common.base.Strings;
import com.google.common.base.Throwables;

import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.ListTag;
import cn.nukkit.nbt.tag.Tag;
import cn.nukkit.item.enchantment.Enchantment;

public class PokkitItemMeta extends ItemMeta.Spigot implements ItemMeta {

	protected CompoundTag tag;

	PokkitItemMeta(CompoundTag tag) {
		this.tag = Objects.requireNonNull(tag, "tag");
	}

	@Override
	public boolean addEnchant(Enchantment ench, int level, boolean ignoreLevelRestriction) {
		throw Pokkit.unsupported();
	}

	@Override
	public void addItemFlags(ItemFlag... itemFlags) {
		// No supported
	}

	@Override
	public ItemMeta clone() {
		try {
			PokkitItemMeta meta = (PokkitItemMeta) super.clone();
			meta.tag = this.tag.copy();
			return meta;
		} catch (CloneNotSupportedException e) {
			throw Throwables.propagate(e);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PokkitItemMeta other = (PokkitItemMeta) obj;
		if (!tag.equals(other.tag))
			return false;
		return true;
	}

	@Override
	public String getDisplayName() {
		if (!tag.contains("display")) {
			return "";
		}
		CompoundTag displayTag = tag.getCompound("display");
		return displayTag.getString("Name");
	}

	@Override
	public int getEnchantLevel(Enchantment ench) {
		throw Pokkit.unsupported();
	}

	@Override
	public Map<Enchantment, Integer> getEnchants() {
		throw Pokkit.unsupported();
	}

	@Override
	public Set<ItemFlag> getItemFlags() {
		return EnumSet.noneOf(ItemFlag.class);
	}

	@Override
	public List<String> getLore() {
		return new ArrayList<>();
	}

	/**
	 * Accesses the NBT tag of this item meta.
	 *
	 * @return The NBT tag.
	 */
	public CompoundTag getTag() {
		return tag;
	}

	@Override
	public boolean hasConflictingEnchant(Enchantment ench) {
		throw Pokkit.unsupported();
	}

	@Override
	public boolean hasDisplayName() {
		if (!tag.contains("display")) {
			return false;
		}
		CompoundTag displayTag = tag.getCompound("display");
		return displayTag.contains("Name");
	}

	@Override
	public boolean hasEnchant(Enchantment ench) {
		throw Pokkit.unsupported();
	}

	@Override
	public boolean hasEnchants() {
        CompoundTag tag = this.tag;

        if (tag.contains("ench")) {
            Tag enchTag = tag.get("ench");
            if (enchTag instanceof ListTag) {
                return true;
            }
        }

        return false;
	}

	@Override
	public int hashCode() {
		return tag.hashCode();
	}

	@Override
	public boolean hasItemFlag(ItemFlag flag) {
		return false;
	}

	@Override
	public boolean hasLore() {
		return false;
	}

	boolean isApplicable(Material material) {
		return true;
	}

	@Override
	public boolean isUnbreakable() {
		return false;
	}

	@Override
	public boolean removeEnchant(Enchantment ench) {
		throw Pokkit.unsupported();
	}

	@Override
	public void removeItemFlags(ItemFlag... itemFlags) {
		// Not supported
	}

	@Override
	public Map<String, Object> serialize() {
		throw Pokkit.unsupported();
	}

	@Override
	public void setDisplayName(String name) {
		if (Strings.isNullOrEmpty(name)) {
			if (!hasDisplayName()) {
				return;
			}

			// Remove custom name
			CompoundTag displayTag = tag.getCompound("display");
			displayTag.remove("Name");
			setOrRemoveChildTag(tag, "display", displayTag);
			return;
		}

		CompoundTag displayTag = tag.getCompound("display");
		displayTag.putString("Name", name);
		tag.putCompound("display", displayTag);
	}

	@Override
	public void setLore(List<String> lore) {
		// Not supported
	}

	/**
	 * Sets the child in the parent when the child is not empty. If the child is
	 * empty, the child tag is removed from the parent.
	 *
	 * @param parent
	 *            The parent tag.
	 * @param name
	 *            The name of the child tag.
	 * @param child
	 *            The child tag.
	 */
	protected void setOrRemoveChildTag(CompoundTag parent, String name, CompoundTag child) {
		if (child.isEmpty()) {
			parent.remove(name);
		} else {
			parent.putCompound(name, child);
		}
	}

	@Override
	public void setUnbreakable(boolean unbreakable) {
		throw Pokkit.unsupported();
	}

	@Override
	public Spigot spigot() {
		return this;
	}

}
