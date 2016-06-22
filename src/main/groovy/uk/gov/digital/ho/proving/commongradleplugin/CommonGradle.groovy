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


            ext {
                cucumberVersion = '1.2.4'
                groovyVersion = '2.4.3'
                jsonVersion = '20160212'
                jacksonVersion = '2.7.4'
                logbackVersion = '1.1.3'
                mongoVersion = '3.0.4'
                restAssuredVersion = '2.9.0'
                serenityCucumberVersion = '1.1.6'
                serenityVersion = '1.1.31'
                spockVersion = '1.0-groovy-2.4'
                springBootVersion = '1.3.3.RELEASE'
                springRestDocsVersion = '1.1.0.RC1'
                springVersion = '4.2.5.RELEASE'
            }

            ext.libraries = [

                groovy              : [
                    "org.codehaus.groovy:groovy-all:$groovyVersion"
                ],

                json                : [
                    "org.json:json:$jsonVersion"
                ],

                testUtils           : [
                    'com.jayway.jsonpath:json-path:2.2.0',
                    "org.springframework:spring-test:$springVersion",
                    "junit:junit:4.12",
                    "org.mockito:mockito-all:1.10.19:",
                    'org.assertj:assertj-core:3.4.1',
                    'nl.jqno.equalsverifier:equalsverifier:1.7.2'
                ],

                logging             : [
                    "ch.qos.logback:logback-classic:$logbackVersion",
                    "ch.qos.logback:logback-core:$logbackVersion",
                    "org.slf4j:slf4j-parent:1.7.14"
                ],

                jackson             : [
                    "com.fasterxml.jackson.core:jackson-annotations:$jacksonVersion",
                    "com.fasterxml.jackson.core:jackson-databind:$jacksonVersion"
                ],

                jacksonJsonProviders: [
                    "com.sun.jersey:jersey-client:1.19",
                    "org.glassfish.jersey.media:jersey-media-json-jackson:2.22.2",
                    "com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion"
                ],

                mongo               : [
                    "org.mongodb:bson:$mongoVersion",
                    "org.mongodb:mongodb-driver:$mongoVersion"
                ],

                springboot          : [
                    "org.springframework.boot:spring-boot:$springBootVersion",
                    "org.springframework.boot:spring-boot-starter-web:$springBootVersion"
                ],

                springbootActuator  : [
                    "org.springframework.boot:spring-boot-starter-actuator:$springBootVersion"
                ],

                springrestdocs      : [
                    "org.springframework.restdocs:spring-restdocs-core:$springRestDocsVersion",
                    "org.springframework.restdocs:spring-restdocs-restassured:$springRestDocsVersion"
                ],

                restassured         : [
                    "com.jayway.restassured:json-schema-validator:$restAssuredVersion",
                    "com.jayway.restassured:rest-assured:$restAssuredVersion"
                ],

                spock               : [
                    "org.spockframework:spock-core:$spockVersion",
                    "org.spockframework:spock-spring:$spockVersion"
                ],

                cucumber            : [
                    "info.cukes:cucumber-java:$cucumberVersion",
                    "info.cukes:cucumber-junit:$cucumberVersion",
                    "info.cukes:gherkin:2.12.2",
                    "net.serenity-bdd:serenity-core:$serenityVersion",
                    "net.serenity-bdd:serenity-cucumber:$serenityCucumberVersion",
                    "net.serenity-bdd:serenity-junit:$serenityVersion",
                    "org.codehaus.groovy.modules.http-builder:http-builder:0.7.1"
                ]
            ]
        }

    }


}




