package tests;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Test;
import base.baseTest;

public class playStoreTest extends baseTest {

    @Test
    public void testPlayStoreLink() throws InterruptedException {
        boolean testFailed = false; // Track failure status

        driver.get("https://asianlink.ai/");
        Reporter.log("Asianlink.ai is Launched<br>", true);

        try {
            driver.findElement(By.xpath("//button[contains(text(), 'Accept Necessary')]")).click();
            Reporter.log("Accept Cookies<br>", true);
            Thread.sleep(2000);
        } catch (Exception e) {
            Reporter.log("FAILED: Could not accept cookies<br>", true);
            testFailed = true;
        }

        try {
            driver.findElement(By.xpath("//img[@alt='Play Store']")).click();
            Reporter.log("Play Store Download is clicked<br>", true);
            Thread.sleep(5000);
        } catch (Exception e) {
            Reporter.log("FAILED: Could not click Play Store link<br>", true);
            testFailed = true;
        }

        // Log final test status
        if (testFailed) {
            Reporter.log("Status: FAILED<br>", true);
            assert false; // Force test failure without printing stack trace
        } else {
            Reporter.log("Status: PASSED<br>", true);
        }
    }
}
