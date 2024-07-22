val kotlinVersion: String by project
val logbackVersion: String by project
val exposedVersion: String by project
val h2Version: String by project
val ktorVersion: String by project

plugins {
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.serialization") version "2.0.0"
    id("io.ktor.plugin") version "2.3.12"
}

group = "com.lowbudgetlcs"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("com.h2database:h2:$h2Version")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-server-config-yaml:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("com.rabbitmq:amqp-client:5.21.0")
    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
}
