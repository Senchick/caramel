import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    id("org.jetbrains.compose")
    kotlin("jvm")
    id("io.gitlab.arturbosch.detekt")
    kotlin("kapt")
}

group = "com.caramel"
version = "1.0-SNAPSHOT"

dependencies {
    implementation(project(":domain"))
    implementation(project(":data-desktop"))

    implementation(compose.desktop.currentOs)
    implementation(libs.material3.desktop)

    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    detektPlugins(libs.detekt.rules.compose)
    detektPlugins(libs.detekt.formatting)
    detektPlugins(libs.detekt.hbmartin.rules)
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

detekt {
    parallel = true
    config.setFrom("$projectDir/config/detekt-desktop.yaml")
    buildUponDefaultConfig = false
    allRules = false
    disableDefaultRuleSets = false
    debug = false
    ignoreFailures = false
    basePath = projectDir.absolutePath
}
