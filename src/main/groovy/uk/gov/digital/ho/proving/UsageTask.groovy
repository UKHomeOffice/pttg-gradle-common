package uk.gov.digital.ho.proving

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * @Author Home Office Digital
 */
class UsageTask extends DefaultTask {

    String description = "Describe how to use the plugin"
    String group = "help"

    @TaskAction
    def usageReport() {
        println "\n" +
            "************************************ USAGE ********************************************\n" +
            "pttgCommonGradle plugin usage guide\n" +
            "\n" +
            "The following dependency libraries are available and can be added to your dependencies block as eg\n" +
            "    compile libraries.groovy\n" +
            "or\n" +
            "    testCompile libraries.testUtils\n" +
            "\n"

        project.libraries.each { k, v ->
            println "libraries.$k["
            v.each {
                println "    $it"
            }
            println "]"
            println ""
        }

        println "\n" +
            "The following sections list configuration that this plugin applies to your project\n" +
            "\n" +
            "Plugins:\n" +
            "    Java\n" +
            "    Groovy\n" +
            "    Application\n" +
            "    Checkstyle\n" +
            "    Git Properties\n" +
            "\n" +
            "Repositories:\n" +
            "    jcenter\n" +
            "    mavenCentral\n" +
            "    mavenLocal\n" +
            "\n" +
            "Java version\n" +
            "   1.8\n" +
            "\n" +
            "Gradle Wrapper\n" +
            "    version :2.13\n" +
            "    note: run the gradleWrapper task to configure your project\n" +
            "\n" +
            "Property Expansion\n" +
            "    Properties in build.gradle can be referenced with placeholders in application.properties eg \${version}\n" +
            "\n" +
            "Test reports\n" +
            "    Test reports for Test tasks will appear in their own directory following the task name\n" +
            "    eg reports/acceptanceTest for the acceptanceTest task\n" +
            "\n" +
            "Gulp plugin configuration\n" +
            "    If the gulp plugin is present, the gulp_test task will be added to the build task, and\n" +
            "    a gulpTest task will be added to the verification group\n" +
            "\n" +
            "Checkstyle\n" +
            "    Checkstyle is added with a check for unused imports\n" +
            "    You can apply\n" +
            "        checkstyle {\n" +
            "            ignoreFailures = true\n" +
            "        }\n" +
            "    to fail the build when violations occur\n" +
            "\n" +
            "    You can also point to your own checkstyle config file using\n" +
            "        checkstyle {\n" +
            "            configFile = <path-to-checkstyle.xml>\n" +
            "        }\n" +
            "\n" +
            "See full details in the plugin readme in github at https://github.com/UKHomeOffice/pttg-gradle-common"

    }
}
