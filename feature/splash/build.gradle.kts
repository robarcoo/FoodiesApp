plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "com.example.splash"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

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
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }

}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.compose.ui:ui-android:1.6.4")
    implementation("androidx.navigation:navigation-runtime-ktx:2.7.7")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.0-alpha03")
    kapt("androidx.lifecycle:lifecycle-compiler:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.8.0-alpha03")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.0-alpha03")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.0-alpha03")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0-alpha03")
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation("com.airbnb.android:lottie:5.2.0")
    implementation("com.airbnb.android:lottie-compose:5.2.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
}