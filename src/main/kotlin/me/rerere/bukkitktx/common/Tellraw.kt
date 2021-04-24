package me.rerere.bukkitktx.common

import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.TextComponent

/**
 * Use component+component to connect tellraw components
 */
operator fun BaseComponent.plus(component: BaseComponent): BaseComponent {
    this.addExtra(component)
    return this
}

/**
 * Use component+string to connect tellraw components
 */
operator fun BaseComponent.plus(component: String): BaseComponent {
    this.addExtra(component)
    return this
}

/**
 * Use lambda to quickly build tellraw
 */
inline fun textTellraw(text: String, modifier: BaseComponent.() -> Unit = {}): BaseComponent {
    val component = TextComponent(text)
    component.modifier()
    return component
}