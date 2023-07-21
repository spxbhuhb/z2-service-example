/*
 * Copyright © 2020-2021, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */

import java.net.URI

plugins {
    kotlin("multiplatform") version "1.9.0"
    id("hu.simplexion.z2.service") version "2023.7.21-SNAPSHOT"
    application
}

repositories {
    mavenLocal()
    mavenCentral()
    google()
    maven {
        url = URI("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    }
}

val z2_version : String by project

application {
    mainClass.set("hu.simplexion.z2.service.example.MainKt")
}

kotlin {

    jvmToolchain(11)

    jvm {
        withJava()
    }

    js(IR) {
        browser()
        binaries.executable()
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation("hu.simplexion.z2:z2-service-runtime:${z2_version}")
                implementation("hu.simplexion.z2:z2-service-ktor:${z2_version}")
            }
        }
        val jvmMain by getting
        val jsMain by getting
    }
}