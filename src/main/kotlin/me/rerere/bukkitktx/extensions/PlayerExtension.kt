package me.rerere.bukkitktx.extensions

import net.md_5.bungee.api.chat.BaseComponent
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * Get the latency data of player
 */
val Player.latency: Int
    get() = try {
        val nms = this.javaClass.getDeclaredMethod("getHandle").invoke(this)
        nms.javaClass.getDeclaredField("ping").getInt(nms)
    } catch (exception: Exception) {
        println("Failed to get player latency: ${exception.javaClass.name}")
        exception.printStackTrace()
        0 // 无法获取延迟信息，返回 0
    }

/**
 *  send message: "player send string"
 *
 *  @param text Message
 */
infix fun Player.send(text: String) {
    this.sendMessage(text)
}

/**
 * teleport player: "player teleport location"
 *
 * @param location Destination
 */
infix fun Player.teleport(location: Location) {
    require(Bukkit.isPrimaryThread())
    this.teleport(location)
}

/**
 * Send multiple messages to player
 *
 * @param lines messages
 */
fun CommandSender.sendMessage(lines : Collection<String>){
    lines.forEach { this.sendMessage(it) }
}

/**
 * Send tellraw message to player
 *
 * @param tellrawMessage tellraw message
 */
fun Player.sendMessage(tellrawMessage : BaseComponent){
    this.spigot().sendMessage(tellrawMessage)
}

/**
 * Send tellraw messages to player
 *
 * @param tellrawMessages tellraw messages
 */
fun Player.sendMessage(tellrawMessages : Collection<BaseComponent>){
    tellrawMessages.forEach { this.sendMessage(it) }
}