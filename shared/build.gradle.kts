plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("app.cash.sqldelight")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
    }

    sourceSets {
        val ktorVersion = "2.3.4"
        val commonMain by getting {
            dependencies {
                implementation("app.cash.sqldelight:sqlite-driver:2.0.0")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

            }
        }

        val androidMain by getting {
            dependencies {
                implementation("app.cash.sqldelight:android-driver:2.0.0")
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
            }
        }

        val iosMain by getting {

            dependencies {
                implementation("app.cash.sqldelight:native-driver:2.0.0")
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
            }
        }


    }
}

android {
    namespace = "com.mantum.multiapp"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("com.mantum.database")
        }
    }
}