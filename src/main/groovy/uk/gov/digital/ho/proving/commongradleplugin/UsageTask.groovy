package uk.gov.digital.ho.proving.commongradleplugin

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * @Author Home Office Digital
 */
class UsageTask extends DefaultTask {

    String description = "Describe how to use the plugin"
    String group = "help"

    @TaskAction
    def commonGradleUsageReport() {
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


        try {
            def usage = this.getClass().getResource('/usage/common-plugin-usage.txt').text
            println usage
        } catch (Exception e) {
            println "Error loading usage report"
            e.printStackTrace()
        }
    }
}
