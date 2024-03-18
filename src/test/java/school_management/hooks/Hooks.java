package school_management.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import school_management.utilities.Driver;

import java.time.Duration;


public class Hooks {

    @Before ("@ui")
    public static void before_ui(){
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Driver.getDriver().manage().window().maximize();
    }

    @After("@ui")
    public void tearDown()  {
        Driver.closeDriver();
    }


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