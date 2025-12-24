import java.io.BufferedReader
import java.io.FileReader
import org.gradle.jvm.tasks.Jar

plugins {
    id("fabric-loom") version "1.14-SNAPSHOT"
    id("maven-publish")
    id("com.modrinth.minotaur") version "2.+"
    kotlin("jvm") version "2.2.10"
    id("com.google.devtools.ksp") version "2.2.10-2.0.2"
    id("dev.kikugie.fletching-table.fabric") version "0.1.0-alpha.22"
}

version = "${project.property("mod_version")}+${stonecutter.current.project}"
group = project.property("maven_group") as String

base.archivesName = project.property("archives_base_name") as String

repositories {
}

fabricApi {
    configureDataGeneration {
        client = true
    }
}

dependencies {
    // To change the versions see the gradle.properties file
    minecraft("com.mojang:minecraft:${stonecutter.current.project}")
    mappings (loom.layered {
        officialMojangMappings()
        if (hasProperty("deps.parchment")) {
            parchment("org.parchmentmc.data:parchment-${stonecutter.current.project}:${property("deps.parchment")}@zip")
        }
    })
    modImplementation("net.fabricmc:fabric-loader:${project.property("loader_version")}")

    // Fabric API. This is technically optional, but you probably want it anyway.
    modImplementation("net.fabricmc.fabric-api:fabric-api:${project.property("deps.fabric_api")}")
}

stonecutter {
    replacements.string {
        direction = eval(current.version, ">1.21.10")
        replace("ResourceLocation", "Identifier")
    }
}

fletchingTable {
    mixins.create("main") {
        // Default matches the default value in the annotation
        mixin("default", "${project.property("archives_base_name")}.mixins.json") {
            env("CLIENT", "survivalblock.directional_melons.mixin.client")
        }
    }
    mixins.all {
        automatic = true
    }
}

tasks.processResources {
    val modVersion = project.version
    val minecraftVersion = stonecutter.current.version
    inputs.property("version", modVersion)
    inputs.property("minecraft", minecraftVersion)

    filesMatching("fabric.mod.json") {
        expand(
            mapOf(
                "version" to modVersion,
                "minecraft" to minecraftVersion
            )
        )
    }
}

tasks.named("build") {
    finalizedBy("autoVersionChangelog")
}

tasks.register("autoVersionChangelog") {
    doLast {
        val changelog = File("changelog.md")
        val reader = BufferedReader(FileReader(changelog))
        val lines = reader.readLines().toMutableList()
        val title = "Directional Melons ${project.property("mod_version")}"
        lines[0] = title
        changelog.bufferedWriter().use { writer ->
            for (i in 0..<lines.size) {
                writer.write(lines[i])
                if (i != lines.size - 1) {
                    writer.newLine()
                }
            }
        }
        println("Changelog header successfully replaced as $title")
    }
}

loom {
    runConfigs.all {
        ideConfigGenerated(true)
        runDir = "../../run"
    }

    runConfigs["client"].apply {
        programArgs("--username=Survivalblock", "--uuid=c45e97e6-94ef-42da-8b5e-0c3209551c3f")
    }

    fabricModJsonPath = rootProject.file("src/main/resources/fabric.mod.json")

    mixin {
        useLegacyMixinAp = true
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(21)
}

java {
    // Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build" task
    // if it is present.
    // If you remove this line, sources will not be generated.
    withSourcesJar()

    val java = if (stonecutter.eval(stonecutter.current.version, ">=1.20.5"))
        JavaVersion.VERSION_21 else JavaVersion.VERSION_17

    targetCompatibility = java
    sourceCompatibility = java
}

tasks.jar {
    inputs.property("archivesName", project.base.archivesName)

    from("LICENSE") {
        rename { "${it}_${base.archivesName}"}
    }
}

modrinth {
    token = providers.environmentVariable("MODRINTH_TOKEN")
    projectId = project.base.archivesName
    version = project.version
    uploadFile.set(tasks.named<Jar>("remapJar").get().archiveFile)
    gameVersions.addAll("${project.property("deps.compatibleVersions")}".split(", ").toList())
    loaders.addAll("${project.property("deps.compatibleLoaders")}".split(", ").toList())
    changelog = rootProject.file("changelog.md").readText()
    syncBodyFrom = "<!--DO NOT EDIT MANUALLY: synced from gh readme-->\n" + rootProject.file("README.md").readText()
}