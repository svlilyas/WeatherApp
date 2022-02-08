import Dependencies.Project.data
import Dependencies.Project.routeAndroid
import Dependencies.Project.routeCore

plugins {
    // Application Specific Plugins
    id(Plugins.androidApplication)
    kotlin(Plugins.android)
    id(Plugins.safeargs)
    kotlin(Plugins.kapt)
    kotlin(Plugins.androidExtensions)
    id(Plugins.daggerHiltPlugin)
    id(Plugins.crashlytics)
    id(Plugins.googleService)
    id(Plugins.perfPlugin)
}
androidExtensions {
    isExperimental = true
}
android {
    compileSdkVersion(Configs.compileSdkVersion)
    buildToolsVersion = Configs.buildToolsVersion

    ndkVersion = Configs.ndkVersions

    defaultConfig {
        applicationId = Configs.applicationId
        minSdkVersion(Configs.minSdkVersion)
        targetSdkVersion(Configs.targetSdkVersion)

        versionCode = Configs.versionCode
        versionName = Configs.versionName
        multiDexEnabled = true
        testInstrumentationRunner = Configs.testInstrumentationRunner
    }

    lintOptions {
        this.isAbortOnError = true
        this.isCheckReleaseBuilds = true
        this.disable("NullSafeMutableLiveData")
    }
    signingConfigs {
        /*register(Flavors.BuildTypes.RELEASE) {
            storeFile = file(KeyStore.storeFilePath)
            storePassword = KeyStore.storePassword
            keyAlias = KeyStore.keyAlias
            keyPassword = KeyStore.keyPassword
        }*/
    }

    buildTypes {

        getByName(Flavors.BuildTypes.DEBUG) {
            isTestCoverageEnabled = true
            isMinifyEnabled = false
            isDebuggable = true
            // applicationIdSuffix = ".${Flavors.BuildTypes.DEBUG}"
            //signingConfig = signingConfigs.getByName(Flavors.BuildTypes.RELEASE)
        }

        getByName(Flavors.BuildTypes.RELEASE) {
            isMinifyEnabled = true
            isDebuggable = false
            // Enables resource shrinking, which is performed by the
            // Android Gradle plugin.
            isShrinkResources = true
            defaultPublishConfig = name
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            the<com.google.firebase.crashlytics.buildtools.gradle.CrashlyticsExtension>().mappingFileUploadEnabled =
                false

            // firebaseCrashlytics.mappingFileUploadEnabled = true

            //signingConfig = signingConfigs.getByName(Flavors.BuildTypes.RELEASE)
        }
    }

    flavorDimensions(Flavors.FlavorDimensions.ENVIRONMENT)
    productFlavors {

        // dev
        create(Flavors.ProductFlavors.DEV) {
            dimension = Flavors.FlavorDimensions.ENVIRONMENT
            // applicationIdSuffix = ".${Flavors.ProductFlavors.DEV}"
            // versionNameSuffix = "-${Flavors.ProductFlavors.DEV}"

            resValue("string", "app_label_name", "WeatherApp")

            // BuildConfigField
            stringField(Field.SERVICE_URL to "http://13.73.160.196:84")
            stringField(Field.SERVICE_PUBLIC_KEY to "")
            stringField(Field.SERVICE_CERTIFICATE_PATH to "")
            stringField(Field.FIREBASE_APP_INTEGRATION_KEY to "AIzaSyDLjJTmGJaVT0ADDPv30f3bmET6S9JWcS8")
        }

        // uat
        create(Flavors.ProductFlavors.UAT) {
            dimension = Flavors.FlavorDimensions.ENVIRONMENT
            // applicationIdSuffix = ".${Flavors.ProductFlavors.UAT}"
            // versionNameSuffix = "-${Flavors.ProductFlavors.UAT}"
            resValue("string", "app_label_name", "WeatherApp")
            // BuildConfigField
            stringField(Field.SERVICE_URL to "http://13.73.160.196:84")
            stringField(Field.SERVICE_PUBLIC_KEY to "")
            stringField(Field.SERVICE_CERTIFICATE_PATH to "")
            stringField(Field.FIREBASE_APP_INTEGRATION_KEY to "AIzaSyDLjJTmGJaVT0ADDPv30f3bmET6S9JWcS8")
        }

        // pilot
        create(Flavors.ProductFlavors.PILOT) {
            dimension = Flavors.FlavorDimensions.ENVIRONMENT
            //   applicationIdSuffix = ".${Flavors.ProductFlavors.PILOT}"
            // versionNameSuffix = "-${Flavors.ProductFlavors.PILOT}"
            resValue("string", "app_label_name", "WeatherApp")

            // BuildConfigField
            stringField(Field.SERVICE_URL to "http://13.73.160.196:84")
            stringField(Field.SERVICE_PUBLIC_KEY to "")
            stringField(Field.SERVICE_CERTIFICATE_PATH to "")
            stringField(Field.FIREBASE_APP_INTEGRATION_KEY to "AIzaSyDLjJTmGJaVT0ADDPv30f3bmET6S9JWcS8")
        }

        // store
        create(Flavors.ProductFlavors.STORE) {
            dimension = Flavors.FlavorDimensions.ENVIRONMENT
            applicationIdSuffix = ""
            versionNameSuffix = ""
            resValue("string", "app_label_name", "WeatherApp$versionNameSuffix")

            // BuildConfigField
            stringField(Field.SERVICE_URL to "http://13.73.160.196:84")
            stringField(Field.SERVICE_PUBLIC_KEY to "")
            stringField(Field.FIREBASE_APP_INTEGRATION_KEY to "AIzaSyDLjJTmGJaVT0ADDPv30f3bmET6S9JWcS8")
        }
    }

    sourceSets {
        getByName(Flavors.Default.MAIN) {
            java.srcDir("src/main/kotlin")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    packagingOptions {
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/LICENSE")
        exclude("META-INF/LICENSE.txt")
        exclude("META-INF/license.txt")
        exclude("META-INF/NOTICE")
        exclude("META-INF/NOTICE.txt")
        exclude("META-INF/notice.txt")
        exclude("META-INF/ASL2.0")
        exclude("META-INF/*.kotlin_module")
    }
}
dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))
    // Data Module
    implementation(data())
    implementation(routeAndroid())
    implementation(routeCore())
    implementation("me.zhanghai.android.materialprogressbar:library:1.6.1")
    implementation("io.coil-kt:coil:1.4.0")
    implementation("com.github.hkk595:Resizer:v1.5")
    // Kotlin
    implementation(Dependencies.Kotlin.kotlinStdLib)
    implementation(Dependencies.Kotlin.kotlinCoroutinesCore)
    implementation(Dependencies.Kotlin.kotlinCoroutinesAndroid)

    // Android
    implementation(Dependencies.Android.androidCore)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.legacySupport)
    implementation(Dependencies.Android.multidex)
    implementation(Dependencies.Android.materialDesign)
    implementation(Dependencies.Android.fragment)
    implementation(Dependencies.Android.constraintLayout)
    implementation(Dependencies.Android.recyclerView)
    implementation(Dependencies.Android.recyclerViewSelection)
    implementation(Dependencies.Android.cardView)
    implementation(Dependencies.Android.palette)
    implementation(Dependencies.Android.workManger)

    // Navigation
    implementation(Dependencies.Navigation.runTimeNavigation)
    implementation(Dependencies.Navigation.navigationFragment)
    implementation(Dependencies.Navigation.navigationUi)

    // LifeCycle
    implementation(Dependencies.LifeCycle.runTimeLiveCycle)
    implementation(Dependencies.LifeCycle.lifeCycleCompiler)
    implementation(Dependencies.LifeCycle.liveData)
    implementation(Dependencies.LifeCycle.viewModel)
    implementation(Dependencies.LifeCycle.viewModelState)

    // DI
    implementation(Dependencies.DI.hilt)
    implementation(Dependencies.DI.hiltWork)
    implementation(Dependencies.DI.hiltNavigation)
    implementation("androidx.recyclerview:recyclerview-selection:1.1.0")
    //implementation(Dependencies.DI.hiltViewModel)
    kapt(Dependencies.DI.hiltCompiler)
    kapt(Dependencies.DI.hiltWorkManagerCompiler)
    annotationProcessor(Dependencies.DI.hiltWorkManagerCompiler)

    // ReactiveFunc
    implementation(Dependencies.ReactiveFunc.rxJava)
    implementation(Dependencies.ReactiveFunc.rxKotlin)
    implementation(Dependencies.ReactiveFunc.rxAndroid)
    implementation(Dependencies.Network.scalar)
    implementation(Dependencies.Network.gson)

    // AppCenter
    implementation(Dependencies.AppCenter.crashes)
    implementation(Dependencies.AppCenter.analytics)
    // Shimmer Layout
    implementation(Dependencies.Tools.shimmerLayout)

    // Timber
    implementation(Dependencies.Tools.timber)

    // Material SearchBar
    implementation(Dependencies.Tools.materialSearchBar)

    // ZoomageView
    implementation(Dependencies.Tools.zoomageView)

    // ExoPlayer
    implementation(Dependencies.Tools.exoPlayer)
    // ExoPlayerUI
    implementation(Dependencies.Tools.exoPlayerUi)
    // PierFrancescoSoffritti Youtube Player
    implementation("com.pierfrancescosoffritti.androidyoutubeplayer:core:10.0.5")

    //PickImage Library
    implementation("com.github.jrvansuita:PickImage:2.5.4")

    // balloon
    implementation(Dependencies.Tools.balloon)
    // rangeSeekBar
    implementation(Dependencies.Tools.rangeSeekBar)

    // EventBus
    implementation(Dependencies.Tools.eventbus)

    // Lottie (Animation)
    implementation(Dependencies.Tools.lottie)

    // DrawabloToolbox
    implementation(Dependencies.Tools.drawableToolbox)
