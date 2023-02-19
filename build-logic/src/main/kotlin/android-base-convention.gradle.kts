import com.android.build.gradle.BaseExtension
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = Settings.JVM_TARGET
}

tasks.withType<Test> {
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
        events = setOf(
            TestLogEvent.STARTED,
            TestLogEvent.SKIPPED,
            TestLogEvent.PASSED,
            TestLogEvent.FAILED,
        )
        showStandardStreams = true
    }
}

configure<BaseExtension> {
    compileSdkVersion(Settings.COMPILE_SDK_VERSION)

    defaultConfig {
        minSdk = Settings.MIN_SDK_VERSION
        targetSdk = Settings.TARGET_SDK_VERSION
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}