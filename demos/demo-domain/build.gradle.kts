/*
 * This file was generated by the Gradle 'init' task.
 *
 * This project uses @Incubating APIs which are subject to change.
 */

plugins {
    id("org.divy.java-conventions")
}

dependencies {
    implementation(project(":bo-framework-database-jpa"))
    implementation(project(":bo-framework-database-core"))
}

description = "demo-domain"
