package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import base.baseTest;
import java.util.Set;

public class appStoreTest extends baseTest {
    
    @Test
    public void testAppStoreLink() throws InterruptedException {
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
            driver.findElement(By.xpath("//img[@alt='App Store']")).click();
            Reporter.log("App Store Download is clicked<br>", true);
            Thread.sleep(5000);
        } catch (Exception e) {
            Reporter.log("Failed to click App Store link: " + e.getMessage() + "<br>", true);
            softAssert.fail("Exception while clicking the App Store link: " + e.getMessage());
        }

        try {
            String mainWindow = driver.getWindowHandle();
            Set<String> allWindows = driver.getWindowHandles();

            for (String window : allWindows) {
                if (!window.equals(mainWindow)) {
                    driver.switchTo().window(window);
                    Reporter.log("Switched to new window and closing it.<br>", true);
                    driver.close(); 
                    driver.switchTo().window(mainWindow);
                    Reporter.log("Switched back to main window.<br>", true);
                }
            }
        } catch (Exception e) {
            Reporter.log("Failed while handling multiple windows: " + e.getMessage() + "<br>", true);
            softAssert.fail("Exception while handling windows: " + e.getMessage());
        }

        softAssert.assertAll();
    }
}
