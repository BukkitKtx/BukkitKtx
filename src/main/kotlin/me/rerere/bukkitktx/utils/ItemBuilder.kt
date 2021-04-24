package me.rerere.bukkitktx.utils

import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

/**
 * Use higher-order functions to quickly build ItemStack
 */
inline fun buildItemStack(type: Material, modifier: ItemBuilder.() -> (Unit)): ItemStack {
    val builder = ItemBuilder(type)
    builder.modifier()
    return builder.itemStack
}

/**
 * The builder of ItemStack, don't use it directly, please use buildItemStack()
 *
 * @see buildItemStack
 */
class ItemBuilder constructor(type: Material, amount: Int = 1) {
    var itemStack = ItemStack(type, amount)

    /**
     * The display name of the item
     */
    var displayName: String?
        get() = if (itemStack.itemMeta != null) itemStack.itemMeta!!.displayName else itemStack.type.name
        set(value) {
            itemStack.itemMeta?.setDisplayName(value)
        }

    /**
     * The amount of the item
     */
    var amount: Int
        get() = itemStack.amount
        set(value) {
            itemStack.amount = value
        }

    /**
     * Change the lore of the item
     *
     * @param lore the lore data
     */
    fun lore(lore: List<String>, colorful: Boolean = true): ItemBuilder {
        itemStack.itemMeta?.lore = lore.apply { if (colorful) this.coloring() }
        return this
    }

    /**
     * Add enchantment to the item
     *
     * @param enchants the enchantment that you want to add
     * @param ignoreLevelRestrict should ignoring the level limit
     */
    fun enchant(vararg enchants: Pair<Enchantment, Int>, ignoreLevelRestrict: Boolean = false): ItemBuilder {
        itemStack.itemMeta?.let { meta ->
            for (enchant in enchants) {
                val (type, level) = enchant
                meta.addEnchant(type, level, ignoreLevelRestrict)
            }
        }
        return this
    }

    /**
     * Use lambda to edit the item meta
     *
     * @param modifier the modifier of meta
     */
    inline fun meta(modifier: ItemMeta.() -> Unit): ItemBuilder {
        val meta = itemStack.itemMeta
        meta?.let {
            it.modifier()
            itemStack.itemMeta = it
        }
        return this
    }
}