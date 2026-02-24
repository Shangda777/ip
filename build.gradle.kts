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
    implementation("dev.langchain4j:langchain4j-google-ai-gemini:1.0.0-beta1")
    implementation("dev.langchain4j:langchain4j:1.0.0-beta1")


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
    mainClass.set(project.findProperty("mainClass") as String? ?: "richal.Main")
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
    System.getenv("LLM_API_KEY")?.let { environment("LLM_API_KEY", it) }
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

tasks.named<Jar>("jar") {
    manifest {
        attributes["Main-Class"] = "richal.Main"
    }
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}

