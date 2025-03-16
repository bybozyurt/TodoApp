plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.library")
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        getByName("commonMain") {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.components.uiToolingPreview)

                implementation(libs.navigator)
                implementation(libs.navigator.screen.model)
                implementation(libs.navigator.transitions)
                implementation(libs.navigator.koin)
                implementation(libs.androidx.navigation.compose)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.koin.core)
                implementation(libs.koin.compose)
                implementation(libs.koin.compose.viewmodel)
                implementation("co.touchlab:stately-common:2.0.5")
                implementation(libs.lifecycle.runtime.compose)


                implementation(project(":domain"))
                implementation(project(":shared"))
                implementation(project(":core:di"))
                implementation(project(":core:ui"))
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
    namespace = "ab.todoapp.feature.home"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
}