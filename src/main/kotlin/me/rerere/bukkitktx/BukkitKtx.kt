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
        try {
            val pm = Bukkit.getPluginManager() as SimplePluginManager
            val commandMap : SimpleCommandMap = pm.javaClass.getDeclaredField("commandMap").let {
                it.isAccessible = true
                it.get(pm) as SimpleCommandMap
            }
            val scmClazz = SimpleCommandMap::class.java
            val knownCommand : MutableMap<String, Command> = scmClazz.getDeclaredField("knownCommands").let {
                it.isAccessible = true
                it.get(commandMap) as MutableMap<String, Command>
            }
            knownCommand["plugins"] = PluginListCommand()
            knownCommand["pl"] = PluginListCommand()
            logger.info("Injected `plugins` command")
        }catch (exception : Exception){
            exception.printStackTrace()
            logger.warning("Failed to inject `plugins` command!")
        }
    }
}

fun isKTXPlugin(plugin: Plugin): Boolean {
    return plugin == BukkitKtx.instance || plugin.description.depend.contains(BukkitKtx.instance.name)
}
