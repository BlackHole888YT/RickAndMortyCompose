plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)

}

android {
    namespace = "com.example.rickandmortycompose"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.rickandmortycompose"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            buildConfigField("String","BASE_URL", "\"https://rickandmortyapi.com/api/\"")
        }
        release {
            buildConfigField("String","BASE_URL", "\"https://rickandmortyapi.com/api/\"")

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
        buildConfig = true
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
    //Live data
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    //Converter-gson
    implementation(libs.converter.gson)

    //Google Gson
    implementation (libs.gson)

    //OkHttp
    // define a BOM and its version
    implementation(platform(libs.okhttp.bom))
    // define any required OkHttp artifacts without version
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    //Retrofit
    implementation (libs.retrofit)

    //Koin https://mvnrepository.com/artifact/io.insert-koin/
    //koin-compose-viewmodel
    runtimeOnly(libs.koin.compose.viewmodel)
    //koin-androidx-compose-navigation
    implementation(libs.koin.androidx.compose.navigation)
    //koin-androidx-compose
    runtimeOnly(libs.koin.androidx.compose)
    //koin-android
    implementation(libs.koin.android)

    //Serialization
    implementation(libs.kotlinx.serialization.json)

    // Навигация в Jetpack Compose
    implementation(libs.androidx.navigation.compose)

    // Other
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation("io.coil-kt:coil-compose:2.5.0")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}