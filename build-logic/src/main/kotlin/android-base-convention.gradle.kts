import com.android.build.gradle.BaseExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = Settings.JVM_TARGET
}

configure<BaseExtension> {
    compileSdkVersion(Settings.COMPILE_SDK_VERSION)

    defaultConfig {
        minSdk = Settings.MIN_SDK_VERSION
        targetSdk = Settings.TARGET_SDK_VERSION
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}