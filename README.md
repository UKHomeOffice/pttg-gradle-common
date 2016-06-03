## Gradle plugin that applies PTTG team standards and conventions.

### Use this plugin in your gradle build by:

1. Adding a buildscript dependency

```
buildscript {
       repositories{
            maven { url "https://github.com/UKHomeOffice/pttg-gradle-repo/raw/master/releases" }
       }
       dependencies {
           classpath 'pttg-gradle-common:pttgCommonGradle:1.0.RELEASE'
       }
}
```

2. Applying the plugin

```
apply plugin: 'pttgCommonGradle'
```


### What this plugin gives your build automatically

1. Applies our commonly used plugins 
 - java
 - groovy
 - application

2. Applies the standard repositories that we use

3. Sets our standard gradle / gradle wrapper version

4. Sets our standard Java version

5. Applies our convention for test reporting: All tasks with a type of 'Test' will generate report HTML into build/reporting/task-name, eg build/reporting/acceptanceTest or build/reporting/test or build/reporting/myCustomTest

6. Includes commonly used dependencies at our standard versions, eg
 - junit
 - mockito
 - groovy
 - json
 
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

- logging
- jackson
- mongo    
- springboot 
- springbootDev (developer tools for spring boot)
- springrestdocs (API documentation support)
- restassured 
- spock    
- cucumber
