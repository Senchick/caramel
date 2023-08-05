import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    id("org.jetbrains.compose") version "1.5.0-beta01"
    kotlin("jvm")
    id("com.google.devtools.ksp") version "1.9.0-1.0.13"
}

group = "com.caramel"
version = "1.0-SNAPSHOT"

dependencies {
    implementation(compose.desktop.currentOs)
    implementation("org.jetbrains.compose.material3:material3-desktop:1.5.0-beta01")

    implementation(project(":domain"))
    implementation(project(":data-desktop"))

    implementation("io.insert-koin:koin-core:3.4.3")
    implementation("io.insert-koin:koin-annotations:3.4.3")
    implementation("io.insert-koin:koin-compose:1.0.4")
    ksp("io.insert-koin:koin-ksp-compiler:3.4.3")
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "desktop"
            packageVersion = "1.0.0"
        }
    }
}

sourceSets.main {
    java.srcDirs("build/generated/ksp/main/kotlin")
}
