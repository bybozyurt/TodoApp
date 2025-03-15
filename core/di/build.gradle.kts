plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.library")
}

kotlin {
    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        getByName("commonMain") {
            dependencies {
                implementation(libs.koin.core)
                implementation(libs.koin.compose)
                implementation("co.touchlab:stately-common:2.0.5")
                implementation(libs.kotlin.coroutines)
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
    namespace = "ab.todoapp.core.di"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

}