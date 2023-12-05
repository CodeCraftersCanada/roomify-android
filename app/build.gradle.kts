plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "edu.lambton.roomify"
    compileSdk = 34

    defaultConfig {
        applicationId = "edu.lambton.roomify"
        minSdk = 31
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        viewBinding {
            enable = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        viewBinding = true
    }
    /*dynamicFeatures += setOf(":features:landlord")*/
}

dependencies {
    /*implementation(project(":features:navigation"))
    implementation(project(":features:landlord"))
    implementation(project(":features:student"))
    implementation(project(":features:common:networking"))*/

    /*implementation(platform("com.google.firebase:firebase-bom:32.6.0"))
    implementation("com.google.gms:google-services:4.4.0")
    implementation("com.google.firebase:firebase-common:20.4.2")*/

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.annotation:annotation:1.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    implementation("com.google.firebase:firebase-auth:22.3.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")


    implementation("androidx.navigation:navigation-fragment:2.7.5")
    implementation("androidx.navigation:navigation-ui:2.7.5")

    // Feature module Support
    implementation("androidx.navigation:navigation-dynamic-features-fragment:2.7.5")

    // Testing Navigation
    androidTestImplementation("androidx.navigation:navigation-testing:2.7.5")

    // Jetpack Compose Integration
    implementation("androidx.navigation:navigation-compose:2.7.5")


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}