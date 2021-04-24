package me.rerere.bukkitktx.common

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.*

/**
 * directly get the player object
 *
 * @param name Player name
 * @param operation execute the operation if found the player
 */
inline fun playerOf(name: String, operation: Player.() -> Unit = {}): Player? {
    val player = Bukkit.getPlayerExact(name)
    player?.let(operation)
    return player
}

/**
 * directly get the player object
 *
 * @param uuid uuid of the player
 * @param operation execute the operation if found the player
 */
inline fun playerOf(uuid: UUID, operation: Player.() -> Unit = {}): Player? {
    val player = Bukkit.getPlayer(uuid)
    player?.let(operation)
    return player
}