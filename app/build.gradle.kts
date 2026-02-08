plugins {
    application
    java
    id("checkstyle")
}

repositories {
    mavenCentral()
}

checkstyle {
    toolVersion = "11.0.0"
}

val javaFxVersion = "17.0.7"

dependencies {
    // JavaFX (multi-platform)
    implementation("org.openjfx:javafx-base:$javaFxVersion:win")
    implementation("org.openjfx:javafx-base:$javaFxVersion:mac")
    implementation("org.openjfx:javafx-base:$javaFxVersion:linux")
    implementation("org.openjfx:javafx-controls:$javaFxVersion:win")
    implementation("org.openjfx:javafx-controls:$javaFxVersion:mac")
    implementation("org.openjfx:javafx-controls:$javaFxVersion:linux")
    implementation("org.openjfx:javafx-fxml:$javaFxVersion:win")
    implementation("org.openjfx:javafx-fxml:$javaFxVersion:mac")
    implementation("org.openjfx:javafx-fxml:$javaFxVersion:linux")
    implementation("org.openjfx:javafx-graphics:$javaFxVersion:win")
    implementation("org.openjfx:javafx-graphics:$javaFxVersion:mac")
    implementation("org.openjfx:javafx-graphics:$javaFxVersion:linux")

    // JUnit 5 for tests
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

application {
    mainClass.set("richal.Richal")
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

tasks.named<Jar>("jar") {
    manifest {
        attributes["Main-Class"] = "richal.Richal"
    }
}
