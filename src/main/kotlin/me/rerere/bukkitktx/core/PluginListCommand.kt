package me.rerere.bukkitktx.core

import me.rerere.bukkitktx.extensions.sendMessage
import me.rerere.bukkitktx.isKTXPlugin
import me.rerere.bukkitktx.utils.coloring
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.command.defaults.BukkitCommand
import java.util.*

class PluginListCommand : BukkitCommand("plugins") {
    init {
        description = "Gets a list of plugins running on the server"
        usageMessage = "/plugins"
        permission = "bukkit.command.plugins"
        aliases = listOf("pl")
    }

    override fun execute(sender: CommandSender, commandLabel: String, args: Array<out String>): Boolean {
        if (!testPermission(sender)) return true

        sender.sendMessage(getPluginList())
        return true
    }

    override fun tabComplete(sender: CommandSender, alias: String, args: Array<out String>): MutableList<String> {
        return Collections.emptyList()
    }

    private fun getPluginList(): List<String> {
        val plugins = Bukkit.getPluginManager().plugins
        val javaPlugins = plugins.filter { !isKTXPlugin(it) }
        val kotlinPlugins = plugins.filter { isKTXPlugin(it) }
        return listOf(
            "Plugins (${javaPlugins.size + kotlinPlugins.size}): ",
            "  &7♦ &fKotlin: &r".coloring() + kotlinPlugins.joinToString(separator = ", ") { "&e${it.name}".coloring() },
            "  &7♦ &fJava: &r".coloring() + javaPlugins.joinToString(separator = ", ") { "&a${it.name}".coloring() })
    }
}