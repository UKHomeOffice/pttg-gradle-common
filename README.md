## Gradle plugin that applies PTTG team standards and conventions.

### Use this plugin in your gradle build by:

1. Adding a buildscript dependency

```
buildscript {
       repositories{
            maven { url "https://github.com/UKHomeOffice/pttg-gradle-repo/raw/master/releases" }
            maven { url "https://plugins.gradle.org/m2/" }
       }
       dependencies {
           classpath 'pttg-gradle-common:pttgCommonGradle:1.2.RELEASE'
       }
}
```

2. Applying the plugin

```
apply plugin: 'pttgCommonGradle'
```

3. User guide
See the following documentation. In your project you can also execute the 'pttgCommonGradlePluginUsage' task to
see usage instructions and version information etc.


### What this plugin gives your build automatically

1. Applies our commonly used plugins 
 - java
 - groovy
 - application
 - checkstyle
 - git properties

2. Applies the standard repositories that we use

3. Sets our standard gradle / gradle wrapper version

4. Sets our standard Java version

5. Applies our convention for test reporting: All tasks with a type of 'Test' will generate report HTML into 
build/reporting/task-name, eg build/reporting/acceptanceTest or build/reporting/test or build/reporting/myCustomTest

6. Includes commonly used dependencies at our standard versions, eg
 - junit
 - mockito
 - groovy
 - json
 
7. Applies property expansion to application.properties so that you can use eg ${version} to receive the version
property from build.gradle
 
8. If the project uses the 'com.moowork.gulp' plugin, then the build is changed to depned on the gulp_test task, and 
the gulp_test task is added to the verification group
 
9. Adds checkstyleTest task to the verification group, and adds Checkstyle to the 'check' task, 
configured to report on the following errors (but not fail the build):
 - Unused imports
  
  NB You can choose to have Checkstyle violations fail the build by adding this configuration:
  
  ```
  checkstyle {
    ignoreFailures = true
  }
  ```
  NB You can use your own Checkstyle rules using the following configuration to point to your checkstyle.xml
  
  ```
  checkstyle {
    configFile = <path-to-checkstyle.xml>
  }
  ```
 
### What this plugin allows you to use in your build

1. The plugin creates libraries of dependency groupings at our standard versions

eg the plugin defines a standard dependency grouping for Cucumber BDD support, including the following dependencies
```
"info.cukes:cucumber-java:$cucumberVersion",
"info.cukes:cucumber-junit:$cucumberVersion",
"info.cukes:gherkin:2.12.2",
"net.serenity-bdd:serenity-core:$serenityVersion",
"net.serenity-bdd:serenity-cucumber:$serenityCucumberVersion",
"net.serenity-bdd:serenity-junit:$serenityVersion"
```
You can use this library in your build as follows:

```
dependencies{
    testCompile libraries.cucumber
}
```

The following libraries are defined:

- groovy
- json
- testUtils
- logging
- jackson
- jacksonJsonProviders
- mongo    
- springboot 
- springbootActuator
- springrestdocs
- restassured 
- spock    
- cucumber

### Deploying this plugin to the GitHub gradle repository

1. Set the version number in build.gradle (and update this readme)
2. Execute the tasks clean, build, publishToGitHub
3. IntelliJ will ask you for your github login the first time

This relies on the [gradle-git-repo-plugin](https://github.com/layerhq/gradle-git-repo-plugin)
