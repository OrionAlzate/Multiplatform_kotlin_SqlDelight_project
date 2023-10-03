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

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("app.cash.sqldelight:sqlite-driver:2.0.0")
            }
        }

        val androidMain by getting {
            dependencies {
                implementation("app.cash.sqldelight:android-driver:2.0.0")

            }
        }

        val iosMain by getting {
            dependencies {
                implementation("app.cash.sqldelight:native-driver:2.0.0")

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