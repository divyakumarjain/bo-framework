/*
 * This file was generated by the Gradle 'init' task.
 *
 * This project uses @Incubating APIs which are subject to change.
 */

plugins {
    id("org.divy.java-conventions")
}

dependencies {
    implementation(project(":bo-framework-validation-core"))
    implementation(project(":bo-framework-repository"))
    implementation("jakarta.validation:jakarta.validation-api:3.0.2")
}

description = "jsr303-validation"
