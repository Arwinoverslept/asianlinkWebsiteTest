package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import base.baseTest;

public class playStoreTest extends baseTest{
    @Test
    public void testPlayStoreLink() throws InterruptedException {
        driver.get("https://asianlink.ai/");
        
        driver.findElement(By.xpath("//img[@alt='Play Store']")).click();
        Thread.sleep(5000);
    }

}
