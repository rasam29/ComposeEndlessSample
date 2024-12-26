plugins {
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.secrets.gradle)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.aparat.androidinterview"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.aparat.androidinterview"
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
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.paging.compose.android)
    implementation(libs.androidx.lifecycle.runtime.compose.android)
    with(libs) {
        implementation(androidx.ui)
        implementation(androidx.core.ktx)
        implementation(androidx.material3)
        implementation(compose.navigation)
        implementation(androidx.ui.graphics)
        implementation(androidx.activity.compose)
        implementation(androidx.ui.tooling.preview)
        implementation(androidx.lifecycle.runtime.ktx)
        implementation(platform(androidx.compose.bom))
        kapt(hilt.compiler)
        implementation(coil)
        implementation(moshi)
        implementation("com.squareup.moshi:moshi-adapters:1.8.0")
        implementation(timber)
        implementation(hilt.compose)
        implementation(hilt.android)
        implementation(bundles.arrow)
        implementation(bundles.okhttp)
        implementation(bundles.retrofit)
        implementation(kotlin.immutable)
        debugImplementation(androidx.ui.tooling)
        debugImplementation(androidx.ui.test.manifest)
        testImplementation(junit)
        androidTestImplementation(androidx.junit)
        androidTestImplementation(androidx.espresso.core)
        androidTestImplementation(androidx.ui.test.junit4)
        androidTestImplementation(platform(androidx.compose.bom))
    }
}
