plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.library")
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)

}

kotlin {
    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        getByName("commonMain") {
            dependencies {
                implementation(libs.androidx.navigation.compose)
                implementation(libs.kotlinx.serialization.json)
                implementation(projects.feature.home)
                implementation(projects.feature.taskeditor)
            }
        }

        getByName("androidMain") {
            dependsOn(getByName("commonMain"))
        }

        create("iosMain") {
            dependsOn(getByName("commonMain"))
        }

        getByName("iosX64Main") {
            dependsOn(getByName("iosMain"))
        }

        getByName("iosArm64Main") {
            dependsOn(getByName("iosMain"))
        }

        getByName("iosSimulatorArm64Main") {
            dependsOn(getByName("iosMain"))
        }
    }
}

android {
    namespace = "ab.todoapp.navigation"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}