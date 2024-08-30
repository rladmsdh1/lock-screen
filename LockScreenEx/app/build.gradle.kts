plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlinx-serialization")
}

android {
    namespace = "com.example.lockscreenex"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.lockscreenex"
        minSdk = 24
        targetSdk = 35
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
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.accompanist.permissions)

    //Ktor
    implementation("io.ktor:ktor-client-android:2.3.4")

    //JVM, Android 및 Native 플랫폼에서 사용할 수 있는 완전 비동기식 코루틴 기반 엔진
    implementation("io.ktor:ktor-client-cio:2.3.4")

    //HTTP Request을 로깅하기 위해 사용
    implementation("io.ktor:ktor-client-logging-jvm:2.3.4")

    //직렬화/역직렬화를 위한 ContentNegotiation
    implementation("io.ktor:ktor-client-content-negotiation:2.3.4")

    //Serialization json
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.4")
}