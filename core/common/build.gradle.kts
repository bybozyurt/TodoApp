plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.library")
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
            baseName = "core.common"
            isStatic = true
        }
    }

    sourceSets {
        getByName("commonMain") {
            dependencies {
                implementation(libs.koin.core)
                implementation(libs.koin.compose)
                implementation("co.touchlab:stately-common:2.0.5")
                implementation(libs.kotlin.coroutines)
            }
        }
    }
}

android {
    namespace = "ab.todoapp.core.common"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

}