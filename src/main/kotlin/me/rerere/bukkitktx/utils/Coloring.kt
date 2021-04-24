package me.rerere.bukkitktx.utils

import org.bukkit.ChatColor

/**
 * Use '&' color codes to replace color variables of string
 */
fun String.coloring(colorCode : Char = '&') : String{
    return ChatColor.translateAlternateColorCodes(colorCode, this)
}

/**
 * Use '&' color codes to replace color variables of string collections
 */
fun Collection<String>.coloring(colorCode : Char = '&') : Collection<String>{
    return this.map { it.coloring(colorCode) }
}