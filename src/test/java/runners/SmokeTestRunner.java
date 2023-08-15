package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * An example of using TestNG when the test class does not inherit from
 * AbstractTestNGCucumberTests but still executes each scenario as a separate
 * TestNG test.
 * , "me.jvt.cucumber.report.PrettyReports:target/android/cucumber-html-reports"}
 */
@CucumberOptions(
        plugin = {"pretty"
                , "html:target/android/cucumber"
                , "summary"
                , "de.monochromata.cucumber.report.PrettyReports:target/android/cucumber-html-reports",
                "junit:target/cucumber-reports/cucumber-junit.xml",
                "json:target/json-report/cucumber.json",
                "json:target/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
        , features = {
        "src/test/resources/functionalTests"
},
        glue = {
                "stepDefinitions"
        }
        , dryRun = false
        , monochrome = true
        , tags = "@smoke"
)
public class SmokeTestRunner extends AbstractTestNGCucumberTests {

    /**
     * Scenarios object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}