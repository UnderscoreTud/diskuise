import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import java.util.*

plugins {
    id("java")
    id("com.gradleup.shadow") version "8.3.0"
}

group = "me.tud.diskuise"
version = "0.3.4"

repositories {
    mavenCentral()
    maven {
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    maven {
        url = uri("https://oss.sonatype.org/content/groups/public/")
    }
    maven {
        url = uri("https://repo.skriptlang.org/releases")
    }
    maven {
        url = uri("https://repo.md-5.net/content/groups/public")
    }
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
    maven {
        url = uri("https://repo.dmulloy2.net/repository/public/")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    compileOnly("com.github.SkriptLang:Skript:2.10.0")
    compileOnly("LibsDisguises:LibsDisguises:10.0.44")
    compileOnly("com.comphenix.protocol:ProtocolLib:5.1.0")
    compileOnly("org.eclipse.jdt:org.eclipse.jdt.annotation:2.2.600")
    implementation("org.bstats:bstats-bukkit:3.0.2")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

val properties = Properties()
val localProperties = file("local.properties")
if (localProperties.isFile)
    properties.load(localProperties.inputStream())
val jarName: String? = properties.getProperty("jarName")
val jarDir: String? = properties.getProperty("jarDir")

tasks.withType<Jar> {
    archiveFileName.set(jarName ?: "${project.name}-${project.version}.jar")
    if (jarDir != null)
        destinationDirectory.set(project.file(jarDir))
}

tasks.withType<ShadowJar> {
    relocate("org.bstats", "me.tud.diskuise.bstats")
}

tasks.processResources {
    expand(mapOf(
        "name" to project.name,
        "version" to project.version,
        "group" to project.group
    ))
    filteringCharset = "UTF-8"
}
