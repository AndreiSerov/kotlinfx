import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.3"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.openjfx.javafxplugin") version ("0.0.13")

    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
//    implementation("org.liquibase:liquibase-core")
//    implementation("org.jetbrains.exposed:exposed-core:0.41.1")
//    implementation("org.jetbrains.exposed:exposed-spring-boot-starter:0.41.1")
    implementation("org.openjfx:javafx-controls:19.0.2.1")
//    implementation("org.openjfx:javafx-base:19.0.2.1")
//    implementation("org.openjfx:javafx-fxml:19.0.2.1")
//    compileOnly("org.openjfx:javafx-fxml:19.0.2.1")
//    implementation("org.openjfx:javafx:19.0.2.1")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

javafx {
    version = "19"
    modules = listOf("javafx.controls", "javafx.fxml")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
