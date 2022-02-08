// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val kotlin_version by extra("1.5.20")
    repositories {
        google()
        jcenter { url = java.net.URI.create("http://jcenter.bintray.com/") }
        mavenCentral()

        jcenter()
        maven {
            url = java.net.URI.create("https://jitpack.io")
        } // ANDROID Maven repository (Add this line)
        maven {
            url = java.net.URI.create("https://plugins.gradle.org/m2/")
            url =
                java.net.URI.create("https://developer.huawei.com/repo/") // HUAWEI Maven repository
        }
    }
    dependencies {
        classpath(Classpaths.gradleClasspath)
        classpath(Classpaths.kotlinGradleClasspath)
        classpath(Classpaths.kotlinSerialization)
        classpath(Classpaths.safeVarargs)
        classpath(Classpaths.gradleVersionPlugin)
        classpath(Classpaths.hiltGradleClasspath)
        classpath(Classpaths.huaweiPlugin)
        classpath(Classpaths.googleService)
        classpath(Classpaths.firebaseCrashPlugin)
        classpath(Classpaths.perfPlugin)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter { url = java.net.URI.create("http://jcenter.bintray.com/") }
        jcenter()
        mavenCentral()

        maven { url = java.net.URI.create("https://jitpack.io") }
        maven {
            url = java.net.URI.create("http://maven.google.com/")
            url = java.net.URI.create("https://jitpack.io")
            url = java.net.URI.create("https://jcenter.bintray.com")
        }
        maven {
            url =
                java.net.URI.create("https://developer.huawei.com/repo/") // HUAWEI Maven repository
        }
    }
}

tasks.register("clean").configure {
    delete("build")
}

