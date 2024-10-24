plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

object Props {
    const val versionCode = 5
    const val versionName = "v1.0.4"
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
        versionCode = Props.versionCode
        versionName = Props.versionName
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
    implementation("org.lsposed.hiddenapibypass:hiddenapibypass:4.3")
}

tasks.register<Zip>("assembleModule") {
    val zipTree = zipTree(layout.buildDirectory.asFile.get().resolve("outputs/apk/release/module-release-unsigned.apk"))
    from(zipTree) {
        include("assets/**", "lib/**", "classes.dex")
        exclude("assets/module.prop")
        eachFile {
            path = when {
                path.startsWith("lib/") -> buildString {
                    val startIndex = path.indexOf('/') + 1
                    val endIndex = path.indexOf('/', startIndex)
                    append("zygisk/")
                    append(path.substring(startIndex, endIndex))
                    append(".so")
                }

                path.startsWith("assets/") -> path.replace("assets/", "")

                else -> path
            }
        }
    }
    from(file("src/main/assets/module.prop")) {
        filter { line ->
            line.replace("%%VERSION%%", Props.versionName)
                .replace("%%VERSIONCODE%%", Props.versionCode.toString())
        }
    }
    destinationDirectory.set(layout.buildDirectory.asFile.get().resolve("outputs/module"))
    archiveFileName.set("mi-use-aosp-share-sheet.zip")
}

afterEvaluate {
    tasks["assembleModule"].dependsOn(tasks["assembleRelease"])
}
