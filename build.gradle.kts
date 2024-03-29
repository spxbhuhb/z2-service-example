/*
 * Copyright © 2023, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    kotlin("multiplatform") version "1.9.0"
    id("hu.simplexion.z2.service") version "2023.7.28"
    application
}

repositories {
    mavenCentral()
}

val z2_version : String by project
val ktor_version: String by project
val logback_version: String by project

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

                implementation("io.ktor:ktor-client-websockets:$ktor_version")
            }
        }
        sourceSets["jvmMain"].dependencies {
            dependencies {
                implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
                implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
                implementation("io.ktor:ktor-server-websockets:$ktor_version")
                implementation("ch.qos.logback:logback-classic:$logback_version")
            }
        }
    }
}