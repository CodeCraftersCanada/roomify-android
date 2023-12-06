buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.0")

        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.5")
    }
    repositories {
        google()
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("com.android.dynamic-feature") version "8.2.0" apply false
    id("com.android.library") version "8.2.0" apply false
    id("com.google.gms.google-services") version "4.4.0" apply false
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1" apply false

}