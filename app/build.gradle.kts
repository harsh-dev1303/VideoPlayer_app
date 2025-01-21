plugins {
  /*  alias(libs.plugins.android-application)
    alias(libs.plugins.kotlin-android)*/
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp") version "2.0.21-1.0.27"
    id("com.google.dagger.hilt.android")
    kotlin("plugin.serialization") version "2.0.21"   //serialization
    // Existing plugins
    alias(libs.plugins.compose.compiler)


}

android {
    namespace = "com.example.videoplayer"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.videoplayer"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
   /* implementation(libs.androidx-core-ktx)
    implementation(libs.androidx.lifecycle-runtime-ktx)*/
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.0")// Or your preferred version (1.9.0 in this case)
    implementation("org.jetbrains.kotlin:kotlin-stdlib-common:1.9.0") // Ensure consistency

    //for navigation between screens
    val nav_version = "2.8.5"

    implementation("androidx.navigation:navigation-compose:$nav_version")

    //serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")

    implementation("androidx.compose.material:material-icons-extended-android:1.7.6")

    implementation("com.google.dagger:hilt-android:2.51.1")
    ksp("com.google.dagger:hilt-android-compiler:2.51.1")

    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    implementation("com.google.accompanist:accompanist-permissions:0.36.0")

    //exoplayer
    implementation("androidx.media3:media3-exoplayer:1.3.1")
    implementation("androidx.media3:media3-exoplayer-dash:1.3.1")
    implementation("androidx.media3:media3-ui:1.3.1")


}