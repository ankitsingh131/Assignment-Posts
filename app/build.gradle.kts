plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.assignment"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.assignment"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        resourceConfigurations += listOf("en", "ar")
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
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
        buildConfig = true
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {

    //Version catalog like implementation.
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.logging.interceptor)
    implementation(libs.converter.gson)
    implementation(libs.adapter.rxjava3)
    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.dagger)
    implementation(libs.dagger.android)
    implementation(libs.dagger.android.support)
    implementation(libs.bindingcollectionadapter.ktx)
    implementation(libs.bindingcollectionadapter.recyclerview)
    implementation(libs.bindingcollectionadapter.viewpager2)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.mockito.kotlin)
    kapt(libs.dagger.android.processor)
    kapt(libs.dagger.compiler)
    implementation(libs.rxjava)
    implementation(libs.rxandroid)
    implementation(libs.glide)
    kapt(libs.glide.compiler)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockwebserver)
    androidTestImplementation(libs.mockito.android)
    testImplementation(libs.mockito.inline)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}