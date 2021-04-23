package me.rerere.bukkitktx.extensions

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player

/**
 * 扩展属性，获得玩家的延迟信息
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
 *  精简的发送消息: "player send string"
 */
infix fun Player.send(text: String) {
    this.sendMessage(text)
}

/**
 * 精简传送: "player teleport location"
 */
infix fun Player.teleport(location: Location) {
    require(Bukkit.isPrimaryThread())
    this.teleport(location)
}