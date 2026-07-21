plugins {
    id("com.android.library") version "8.2.0"
    id("maven-publish")
}

android {
    namespace = "com.slipstream"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

dependencies {
    compileOnly("org.firstinspires.ftc:RobotCore:11.0.0")
    compileOnly("org.firstinspires.ftc:Hardware:11.0.0")
    compileOnly("org.firstinspires.ftc:FtcCommon:11.0.0")

    compileOnly("com.pedropathing:ftc:2.1.2")
    compileOnly("com.pedropathing:telemetry:1.0.0")

    compileOnly("com.bylazar:fullpanels:1.0.12")
}

group = "com.github.helotyr"
version = "1.0.0"

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])

                groupId = "com.github.helotyr"
                artifactId = "Slipstream"
                version = "1.0.0"
            }
        }
    }
}