package tests;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import base.baseTest;

public class joinOurCommunity extends baseTest {
    
    @Test
    public void communitySection() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Actions actions = new Actions(driver);

        driver.get("https://asianlink.ai/");
        Reporter.log("Asianlink.ai is Launched<br>", true);
        Thread.sleep(2000);
        
        try {
            driver.findElement(By.xpath("//button[contains(text(), 'Accept Necessary')]")).click();
            Reporter.log("Accept Cookies<br>", true);
        } catch (Exception e) {
            Reporter.log("Failed to accept cookies: " + e.getMessage() + "<br>", true);
            softAssert.fail("Exception while accepting cookies: " + e.getMessage());
        }
        Thread.sleep(2000);
        
        try {
            WebElement element = driver.findElement(By.xpath("//h2[normalize-space(text())='Join our Community']"));
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
            Reporter.log("Navigate to Join Our Community<br>", true);
            Thread.sleep(2000);
        } catch (Exception e) {
            Reporter.log("Failed to navigate to Join Our Community: " + e.getMessage() + "<br>", true);
            softAssert.fail("Exception while navigating to Join Our Community: " + e.getMessage());
        }
        
        clickButton("//button[contains(@class, 'text-white') and contains(@class, 'bg-heading') and contains(@class, 'rounded-full')][1]", "left", actions, softAssert);
        clickButton("//button[contains(@class, 'text-white') and contains(@class, 'bg-heading') and contains(@class, 'rounded-full')][2]", "right", actions, softAssert);
        clickButton("//button[contains(@class, 'text-white') and contains(@class, 'bg-heading') and contains(@class, 'rounded-full')][1]", "left", actions, softAssert);
        clickButton("//button[contains(@class, 'text-white') and contains(@class, 'bg-heading') and contains(@class, 'rounded-full')][2]", "right", actions, softAssert);

        softAssert.assertAll();
    }

    private void clickButton(String xpath, String buttonName, Actions actions, SoftAssert softAssert) {
        try {
            WebElement button = driver.findElement(By.xpath(xpath));
            actions.moveToElement(button).click().perform();
            Reporter.log("Click " + buttonName + " button for navigating the comments<br>", true);
            Thread.sleep(2000);
        } catch (Exception e) {
            Reporter.log("Failed to click " + buttonName + " button: " + e.getMessage() + "<br>", true);
            softAssert.fail("Exception while clicking " + buttonName + " button: " + e.getMessage());
        }
    }
}
