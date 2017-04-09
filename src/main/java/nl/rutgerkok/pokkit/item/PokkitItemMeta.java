package nl.rutgerkok.pokkit.item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import nl.rutgerkok.pokkit.Pokkit;
import nl.rutgerkok.pokkit.enchantment.PokkitEnchantment;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;

import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableMap;

import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.ListTag;
import cn.nukkit.nbt.tag.StringTag;
import cn.nukkit.nbt.tag.Tag;

public class PokkitItemMeta extends ItemMeta.Spigot implements ItemMeta {

	protected CompoundTag tag;

	PokkitItemMeta(CompoundTag tag) {
		this.tag = Objects.requireNonNull(tag, "tag");
	}

	@Override
	public boolean addEnchant(Enchantment enchantment, int level, boolean ignoreLevelRestriction) {
		if (level > enchantment.getMaxLevel() && !ignoreLevelRestriction) {
			return false;
		}

		short nukkitEnchantmentId = (short) PokkitEnchantment.toNukkit(enchantment);

		ListTag<CompoundTag> enchTag;
		if (!hasEnchants()) {
			enchTag = new ListTag<>("ench");
			tag.putList(enchTag);
		} else {
			enchTag = tag.getList("ench", CompoundTag.class);
		}

		// Try to modify an existing enchantment first
		boolean modifiedATag = false;
		for (int i = 0; i < enchTag.size(); i++) {
			CompoundTag entry = enchTag.get(i);
			if (entry.getShort("id") == nukkitEnchantmentId) {
				// The add method actually replaces a tag
				enchTag.add(i, new CompoundTag()
						.putShort("id", nukkitEnchantmentId)
						.putShort("lvl", level));
				modifiedATag = true;
				break;
			}
		}

		// Else, add this enchantment as a new enchantment
		if (!modifiedATag) {
			enchTag.add(new CompoundTag()
					.putShort("id", nukkitEnchantmentId)
					.putShort("lvl", level));
		}

		return true;
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
	public int getEnchantLevel(Enchantment enchantment) {
		int nukkitEnchantmentId = PokkitEnchantment.toNukkit(enchantment);

		if (!hasEnchants()) {
			return 0;
		}

		ListTag<CompoundTag> enchTag = tag.getList("ench", CompoundTag.class);
		for (int i = 0; i < enchTag.size(); i++) {
			CompoundTag entry = enchTag.get(i);
			if (entry.getShort("id") == nukkitEnchantmentId) {
				return entry.getShort("lvl");
			}
		}

		return 0;
	}

	@Override
	public Map<Enchantment, Integer> getEnchants() {
		if (!hasEnchants()) {
			return Collections.emptyMap();
		}

		ImmutableMap.Builder<Enchantment, Integer> map = new ImmutableMap.Builder<>();

		ListTag<CompoundTag> enchTag = tag.getList("ench", CompoundTag.class);
		for (int i = 0; i < enchTag.size(); i++) {
			CompoundTag entry = enchTag.get(i);
			map.put(PokkitEnchantment.toBukkit(entry.getShort("id")), entry.getShort("lvl"));
		}

		return map.build();
	}

	@Override
	public Set<ItemFlag> getItemFlags() {
		return EnumSet.noneOf(ItemFlag.class);
	}

	@Override
	public String getLocalizedName() {
		return null; // Silently unsupported
	}

	@Override
	public List<String> getLore() {
		List<String> lore = new ArrayList<>();
		
		if (!hasLore()) {
			return lore;
		}
		
		CompoundTag displayTag = tag.getCompound("display");
		ListTag<? extends Tag> loreListTag = displayTag.getList("Lore");
		
		for (Tag tag : loreListTag.getAll()) {
			if (!(tag instanceof StringTag)) {
				continue;
			}
			
			lore.add(((StringTag) tag).data);
		}
		
		return lore;
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
		for (Enchantment onItem : getEnchants().keySet()) {
			if (onItem.conflictsWith(ench)) {
				return true;
			}
		}
		return false;
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
	public boolean hasEnchant(Enchantment enchantment) {
		int nukkitEnchantmentId = PokkitEnchantment.toNukkit(enchantment);

		if (!hasEnchants()) {
			return false;
		}

		ListTag<CompoundTag> enchTag = tag.getList("ench", CompoundTag.class);
		for (int i = 0; i < enchTag.size(); i++) {
			CompoundTag entry = enchTag.get(i);
			if (entry.getShort("id") == nukkitEnchantmentId) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean hasEnchants() {
		CompoundTag tag = this.tag;

		if (tag.contains("ench")) {
			Tag enchTag = tag.get("ench");
			if (enchTag instanceof ListTag) {
				return ((ListTag<?>) enchTag).size() > 0;
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
	public boolean hasLocalizedName() {
		return false; // Silently unsupported
	}

	@Override
	public boolean hasLore() {
		if (!tag.contains("display")) {
			return false;
		}
		CompoundTag displayTag = tag.getCompound("display");
		return displayTag.contains("Lore");
	}

	boolean isApplicable(Material material) {
		return true;
	}

	@Override
	public boolean isUnbreakable() {
		return false;
	}

	@Override
	public boolean removeEnchant(Enchantment enchantment) {
		if (!hasEnchants()) {
			return false;
		}

		int nukkitEnchantmentId = PokkitEnchantment.toNukkit(enchantment);

		ListTag<CompoundTag> enchTag = tag.getList("ench", CompoundTag.class);
		for (int i = 0; i < enchTag.size(); i++) {
			CompoundTag entry = enchTag.get(i);
			if (entry.getShort("id") == nukkitEnchantmentId) {
				// Found enchantment of right type, remove
				enchTag.remove(i);

				if (enchTag.size() == 0) {
					// No enchantments remain, remove tag
					tag.remove("ench");
				}

				return true;
			}
		}

		return false;
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
	public void setLocalizedName(String name) {
		throw Pokkit.unsupported();
	}

	@Override
	public void setLore(List<String> lore) {
		ListTag<StringTag> loreListTag = new ListTag<>("Lore");
		
		if (lore != null) {
			for (String line : lore) {
				loreListTag.add(new StringTag("", line));
			}
		}
		
		CompoundTag displayTag = tag.getCompound("display");
		displayTag.putList(loreListTag);
		tag.putCompound("display", displayTag);
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
