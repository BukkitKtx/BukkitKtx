package me.rerere.bukkitktx.extensions

import org.bukkit.entity.Player

class PlayerData constructor(val bukkitPlayer: Player) : Player by bukkitPlayer{
    override fun isSwimming(): Boolean {
        return false
    }
}