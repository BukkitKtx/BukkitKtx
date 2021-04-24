package me.rerere.bukkitktx.command

fun createCommand(name: String, builder: KCommand.() -> Unit) {

}

class KCommand(name: String) : CommandSection(name) {
}