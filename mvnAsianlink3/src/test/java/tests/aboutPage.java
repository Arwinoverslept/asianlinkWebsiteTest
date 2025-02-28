package tests;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import base.baseTest;

public class aboutPage extends baseTest {

	@Test
	public void aboutsPageTest() throws InterruptedException {
		driver = new ChromeDriver();
		Actions actions = new Actions(driver);
		
		driver.get("https://asianlink.ai/");
		driver.findElement(By.xpath("(//a[@href='/#about'])[1]")).click();
		Thread.sleep(3000);
		
        WebElement elementToHover = driver.findElement(By.xpath("//img[@alt='Kevin D Keng']"));
        actions.moveToElement(elementToHover).perform();
        Thread.sleep(3000);
        
        List<String> xpaths = Arrays.asList(
                "//img[@alt='Kevin D Keng']",
                "//img[@alt='Jose Czarauz Parra']",
                "//img[@alt='Cesar De Guzman']",
                "//img[@alt='Maria Melanie De Leon']",
                "//img[@alt='Dexter Ganibe']",
                "//img[@alt='Erick Dela Rosa']",
                "//img[@alt='Raquel Mallannao']",
                "//img[@alt='Jason Isaac Benitez']"
            );

            for (String xpath : xpaths) {
                WebElement element = driver.findElement(By.xpath(xpath));
                actions.moveToElement(element).perform();
                Thread.sleep(3000);
            }
		
	}

}
