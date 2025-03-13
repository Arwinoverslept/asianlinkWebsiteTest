package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.testng.Reporter;

import base.baseTest;

public class HowtoGetInvolvedTest extends baseTest {

	@Test
	public void howtogetinvolved() throws InterruptedException {

		Actions actions = new Actions(driver);
		StringBuilder logReport = new StringBuilder();

		driver.get("https://asianlink.ai/");
		Reporter.log("Asianlink.ai is Launched<br>", true);
		Thread.sleep(1000);

		try {
			WebElement element = driver.findElement(By.xpath("//h2[text('How to Get Involved']"));
			((JavascriptExecutor) driver)
					.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
			Reporter.log("How to get Involved is Visible", true);
		} catch (NoSuchElementException e) {
			Reporter.log("How to get Involved is Missing", true);
		}

		Thread.sleep(3000);

	}

}
