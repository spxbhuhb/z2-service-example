/*
 * Copyright © 2020-2021, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */

import java.net.URI

plugins {
    kotlin("multiplatform") version "1.9.0"
    kotlin("plugin.serialization") version "1.9.0"
    id("com.google.devtools.ksp")version "1.9.0-1.0.11"
    application
}

repositories {
    mavenCentral()
    google()
    maven {
        url = URI("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    }
}

val z2_rpc_version : String by project

application {
    mainClass.set("hu.simplexion.z2.rpc.test.MainKt")
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
                implementation("hu.simplexion.z2:z2-rpc-runtime:${z2_rpc_version}")
                implementation("hu.simplexion.z2:z2-rpc-ktor:${z2_rpc_version}")
            }
        }
        val jvmMain by getting
        val jsMain by getting
    }
}

// https://github.com/google/ksp/issues/567#issuecomment-1510477456

dependencies {
    add("kspCommonMainMetadata", "hu.simplexion.z2:z2-rpc-processor:${z2_rpc_version}")
}

tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinCompile<*>>().all {
    if(name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}

kotlin.sourceSets.commonMain {
    kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
}