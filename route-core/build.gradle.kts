@file:Suppress("UNUSED_VARIABLE")


plugins {
    kotlin("multiplatform")
    `maven-publish`
}

group = "market-turkey"
version = "0.0.2"

kotlin {
    jvm("jvm")

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Dependencies.Kotlin.StdLib.common)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(Dependencies.Kotlin.Test.common)
                implementation(Dependencies.Kotlin.Test.annotations)
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(Dependencies.Kotlin.StdLib.jdk)
            }
        }

        val jvmTest by getting {
            dependsOn(commonTest)
            dependencies {
                implementation(Dependencies.Kotlin.Test.junit)
            }
        }
    }
}

