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

        }

    }


}





