object Dependencies {
    const val androidxCoreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val junit = "junit:junit:${Versions.junit}"
    const val androidxJunit = "androidx.test.ext:junit:${Versions.junitVersion}"
    const val androidxEspressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val androidxLifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeKtx}"
    const val androidxActivityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val androidxComposeBom = "androidx.compose:compose-bom:${Versions.composeBom}"
    const val androidxUi = "androidx.compose.ui:ui"
    const val androidxUiGraphics = "androidx.compose.ui:ui-graphics"
    const val androidxUiTooling = "androidx.compose.ui:ui-tooling"
    const val androidxUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    const val androidxUiTestManifest = "androidx.compose.ui:ui-test-manifest"
    const val androidxUiTestJUnit4 = "androidx.compose.ui:ui-test-junit4"
    const val androidxMaterial3 = "androidx.compose.material3:material3"
    const val coroutines= "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val hilt="com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltKapt = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationCompose}"
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.lifecycle_version}"
    const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle_version}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_version}"
    const val savedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle_version}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout-compose:${Versions.constraintlayout}"
    const val room = "androidx.room:room-runtime:${Versions.room_version}"
    const val roomKtx= "androidx.room:room-ktx:${Versions.room_version}"
    const val roomPaging = "androidx.room:room-paging:${Versions.room_version}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room_version}"
    const val navigation = "androidx.navigation:navigation-compose:${Versions.nav_version}"

    // Retrofit & Moshi
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofit_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val retrofit_mock = "com.squareup.retrofit2:retrofit-mock:${Versions.retrofit}"
    const val retrofit_moshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val moshi_kotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"

    // okhttp
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttp_logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val okhttp_mock_web_server = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"

    // coil
    const val coil = "io.coil-kt:coil-compose:${Versions.coil}"

    // paging
    const val paging_compose = "androidx.paging:paging-compose:${Versions.pagin}"
    const val paging_runtime = "androidx.paging:paging-runtime-ktx:${Versions.pagin}"

    // datastore
    const val datastore = "androidx.datastore:datastore:${Versions.dataStore}"
    const val datastore_core = "androidx.datastore:datastore-core:${Versions.dataStore}"
    const val datastore_preferences = "androidx.datastore:datastore-preferences:${Versions.dataStore}"

    // Test Dependencies
    const val hamcrest = "org.hamcrest:hamcrest-all:${Versions.hamcrest}"
    const val arch_test = "android.arch.core:core-testing:${Versions.arch_core_testing}"
    const val mockito_core = "org.mockito:mockito-core:${Versions.mockito}"
    const val mockito_inline = "org.mockito:mockito-inline:${Versions.mockito}"
    const val mockito_android = "org.mockito:mockito-android:${Versions.mockito_android}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val turb = "app.cash.turbine:turbine:${Versions.turbine}"
    const val test_core = "androidx.test:core:${Versions.android_test_core}"
    const val compose_test_junit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    const val coro_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines_test}"


}