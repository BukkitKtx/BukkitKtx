import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.32"
    id("com.github.johnrengelman.shadow") version "6.1.0"
    application
}

group = "me.rerere"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/groups/public/")
}

dependencies {
    implementation("org.spigotmc:spigot-api:1.16.5-R0.1-SNAPSHOT")
    testImplementation(kotlin("test-junit"))
}

tasks.test {
    useJUnit()
}

tasks{
    named<ShadowJar>("shadowJar"){
        exclude("net/md_5/**")
        exclude("mojang-translations/**")
    }

    build {
        dependsOn(shadowJar)
    }
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = ""
}