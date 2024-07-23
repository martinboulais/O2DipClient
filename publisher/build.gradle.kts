plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(files("../libs/jdip-6.2.0.jar"))
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "o2.dip.App"
}

tasks.named<JavaExec>("run") {
    doFirst {
        environment("LD_LIBRARY_PATH", rootDir.path + "/libs")
    }
}