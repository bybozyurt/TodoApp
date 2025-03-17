plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.library")
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    androidTarget()
    applyDefaultHierarchyTemplate()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.components.resources)
                implementation(compose.ui)
                implementation(compose.runtime)
            }
        }
    }
}

android {
    namespace = "ab.todoapp.shared"
    compileSdk = libs.versions.android.targetSdk.get().toInt()

    sourceSets["main"].res.srcDirs("src/androidMain/res", "src/main/composeResources")

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    compose.resources {
        publicResClass = false
        packageOfResClass = "ab.todoapp.shared.resources"
        generateResClass = always
    }
}