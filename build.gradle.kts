/*
 * Copyright © 2023, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    kotlin("multiplatform") version "1.9.0"
//    id("hu.simplexion.z2.schematic") version "2023.7.30-SNAPSHOT"
    id("hu.simplexion.z2.service") version "2023.8.18-SNAPSHOT"
    application
}

repositories {
    mavenLocal()
    mavenCentral()
}

val z2_commons_version : String by project
val z2_schematic_version : String by project
val z2_service_version : String by project
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
                implementation("hu.simplexion.z2:z2-commons:${z2_commons_version}")
                //implementation("hu.simplexion.z2:z2-schematic-runtime:${z2_schematic_version}")
                implementation("hu.simplexion.z2:z2-service-runtime:${z2_service_version}")
                implementation("hu.simplexion.z2:z2-service-ktor:${z2_service_version}")

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