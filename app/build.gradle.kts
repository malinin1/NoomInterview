plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.noominterview"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.noominterview"
        minSdk = 24
        targetSdk = 34
        compileSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
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
}

dependencies {

    implementation("com.airbnb.android:lottie:6.0.1")

    implementation("androidx.core:core-ktx:1.10.1")
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.recyclerview:recyclerview:1.3.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.6.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("com.google.dagger:dagger:2.46.1")
    kapt("com.google.dagger:dagger-compiler:2.46.1")

    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:adapter-rxjava3:2.9.0")

    implementation("com.github.bumptech.glide:glide:4.15.1")

    implementation("io.reactivex.rxjava3:rxandroid:3.0.2")
    implementation("io.reactivex.rxjava3:rxjava:3.1.6")
    implementation("io.reactivex.rxjava3:rxkotlin:3.0.1")

    implementation("androidx.constraintlayout:constraintlayout:2.2.0-alpha12")

    implementation("com.google.code.gson:gson:2.10.1")

    implementation("androidx.room:room-runtime:2.5.2")
    annotationProcessor("androidx.room:room-compiler:2.5.2")
    implementation("androidx.room:room-rxjava3:2.5.2")
    testImplementation("androidx.room:room-testing:2.5.2")

    kapt("androidx.room:room-compiler:2.5.2")

    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("androidx.datastore:datastore-preferences-core:1.0.0")
    implementation("androidx.datastore:datastore-preferences-rxjava3:1.0.0")

    implementation("com.jakewharton.rxbinding4:rxbinding:4.0.0")
    implementation("com.jakewharton.rxbinding4:rxbinding-core:4.0.0")
    implementation("com.jakewharton.rxbinding4:rxbinding-appcompat:4.0.0")
    implementation("com.jakewharton.rxbinding4:rxbinding-drawerlayout:4.0.0")
    implementation("com.jakewharton.rxbinding4:rxbinding-leanback:4.0.0")
    implementation("com.jakewharton.rxbinding4:rxbinding-recyclerview:4.0.0")
    implementation("com.jakewharton.rxbinding4:rxbinding-slidingpanelayout:4.0.0")
    implementation("com.jakewharton.rxbinding4:rxbinding-swiperefreshlayout:4.0.0")
    implementation("com.jakewharton.rxbinding4:rxbinding-viewpager:4.0.0")
    implementation("com.jakewharton.rxbinding4:rxbinding-viewpager2:4.0.0")

    testImplementation("org.mockito.kotlin:mockito-kotlin:5.1.0")

}