plugins {
    id(Plugins.androidLibrary)
    kotlin(Plugins.android)
    kotlin(Plugins.kapt)
    kotlin(Plugins.androidExtensions)
    id(Plugins.daggerHiltPlugin)
}
android {
    compileSdkVersion(Configs.compileSdkVersion)
    buildToolsVersion = Configs.buildToolsVersion

    defaultConfig {
        minSdkVersion(Configs.minSdkVersion)
        targetSdkVersion(Configs.targetSdkVersion)

        versionCode = Configs.versionCode
        versionName = Configs.versionName
        testInstrumentationRunner = Configs.testInstrumentationRunner
    }

    sourceSets {
        getByName(Flavors.Default.MAIN) {
            java.srcDir("src/main/kotlin")
        }
    }

    buildTypes {
        getByName(Flavors.BuildTypes.RELEASE) {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))
    // Kotlin
    implementation(Dependencies.Kotlin.kotlinStdLib)
    implementation(Dependencies.Google.mapUtils)

    // Android
    implementation(Dependencies.Android.androidCore)

    // Network
    api(Dependencies.Network.gson)
    api(Dependencies.Network.gsonAdapter)
    api(Dependencies.Network.retrofit)
    api(Dependencies.Network.rxJavaAdapter)
    api(Dependencies.Network.okHttp)
    api(Dependencies.Network.loggingInterceptor)
    api(Dependencies.Network.scalar)

    // Firebase
    implementation(platform(Dependencies.Firebase.firebaseBom))
    implementation(Dependencies.Firebase.firebaseAnalysisktx)
    implementation(Dependencies.Firebase.crashlytics)
    implementation(Dependencies.Firebase.commonKtx)
    implementation(Dependencies.Firebase.perf)
    implementation(Dependencies.Firebase.firebaseCore)
    implementation(Dependencies.Firebase.firebaseAnalytics)
    implementation(Dependencies.Firebase.firebaseIID)
    implementation(Dependencies.Firebase.firebaseMessaging)
    implementation("com.google.firebase:firebase-database-ktx")

    // Huawei
    implementation(Dependencies.Huawei.huaweiLocation)
    implementation(Dependencies.Huawei.huaweiMaps)

    // Google
    implementation(Dependencies.Google.playSevicesMap)
    implementation(Dependencies.Google.mapUtils)
    implementation(Dependencies.Google.googleLocation)

    // Security
    api(Dependencies.Security.crypto)

    // DI
    api(Dependencies.DI.hilt)
    kapt(Dependencies.DI.hiltCompiler)

    // ReactiveFunc
    api(Dependencies.ReactiveFunc.rxJava)

    // Timber
    api(Dependencies.Tools.timber)

    // Testing
    testImplementation(Dependencies.Test.junit)
    androidTestImplementation(Dependencies.Test.androidJunit)
    androidTestImplementation(Dependencies.Test.espressoCore)
}
