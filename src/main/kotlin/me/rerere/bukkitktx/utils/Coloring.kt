package me.rerere.bukkitktx.utils

import org.bukkit.ChatColor

/**
 * Use '&' color codes to replace color variables of string
 */
fun String.coloring() : String{
    return ChatColor.translateAlternateColorCodes('&', this)
}

/**
 * Use '&' color codes to replace color variables of string collections
 */
fun Collection<String>.coloring() : Collection<String>{
    return this.map { it.coloring() }
}