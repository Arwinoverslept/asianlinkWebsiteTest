package tests;

import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import base.baseTest;

public class aboutPage extends baseTest {

    @Test
    public void aboutsPageTest() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Actions actions = new Actions(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.get("https://asianlink.ai/");
        Reporter.log("Asianlink.ai is Launched<br>", true);
        Thread.sleep(3000);
        
        try {
            driver.findElement(By.xpath("//button[contains(text(), 'Accept Necessary')]")).click();
            Reporter.log("Accept Cookies<br>", true);
            Thread.sleep(2000);
        } catch (Exception e) {
            Reporter.log("Failed to accept cookies: " + e.getMessage() + "<br>", true);
            softAssert.fail("Exception while accepting cookies: " + e.getMessage());
        }

        try {
            driver.findElement(By.xpath("(//a[@href='/#about'])[1]")).click();
            Reporter.log("Clicked About on the Navigation Bar<br>", true);
            Thread.sleep(3000);
        } catch (Exception e) {
            Reporter.log("Failed to click About on the Navigation Bar: " + e.getMessage() + "<br>", true);
            softAssert.fail("Exception while clicking About on the Navigation Bar: " + e.getMessage());
        }

        List<String> xpaths = Arrays.asList(
            "//img[@alt='Kevin D Keng']", "//img[@alt='Jose Czarauz Parra']", "//img[@alt='Cesar De Guzman']",
            "//img[@alt='Maria Melanie De Leon']", "//img[@alt='Dexter Ganibe']", "//img[@alt='Erick Dela Rosa']",
            "//img[@alt='Raquel Mallannao']", "//img[@alt='Jason Isaac Benitez']"
        );

        for (String xpath : xpaths) {
            try {
                List<WebElement> elements = driver.findElements(By.xpath(xpath));

                if (!elements.isEmpty()) {
                    WebElement element = elements.get(0);
                    String altText = element.getAttribute("alt"); 
                    
                    js.executeScript(
                        "window.scrollTo({top: arguments[0].getBoundingClientRect().top + window.scrollY - 100, behavior: 'smooth'});",
                        element
                    );
                    Thread.sleep(2000);

                    actions.moveToElement(element).perform();
                    Thread.sleep(3000);

                    Reporter.log("<br>Hovered on: " + altText, true); 
                } else {
                    Reporter.log("<br>Element not found for alt: " + xpath, true);
                    softAssert.fail("Element not found for alt: " + xpath);
                }
            } catch (Exception e) {
                Reporter.log("Error interacting with element: <br>" + xpath + " - " + e.getMessage(), true);
                softAssert.fail("Exception while interacting with element: " + xpath + " - " + e.getMessage());
            }
        }
        softAssert.assertAll();
    }
}
