package tests;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Test;
import base.baseTest;
import java.util.Set;

public class appStoreTest extends baseTest {

    @Test
    public void testAppStoreLink() {
        boolean testFailed = false;
        StringBuilder logReport = new StringBuilder();

        log("Asianlink.ai is Launched", logReport);
        driver.get("https://asianlink.ai/");

        try {
            driver.findElement(By.xpath("//button[contains(text(), 'Accept Necessary')]")).click();
            log("Accept Cookies", logReport);
            Thread.sleep(2000);
        } catch (Exception e) {
            log("FAILED: Could not accept cookies - " + e.getMessage(), logReport);
            testFailed = true;
        }

        try {
            driver.findElement(By.xpath("//imlt='App Store']")).click();
            log("App Store Download is clicked", logReport);
            Thread.sleep(5000);
        } catch (Exception e) {
            log("FAILED: Could not click App Store link - " + e.getMessage(), logReport);
            testFailed = true;
        }

        try {
            String mainWindow = driver.getWindowHandle();
            Set<String> allWindows = driver.getWindowHandles();

            for (String window : allWindows) {
                if (!window.equals(mainWindow)) {
                    driver.switchTo().window(window);
                    log("Switched to new window and closing it.", logReport);
                    driver.close();
                    driver.switchTo().window(mainWindow);
                    log("Switched back to main window.", logReport);
                }
            }
        } catch (Exception e) {
            log("FAILED: Could not handle multiple windows - " + e.getMessage(), logReport);
            testFailed = true;
        }

        // Final status message (without "Final Status") ++ Edit mamaya para makita yung report logs
        if (testFailed) {
            log("Status: FAILED", logReport);
            assert false : logReport.toString();
        } else {
            log("Status: PASSED", logReport);
        }

        // 1 Print per report
        Reporter.log("<br>" + logReport.toString().replace("\n", "<br>"), true);
    }

    // Utility method to log messages in StringBuilder only  
    private void log(String message, StringBuilder logReport) {
        logReport.append("- ").append(message).append("\n");
    }
}
