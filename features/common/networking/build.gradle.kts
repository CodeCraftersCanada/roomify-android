import com.android.build.api.dsl.Packaging

plugins {
    id("com.android.library")

}

android {
    namespace = "edu.lambton.networking"
    compileSdk = 34

    defaultConfig {
        minSdk = 31

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        create("viewBinding") {
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")


    /*implementation("com.google.firebase:firebase-common:20.4.2")*/
    // Firebase
    /*implementation(platform("com.google.firebase:firebase-bom:32.6.0"))*/
    /*implementation("com.google.firebase:firebase-auth:22.3.0")*/
    /*

    implementation("com.google.firebase:firebase-analytics:21.5.0")
    implementation("com.google.firebase:firebase-perf:20.5.1")
    implementation("com.google.firebase:firebase-storage:20.3.0")
    implementation("com.google.firebase:firebase-firestore:24.9.1")
    implementation("com.google.firebase:firebase-messaging:23.3.1")
*/



    // Testing
    testImplementation("junit:junit:4.13.2")


}