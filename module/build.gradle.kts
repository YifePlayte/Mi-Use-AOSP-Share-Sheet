plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 34

    namespace = "com.yifeplayte.miuseaospsharesheet"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    externalNativeBuild {
        ndkBuild {
            path = file("jni/Android.mk")
        }
    }

    defaultConfig {
        applicationId = "com.yifeplayte.miuseaospsharesheet"
        minSdk = 33
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"
    }

    buildTypes {
        named("release") {
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles("proguard-rules.pro")
        }
    }
}

dependencies {
    compileOnly(project(":hiddenapi"))
}
