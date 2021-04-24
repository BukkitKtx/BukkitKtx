package me.rerere.bukkitktx

import me.rerere.bukkitktx.core.PluginListCommand
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.SimpleCommandMap
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.SimplePluginManager
import org.bukkit.plugin.java.JavaPlugin

class BukkitKtx : JavaPlugin() {
    companion object {
        lateinit var instance: Plugin
    }

    init {
        instance = this
    }

    override fun onEnable() {
        injectPluginListCommand()
        logger.info("Loaded BukkitKtx (Version: ${description.version}, Kotlin: ${KotlinVersion.CURRENT})")
    }

    private fun injectPluginListCommand(){
        val pm = Bukkit.getPluginManager() as SimplePluginManager
        val commandMap : SimpleCommandMap = pm.javaClass.getDeclaredField("commandMap").let {
            it.isAccessible = true
            it.get(pm) as SimpleCommandMap
        }
        val knownCommand : MutableMap<String, Command> = commandMap.javaClass.getDeclaredField("knownCommands").let {
            it.isAccessible = true
            it.get(commandMap) as MutableMap<String, Command>
        }
        knownCommand["plugins"] = PluginListCommand()
        knownCommand["pl"] = PluginListCommand()
    }
}

fun isKTXPlugin(plugin: Plugin): Boolean {
    return plugin == BukkitKtx.instance || plugin.description.depend.contains(BukkitKtx.instance.name)
}
