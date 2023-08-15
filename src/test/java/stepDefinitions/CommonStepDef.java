package stepDefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import io.cucumber.java.en.*;
import utils.DriverInstance;


public class CommonStepDef {

    DriverInstance driverInstance;

    public CommonStepDef() {
        driverInstance = new DriverInstance();
    }

    @Given("the user spins up the {string} browser")
    public void theUserSpinsUpTheBrowser(String browserInfo) {
        driverInstance.setUpBrowserOnAnyGivenDevice(browserInfo);
      ExtentCucumberAdapter.addTestStepLog("Driver Instance has been successfully Launched" );
    }
}
