package me.rerere.bukkitktx.utils

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import java.util.*

inline fun player(name: String, action: (Player) -> Unit = {}): Player? {
    return Bukkit.getPlayer(name).apply {
        this?.let { action(it) }
    }
}

inline fun player(uuid: UUID, action: (Player) -> Unit = {}): Player? {
    return Bukkit.getPlayer(uuid).apply {
        this?.let { action(it) }
    }
}

fun sync(plugin: Plugin, task: () -> Unit) {
    Bukkit.getScheduler().runTask(plugin, task)
}

fun async(plugin: Plugin, task: () -> Unit) {
    Bukkit.getScheduler().runTaskAsynchronously(plugin, task)
}