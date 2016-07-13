package uk.gov.digital.ho.proving.commongradleplugin

import com.gorylenko.GitPropertiesPlugin
import com.moowork.gradle.gulp.GulpPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ApplicationPlugin
import org.gradle.api.plugins.GroovyPlugin
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.quality.CheckstylePlugin
import org.gradle.api.tasks.testing.Test
import org.gradle.api.tasks.wrapper.Wrapper

/**
 * @Author Home Office Digital
 */
class CommonGradle implements Plugin<Project> {


    @Override
    void apply(Project project) {

        project.extensions.create("commonLibraries", CommonGradlePluginExtension)

        project.with {

            plugins.apply(JavaPlugin)
            plugins.apply(GroovyPlugin)
            plugins.apply(ApplicationPlugin)
            plugins.apply(CheckstylePlugin)
            plugins.apply(GitPropertiesPlugin)

            repositories.jcenter()
            repositories.mavenCentral()
            repositories.mavenLocal()

            setProperty('sourceCompatibility', '1.8')
            setProperty('targetCompatibility', '1.8')

            task('wrapper', type: Wrapper) {
                gradleVersion = '2.13'
            }

            task("pttgCommonGradlePluginUsage", type: UsageTask)

            tasks.withType(Test) {
                reports.html.destination = file("${reporting.baseDir}/${name}")
            }

            processResources {
                filesMatching("**/application.properties") {
                    expand(project.properties)
                }
            }

            plugins.withType(GulpPlugin) {

                build.dependsOn gulp_build, gulp_test

                task("gulpTest") {
                    dependsOn gulp_test
                    group 'verification'
                }
            }

            checkstyle {
                ignoreFailures = true
                config = project.resources.text.fromString("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<!DOCTYPE module PUBLIC\n" +
                    "    \"-//Puppy Crawl//DTD Check Configuration 1.3//EN\" \n" +
                    "     \"http://www.puppycrawl.com/dtds/configuration_1_3.dtd\"> \n" +
                    "<module name=\"Checker\">\n" +
                    "  <module name=\"TreeWalker\">\n" +
                    "    <module name=\"UnusedImports\"/> \n" +
                    "  </module>\n" +
                    "</module>")
            }

            checkstyleTest {
                group 'verification'
            }

            ext.libraries = [

                groovy            : [
                    "org.codehaus.groovy:groovy-all:${project.commonLibraries.groovyVersion}"
                ],

                json              : [
                    "org.json:json:${project.commonLibraries.jsonVersion}"
                ],

                testUtils         : [
                    'com.jayway.jsonpath:json-path:2.2.0',
                    "junit:junit:4.12",
                    "org.mockito:mockito-all:1.10.19:",
                    'org.assertj:assertj-core:3.4.1',
                    'nl.jqno.equalsverifier:equalsverifier:1.7.2'
                ],

                springTest        : [
                    "org.springframework:spring-test:${project.commonLibraries.springVersion}",
                    "org.springframework.boot:spring-boot-starter-test:${project.commonLibraries.springBootVersion}"
                ],

                logging           : [
                    "ch.qos.logback:logback-classic:${project.commonLibraries.logbackVersion}",
                    "ch.qos.logback:logback-core:${project.commonLibraries.logbackVersion}",
                    "org.slf4j:slf4j-parent:1.7.14"
                ],

                jackson           : [
                    "com.fasterxml.jackson.core:jackson-annotations:${project.commonLibraries.jacksonVersion}",
                    "com.fasterxml.jackson.core:jackson-databind:${project.commonLibraries.jacksonVersion}",
                    "com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${project.commonLibraries.jacksonVersion}",
                    "com.fasterxml.jackson.jaxrs:jackson-jaxrs-json-provider:${project.commonLibraries.jacksonVersion}"
                ],

                jersey            : [
                    "com.sun.jersey:jersey-client:1.19",
                    "org.glassfish.jersey.media:jersey-media-json-jackson:2.22.2",
                ],

                mongo             : [
                    "org.mongodb:bson:${project.commonLibraries.mongoVersion}",
                    "org.mongodb:mongodb-driver:${project.commonLibraries.mongoVersion}"
                ],

                springboot        : [
                    "org.springframework.boot:spring-boot:${project.commonLibraries.springBootVersion}",
                    "org.springframework.boot:spring-boot-starter-web:${project.commonLibraries.springBootVersion}"
                ],

                springbootActuator: [
                    "org.springframework.boot:spring-boot-starter-actuator:${project.commonLibraries.springBootVersion}"
                ],

                springrestdocs    : [
                    "org.springframework.restdocs:spring-restdocs-core:${project.commonLibraries.springRestDocsVersion}",
                    "org.springframework.restdocs:spring-restdocs-restassured:${project.commonLibraries.springRestDocsVersion}"
                ],

                restassured       : [
                    "com.jayway.restassured:json-schema-validator:${project.commonLibraries.restAssuredVersion}",
                    "com.jayway.restassured:rest-assured:${project.commonLibraries.restAssuredVersion}"
                ],

                spock             : [
                    "org.spockframework:spock-core:${project.commonLibraries.spockVersion}",
                    "org.spockframework:spock-spring:${project.commonLibraries.spockVersion}",
                    "cglib:cglib-nodep:3.2.1",
                    "org.objenesis:objenesis:2.2"
                ],

                cucumber          : [
                    "info.cukes:cucumber-java:${project.commonLibraries.cucumberVersion}",
                    "info.cukes:cucumber-junit:${project.commonLibraries.cucumberVersion}",
                    "info.cukes:gherkin:2.12.2",
                    "net.serenity-bdd:serenity-core:${project.commonLibraries.serenityVersion}",
                    "net.serenity-bdd:serenity-cucumber:${project.commonLibraries.serenityCucumberVersion}",
                    "net.serenity-bdd:serenity-junit:${project.commonLibraries.serenityVersion}",
                    "net.serenity-bdd:serenity-spring:${project.commonLibraries.serenityVersion}",
                    "org.codehaus.groovy.modules.http-builder:http-builder:0.7.1",
                    "com.github.tomakehurst:wiremock:1.58"
                ]
            ]
        }

    }


}





