package tests;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import base.baseTest;

public class joinOurCommunity extends baseTest {

    @Test
    public void communitySection() throws InterruptedException {
        boolean testFailed = false; 
        Actions actions = new Actions(driver);

        driver.get("https://asianlink.ai/");
        Reporter.log("Asianlink.ai is Launched<br>", true);
        Thread.sleep(2000);

        try {
            driver.findElement(By.xpath("//button[contains(text(), 'Accept Necessary')]")).click();
            Reporter.log("Accept Cookies<br>", true);
        } catch (Exception e) {
            Reporter.log("FAILED: Could not accept cookies<br>", true);
            testFailed = true;
        }
        Thread.sleep(2000);

        try {
            WebElement element = driver.findElement(By.xpath("//h2[normalize-space(text())='Join our Community']"));
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
            Reporter.log("Navigate to Join Our Community<br>", true);
            Thread.sleep(2000);
        } catch (Exception e) {
            Reporter.log("FAILED: Could not navigate to Join Our Community<br>", true);
            testFailed = true;
        }

        testFailed |= clickButton("//button[contains(@class, 'text-white') and contains(@class, 'bg-heading') and contains(@class, 'rounded-full')][1]", "left", actions);
        testFailed |= clickButton("//button[contains(@class, 'text-white') and contains(@class, 'bg-heading') and contains(@class, 'rounded-full')][2]", "right", actions);
        testFailed |= clickButton("//button[contains(@class, 'text-white') and contains(@class, 'bg-heading') and contains(@class, 'rounded-full')][1]", "left", actions);
        testFailed |= clickButton("//button[contains(@class, 'text-white') and contains(@class, 'bg-heading') and contains(@class, 'rounded-full')][2]", "right", actions);

        
        if (testFailed) {
            Reporter.log("Status: FAILED<br>", true);
            assert false;
        } else {
            Reporter.log("Status: PASSED<br>", true);
        }
        
        
    }

    private boolean clickButton(String xpath, String buttonName, Actions actions) {
        try {
            WebElement button = driver.findElement(By.xpath(xpath));
            actions.moveToElement(button).click().perform();
            Reporter.log("Click " + buttonName + " button for navigating the comments<br>", true);
            Thread.sleep(2000);
            return false;
        } catch (Exception e) {
            Reporter.log("FAILED: Could not click " + buttonName + " button<br>", true);
            return true;
        }
    }
}
