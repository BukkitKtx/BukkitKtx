package me.rerere.bukkitktx.utils

import org.bukkit.ChatColor

/**
 * Use '&' color codes to replace color variables of string
 */
fun String.colorful() : String{
    return ChatColor.translateAlternateColorCodes('&', this)
}

/**
 * Use '&' color codes to replace color variables of string collections
 */
fun Collection<String>.colorful() : Collection<String>{
    return this.map { it.colorful() }
}