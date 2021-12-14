package step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.joda.time.LocalDateTime;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import uk.kainos.seleniumframework.driver.DriverManager;
import uk.kainos.seleniumframework.site.Site;

public class Hooks {

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            DriverManager.getDriver().manage().window().maximize();
            final byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(
                    OutputType.BYTES);
            scenario.attach(screenshot, "image/png",
                    String.format("%s_%s", scenario.getName(), LocalDateTime.now().toString()));
        }
        Site.closeWindow();
        DriverManager.clearDriver();
    }
}
