package school_management.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import school_management.utilities.Driver;



public class Hooks {


    @After
    public void tearDown(Scenario scenario){
//        System.out.println("After hooks");
        if (scenario.isFailed()){
            byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot of failed step");
            Driver.closeDriver();
        }

    }


}
