version = "0.1.0"

plugins {
    id("java-library")
    id("com.github.johnrengelman.shadow") version ("7.0.0")
}

repositories {
    mavenLocal()
}

dependencies {
    labyProcessor()
    api(project(":api"))
    shade(files("../libs/pircbot-1.5.0.jar"))
    //maven(mavenCentral(), "pircbot:pircbot:1.5.0")
}

addon {
    internalRelease()
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks {
    shadowJar {
        archiveClassifier.set("")
        archiveBaseName.set("core")

        dependencyFilter.exclude {
            !(it.moduleGroup.startsWith("streamchatplus") || it.moduleGroup.equals("de.unordentlich.streamchatplus.core"))
        }
    }

    getByName("jar").finalizedBy("shadowJar")
}

tasks.compileJava {
    sourceCompatibility = JavaVersion.VERSION_1_8.toString()
    targetCompatibility = JavaVersion.VERSION_1_8.toString()
}