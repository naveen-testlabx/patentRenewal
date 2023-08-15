# test-automation-ecohotels

-ea -Dbrowserstack.accesskey=ACCESSKEY -Dbrowserstack.username=USERNAME in the run config


# CucumberBDD
> * This Framework contains cucumber.io and cucumber info cakes pom.xml file.
> * Any JDK or JVM related check under pom.xml or build path to match the system required paths

### Pre-requisites
+ [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
+ [JDK 11 (or higher)](https://www.oracle.com/java/technologies/javase-downloads.html) (even [OpenJDK](https://openjdk.java.net/install/index.html) is fine)
+ [Maven](http://maven.apache.org/)
+ Any IDE of your choice but we **recommend** to use [IntelliJ](https://www.jetbrains.com/idea/) IDE

## Execution: 
**TestNG**:
* Run from `TestRunner.java class`

**Using Maven**:
* Either Configure mvn build in eclipse with:
  * `clean compile test`
* Or Run form command prompt:
  * `mvn clean compile test`

## Initial Set-up:


- Run Mvn Clean command once the above step is completed to download the core-framework dependency <br>
- Please Note: The version might differ <br>
  ![img.png](docs/images/img_7.png) <br>
  ![img.png](docs/images/img_8.png) <br>

## TestScript Creation
* The first step in the Scenario in any feature file must be Given "When the user spins the "mobiledevice" device, please refer to any sample feature file <br>
  ![img.png](docs/images/img_9.png) <br>
* The parameter "mobiledevice" in the gherkin step is Parameterized and it acts as a KEY, the value must be given in the "Examples" as part of the Test Data <br>
  ![img.png](docs/images/img_10.png) <br>
* Allowed list of values in "mobiledevice" parameter is as "deviceOne", "deviceTwo", "deviceThree", "deviceFour", "deviceFive" <br>
* All the Mobile Device configuration is maintained inside the file named as "config.properties" and the file is present in src/main/java/resources package, the inputs for the parameters present in the config.properties must be taken from the [BrowserStack](https://www.browserstack.com/list-of-browsers-and-platforms/app_automate) website<br>
  ![img.png](docs/images/img_11.png) <br>
* The next set of gherkin keywords can be build on top of the application feature / functionality and the Test Data must be provided in the Examples itself <br>


## Test Results
* The framework is capable of generating multiple reports such as Cucumber HTML Reports, Extent HTML Report, Extent PDF Report <br>
* The recommended report to refer is Extent HTML Report / Extent PDF Report, once the test execution is completed the test results can be seen in the package target/testoutput/HtmlReport and target/testout/PdfReport <br>
  ![img.png](docs/images/img_12.png) <br>
* In case the test's are running inside the Pipeline then the Extent PDF Report is attached to the Test Runs in the Azure Pipeline, Runs
  ![img.png](docs/images/img_13.png) <br>
## Further Documentation help:
* [ExtentReport-Plugin](https://github.com/grasshopper7/extentreports-cucumber6-adapter) for further queries on configuration.
* [ExtentReport- Older plugins](https://www.extentreports.com/docs/versions/5/java/plugins.html) for different versions of plugin's.



## Features:
* Cucumber reporting
* Extent HTML Reporting
* Extent Spark Reporting
* Extent PDF Reporting
* Cucumber latest jars (v6)
* Extent Reports (v5)
* Screenshots logging for cucumber reports 
