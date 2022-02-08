object Configs {
    const val applicationId = "com.mobilion.weatherapp"
    const val minSdkVersion = 23
    const val targetSdkVersion = 30
    const val compileSdkVersion = 30
    const val buildToolsVersion = "30.0.3"
    const val ndkVersions = "23.0.7599858"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val versionCode = 3
    val versionName = calculateVersionName()
    private const val versionMajor = 3
    private const val versionMinor = 0
    private const val versionPatch = 1

    private fun calculateVersionName(): String = "$versionMajor.$versionMinor.$versionPatch"
}
