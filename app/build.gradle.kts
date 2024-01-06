plugins {
    id("com.android.application")
    id("com.google.gms.google-services") //FireBase
}

android {
    namespace = "oleg.trushanin.graz"
    compileSdk = 34

    defaultConfig {
        applicationId = "oleg.trushanin.graz"
        minSdk = 24
        targetSdk = 33
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
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    dependencies {

        implementation("androidx.appcompat:appcompat:1.6.1")
        implementation("com.google.android.material:material:1.10.0")
        implementation("androidx.constraintlayout:constraintlayout:2.1.4")
        implementation("androidx.activity:activity:1.8.1") // нужно чтобы работало переопределение кнопки бэк
        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.1.5")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

        implementation("com.squareup.retrofit2:retrofit:2.9.0") // для запросов в сеть
        implementation("com.squareup.retrofit2:converter-gson:2.7.0") // для работы с JSON

        implementation("io.reactivex.rxjava3:rxandroid:3.0.2") // зависимость для RxJava
        implementation("io.reactivex.rxjava3:rxjava:3.1.5")// зависимость для RxJava
        implementation("com.squareup.retrofit2:adapter-rxjava3:2.9.0") // связываем ретрофит и javaRx

        val room_version = "2.6.0"

        implementation("androidx.room:room-runtime:$room_version") // работа с базой данных
        annotationProcessor("androidx.room:room-compiler:$room_version") // работа с базой данных
        implementation("androidx.room:room-rxjava3:$room_version") // работа с базой данных
        implementation("com.github.bumptech.glide:glide:4.16.0") // работа с изображениями
        implementation("com.squareup.okhttp3:logging-interceptor:4.9.0") // чтобы принимался самоподписанный сертификат SSL
        implementation("com.google.android.material:material:1.10.0") // библиотека картинок и для современного свича
        implementation(platform("com.google.firebase:firebase-bom:32.7.0")) //FireBase
        implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
        implementation("com.google.firebase:firebase-auth")
        implementation("com.google.firebase:firebase-database")
        androidTestImplementation("com.itextpdf:itextg:5.5.10") //Itext
        implementation("com.itextpdf:itext7-core:7.2.3")
        implementation ("commons-io:commons-io:2.6")
        implementation ("androidx.security:security-crypto:1.1.0-alpha06") // инкрипт шаред преференс
        implementation ("com.github.barteksc:android-pdf-viewer:2.8.2") // отображение ПДФ
        implementation ("com.google.android.material:material:1.11.0") // ползунок для аванса
    }
}





dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
}