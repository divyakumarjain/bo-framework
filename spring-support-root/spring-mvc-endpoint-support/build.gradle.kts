/*
 * This file was generated by the Gradle 'init' task.
 *
 * This project uses @Incubating APIs which are subject to change.
 */

plugins {
    id("org.divy.java-conventions")
}

dependencies {
    implementation("com.fasterxml.jackson.module:jackson-module-parameter-names:2.14.0-rc3")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.14.0-rc3")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.14.0-rc3")
    implementation(project(":bo-framework-spring-support-core"))
    implementation(project(":bo-framework-dynamic"))
    implementation(project(":bo-framework-repository"))
    implementation(project(":bo-framework-metadata"))
    implementation(project(":bo-framework-businessapi"))
    implementation(project(":bo-framework-endpoint-core"))
    implementation("org.springframework:spring-context:6.0.0-RC4")
    implementation("org.springframework:spring-beans:6.0.0-RC4")
    implementation("org.springframework:spring-web:6.0.0-RC4")
    implementation("org.springframework.hateoas:spring-hateoas:2.0.0-RC2")
    implementation("org.springframework.boot:spring-boot-autoconfigure:3.0.0-RC2")
    implementation("org.springframework.boot:spring-boot-configuration-processor:3.0.0-RC2")
    implementation("jakarta.inject:jakarta.inject-api:2.0.1")
    compileOnly("jakarta.servlet:jakarta.servlet-api:6.0.0")
}

description = "spring-mvc-endpoint-support"
