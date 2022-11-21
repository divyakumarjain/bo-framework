/*
 * This file was generated by the Gradle 'init' task.
 *
 * This project uses @Incubating APIs which are subject to change.
 */

plugins {
    id("org.divy.java-conventions")
    id("org.gradlex.extra-java-module-info") version "1.1"
}

extraJavaModuleInfo {
    failOnMissingModuleInfo.set(true)
    module("ma.glasnost.orika:orika-core", "orika.core") {

        exports("ma.glasnost.orika")
    }
}

dependencies {
    implementation(project(":bo-framework-core-mapper"))
    implementation("ma.glasnost.orika:orika-core:1.5.4")
    implementation("org.javassist:javassist:3.29.2-GA")
    implementation(project(":bo-framework-repository"))
    implementation(project(":bo-framework-metadata"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.1")
    testImplementation("org.mockito:mockito-core:4.9.0")
    testImplementation("org.hamcrest:hamcrest:2.2")
}

description = "orika-mapper"
