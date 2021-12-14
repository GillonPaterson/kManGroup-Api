package step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import uk.kainos.seleniumframework.driver.DriverManager;

import static org.aspectj.bridge.Version.getText;

public class jobRolesStepDefinitions {
    private final String WEB_PAGE = "localhost:3000/jobRoles";
    protected WebDriver driver = DriverManager.getDriver();

    @Given("the client is on the job roles webpage")
    public jobRolesStepDefinitions theClientOpensHomePage(){
        driver.navigate().to(WEB_PAGE);
        return this;

    }

    @Then("the client should see employee <ID> and their <Job Role>")
    public jobRolesStepDefinitions theClientSeeIdAndJobRoles(string ID,string jobRoles){
        String id = driver.findElement(By.className("a-price-whole")).getText();
        String job-role = driver.findElement(By.className("a-price-whole")).getText();
        if (id = ID and jobRoles == job-role){
            return this;
        } else {
            return null;
        }

    }



}