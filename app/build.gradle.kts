plugins {
    id(Plugins.androidApplication) version Versions.agp
    id(Plugins.kotlinAndroid) version Versions.kotlin
    id(Plugins.kotlinCompose) version Versions.kotlin
    id(Plugins.hiltGradlePlugin)
    id(Plugins.kotlinKapt)
    id(Plugins.kotlinSerialization) version Versions.kotlin_serialization
}

android {
    namespace = "com.example.btccompose"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.btccompose"
        minSdk = 24
        targetSdk = 35
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

        debug {
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
        }

    }
    flavorDimensions += "Btc"

    productFlavors {

        create("btcDev") {
            dimension = "Btc"
            applicationIdSuffix = ".btcDev"
            versionNameSuffix = "-btcDev"
            buildConfigField("String", "GRAPH_BASE_URL", "\"https://graph-api.btcturk.com/\"")
            buildConfigField("String", "BASE_URL", "\"https://api.btcturk.com/api/\"")
        }
        create("btcTest") {
            dimension = "Btc"
            applicationIdSuffix = ".btcTest"
            versionNameSuffix = "-btcTest"
            buildConfigField("String", "GRAPH_BASE_URL", "\"https://graph-api.btcturk.com/\"")
            buildConfigField("String", "BASE_URL", "\"https://api.btcturk.com/api/\"")
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

}

dependencies {

    implementation(Dependencies.androidxCoreKtx)
    implementation(Dependencies.androidxLifecycleRuntimeKtx)
    implementation(Dependencies.androidxActivityCompose)
    implementation(platform(Dependencies.androidxComposeBom))
    implementation(Dependencies.androidxUi)
    implementation(Dependencies.androidxUiGraphics)
    implementation(Dependencies.androidxUiToolingPreview)
    implementation(Dependencies.androidxMaterial3)

    implementation(Dependencies.lifecycle)
    implementation(Dependencies.viewmodel)
    implementation(Dependencies.liveData)
    implementation(Dependencies.savedState)

    implementation(Dependencies.room)
    implementation(Dependencies.roomKtx)
    implementation(Dependencies.roomPaging)
    implementation(libs.play.services.base)
    kapt(Dependencies.roomCompiler)

    implementation(Dependencies.coroutines)
    implementation(Dependencies.hilt)
    implementation(Dependencies.hiltNavigationCompose)
    kapt(Dependencies.hiltKapt)
    implementation(Dependencies.constraintlayout)

    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofit_gson)
    implementation(Dependencies.retrofit_moshi)
    implementation(Dependencies.moshi_kotlin)
    implementation(Dependencies.okhttp)
    implementation(Dependencies.okhttp_logging)
    implementation(Dependencies.okhttp_mock_web_server)
    implementation(Dependencies.coil)
    implementation(Dependencies.paging_compose)
    implementation(Dependencies.paging_runtime)
    implementation(Dependencies.datastore)
    implementation(Dependencies.datastore_core)
    implementation(Dependencies.datastore_preferences)
    implementation(Dependencies.navigation)
    implementation(libs.accompanist.swiperefresh)
    implementation(libs.kotlinx.serialization.json)


    // Debug
    debugImplementation(Dependencies.androidxUiTooling)
    debugImplementation(Dependencies.androidxUiTestManifest)


    // Testing
    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.androidxJunit)
    androidTestImplementation(Dependencies.androidxEspressoCore)
    androidTestImplementation(platform(Dependencies.androidxComposeBom))
    androidTestImplementation(Dependencies.androidxUiTestJUnit4)
    androidTestImplementation(Dependencies.mockito_android)
    androidTestImplementation(Dependencies.arch_test)
    androidTestImplementation(Dependencies.turb)
    androidTestImplementation(Dependencies.compose_test_junit)
    testImplementation(Dependencies.mockito_core)
    testImplementation(Dependencies.mockito_inline)
    testImplementation(Dependencies.mockk)
    testImplementation(Dependencies.hamcrest)
    testImplementation(Dependencies.coro_test)
    testImplementation(Dependencies.test_core)

}

kapt {
    correctErrorTypes= true
}

