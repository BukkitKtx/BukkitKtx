package me.rerere.bukkitktx.extensions

import org.bukkit.Bukkit
import org.bukkit.Location
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
 */
infix fun Player.send(text: String) {
    this.sendMessage(text)
}

/**
 * teleport player: "player teleport location"
 */
infix fun Player.teleport(location: Location) {
    require(Bukkit.isPrimaryThread())
    this.teleport(location)
}