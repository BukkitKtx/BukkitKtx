package me.rerere.bukkitktx.utils

import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

fun YamlConfiguration.loadFromFile(file : File): YamlConfiguration {
    return YamlConfiguration().apply {
        load(file)
    }
}