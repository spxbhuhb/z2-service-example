/*
 * Copyright © 2020-2021, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */

import java.net.URI

plugins {
    kotlin("multiplatform") version "1.8.22"
    kotlin("plugin.serialization") version "1.8.22"
    id("com.google.devtools.ksp")version "1.8.22-1.0.11"
    application
}

repositories {
    mavenCentral()
    google()
    maven {
        url = URI("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    }
}

application {
    mainClass.set("hu.simplexion.z2.rpc.test.MainKt")
}

kotlin {

    jvm {
        jvmToolchain(11)
        withJava()
    }

    js(IR) {
        browser()
        binaries.executable()
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation("hu.simplexion.z2:z2-rpc-runtime:2023.7.7-SNAPSHOT")
                implementation("hu.simplexion.z2:z2-rpc-ktor:2023.7.7-SNAPSHOT")
            }
        }
        val jvmMain by getting
        val jsMain by getting
    }
}

dependencies {
 //   add("kspCommonMainMetadata", "hu.simplexion.z2:z2-rpc-runtime:2023.7.7-SNAPSHOT")
    add("kspJs", "hu.simplexion.z2:z2-rpc-processor:2023.7.7-SNAPSHOT")
    add("kspJvm", "hu.simplexion.z2:z2-rpc-processor:2023.7.7-SNAPSHOT")
}