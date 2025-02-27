package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import base.baseTest;
import java.util.Set;


public class appStoreTest extends baseTest{
	
	@Test
	 public void testAppStoreLink() throws InterruptedException {
	        driver.get("https://asianlink.ai/");
	        driver.findElement(By.xpath("//img[@alt='App Store']")).click();
	        Thread.sleep(5000);

	        String mainWindow = driver.getWindowHandle();
	        Set<String> allWindows = driver.getWindowHandles();

	        for (String window : allWindows) {
	            if (!window.equals(mainWindow)) {
	                driver.switchTo().window(window);
	                driver.close(); 
	                driver.switchTo().window(mainWindow);
	            }
	        }
	    }

}
