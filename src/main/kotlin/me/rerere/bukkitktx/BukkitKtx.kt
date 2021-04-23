package me.rerere.bukkitktx

import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin

class BukkitKtx : JavaPlugin() {
    companion object {
        lateinit var instance: Plugin
    }

    init {
        instance = this
    }

    override fun onEnable() {
        logger.info("Loaded BukkitKtx (Version: ${description.version}, Kotlin: ${KotlinVersion.CURRENT})")
    }
}