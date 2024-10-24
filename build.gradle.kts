plugins {
    id("com.android.application") version "8.7.1" apply false
    id("com.android.library") version "8.7.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
}

tasks.register<Delete>("clean").configure {
    delete(rootProject.layout.buildDirectory)
}