/*
    // SingleRow Calendar
    implementation(Dependencies.Tools.singleRowCalendar)*/

    // RoundedImageView
    implementation(Dependencies.Tools.roundedImageView)

    // QR Code Generator
    //implementation(Dependencies.Tools.qrCodeGenerator)
    implementation("androidmads.library.qrgenearator:QRGenearator:1.0.3")
    //implementation("com.google.zxing:core:3.3.2")

    //CamView
    //implementation(Dependencies.Tools.camView) { isTransitive = true }
    implementation("eu.livotov.labs.android:CAMView:2.0.1@aar") { isTransitive }
    // LinkBuilder
    implementation(Dependencies.Tools.linkBuilder)

    // ViewPager2
    implementation(Dependencies.Tools.viewPager2)

    // WarmDotsIndicator
    implementation(Dependencies.Tools.dotsIndicator)

    // Dengage
    implementation(Dependencies.Dengage.dengage)

    // Gson
    implementation(Dependencies.Tools.gson)

    // Glide
    implementation(Dependencies.Tools.glide)
    implementation(Dependencies.Tools.glideOkHttpIntegration)
    kapt(Dependencies.Tools.glideCompiler)

    // KeyboardListener
    implementation(Dependencies.Tools.keyboardListener)

    // Huawei
    implementation(Dependencies.Huawei.huaweiLocation)
    implementation(Dependencies.Huawei.huaweiMaps)
    implementation(Dependencies.Huawei.hwid)
    implementation(Dependencies.Huawei.huaweiPush)
    implementation(Dependencies.Huawei.adsIdentifier)

    // Google
    implementation(Dependencies.Google.playSevicesMap)
    implementation(Dependencies.Google.mapUtils)
    implementation(Dependencies.Google.googleLocation)
    implementation(Dependencies.Google.googlePlaces)
    implementation(Dependencies.Google.authApiPhone)
    implementation(Dependencies.Google.tagManager)
    implementation(Dependencies.Google.analytics)
    implementation(Dependencies.Google.servicesAuth)

    // Adjust
    implementation(Dependencies.Adjust.adjustAndroid)
    implementation(Dependencies.Adjust.identifier)
    implementation(Dependencies.Adjust.installreferrer)
    implementation(Dependencies.Adjust.webbridge)

    // Firebase
    implementation(platform(Dependencies.Firebase.firebaseBom))
    //implementation(Dependencies.Firebase.firebaseAnalysis)
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation(Dependencies.Firebase.firebaseAnalysisktx)
    implementation(Dependencies.Firebase.crashlytics)
    implementation(Dependencies.Firebase.commonKtx)
    implementation(Dependencies.Firebase.perf)
    implementation(Dependencies.Firebase.firebaseCore)
    implementation(Dependencies.Firebase.firebaseAnalytics)
    implementation(Dependencies.Firebase.firebaseIID)
    implementation(Dependencies.Firebase.firebaseMessaging)
    implementation("com.google.firebase:firebase-database-ktx")

    // Dialog
    implementation(Dependencies.MaterialDialog.core)
    implementation(Dependencies.MaterialDialog.datetime)
    implementation(Dependencies.MaterialDialog.input)
    implementation(Dependencies.MaterialDialog.lifecycle)

    // Testing
    testImplementation(Dependencies.Test.junit)
    testImplementation(Dependencies.Test.truthExt)
    testImplementation(Dependencies.Test.mockK)
    testImplementation(Dependencies.Test.coreTesting)
    androidTestImplementation(Dependencies.Test.androidJunit)
    androidTestImplementation(Dependencies.Test.espressoCore)
}

fun com.android.build.gradle.internal.dsl.ProductFlavor.stringField(entry: Pair<String, String>) {
    buildConfigField("String", entry.first, "\"${entry.second}\"")
}

fun com.android.build.gradle.internal.dsl.ProductFlavor.manifestHolders(entry: Pair<String, String>) {
    val map = HashMap<String, String>()
    map[entry.first] = entry.second
    manifestPlaceholders(map)
}
