package tests;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import base.baseTest;

public class playStoreTest extends baseTest {

    @Test
    public void testPlayStoreLink() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        
        driver.get("https://asianlink.ai/");
        Reporter.log("Asianlink.ai is Launched<br>", true);

        try {
            driver.findElement(By.xpath("//button[contains(text(), 'Accept Necessary')]")).click();
            Reporter.log("Accept Cookies<br>", true);
            Thread.sleep(2000);
        } catch (Exception e) {
            Reporter.log("Failed to accept cookies: " + e.getMessage() + "<br>", true);
            softAssert.fail("Exception while accepting cookies: " + e.getMessage());
        }

        try {
            driver.findElement(By.xpath("//img[@alt='Play Store']")).click();
            Reporter.log("Play Store Download is clicked<br>", true);
            Thread.sleep(5000);
        } catch (Exception e) {
            Reporter.log("Failed to click Play Store link: " + e.getMessage() + "<br>", true);
            softAssert.fail("Exception while clicking Play Store link: " + e.getMessage());
        }

        softAssert.assertAll();
    }
}
