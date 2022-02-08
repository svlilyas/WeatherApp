import Dependencies.Project.routeCore

plugins {
    id(Plugins.androidLibrary)
    kotlin(Plugins.android)
    id(Plugins.kotlinExtensions)
    `maven-publish`
}

android {
    compileSdkVersion(Configs.compileSdkVersion)
    defaultConfig {
        targetSdkVersion(Configs.targetSdkVersion)
        minSdkVersion(Configs.minSdkVersion)
    }
}

androidExtensions {
    isExperimental = true
}

dependencies {
    /* Kotlin */
    implementation(Dependencies.Kotlin.StdLib.jdk)

    /* Android X */
    api(Dependencies.Android.fragment)
    implementation(routeCore())
}