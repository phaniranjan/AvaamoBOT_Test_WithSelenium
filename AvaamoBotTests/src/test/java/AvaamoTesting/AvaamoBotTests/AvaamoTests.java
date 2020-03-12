package AvaamoTesting.AvaamoBotTests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AvaamoTests {

	WebDriver driver = null;
	WebElement avaamoBot = null;

	@Test(priority = 1, enabled = true)
	public void VerifyAvaamoChatBot() throws InterruptedException {

		avaamoBot = driver.findElement(By.name("avaamoIframe"));
		System.out.println("Avaamo frame class :" + avaamoBot.getAttribute("name"));

	}

	@Test(priority = 2, enabled = true)
	public void VerifyTextInputAndResponseMessage() throws InterruptedException {
		switchToAvaamoBotFrame();

		System.out.println("Enter and send test bot option");
		driver.findElement(By.xpath("//*[@id=\"messageEditor-region\"]/div/div[3]/div[2]/textarea"))
				.sendKeys("Hello Avaamo");
		driver.findElement(By.className("Send")).click();

	}

	@Test(enabled = true)
	public void VerifyMainMenuOptions() throws InterruptedException {
		switchToAvaamoBotFrame();

		driver.findElement(By.xpath("//*[@id=\"messageEditor-region\"]/div/div[3]/div[1]")).click();

		String menuOption = driver.findElement(By.xpath("//*[@id=\"messageEditor-region\"]/div/div[4]/div[2]/div/a[1]"))
				.getText();

		Assert.assertEquals(menuOption, "Find a Doctor");

		menuOption = driver.findElement(By.xpath("//*[@id=\"messageEditor-region\"]/div/div[4]/div[2]/div/a[3]"))
				.getText();

		Assert.assertEquals(menuOption, "Find a Service");

	}

	@Test(enabled = true)
	public void VerifyQuickReplyAndSelectIndia() throws InterruptedException {
		switchToAvaamoBotFrame();

		addTestBotTextAndSend();

		System.out.println("Clicking Quick Reply Input option");
		Thread.sleep(4000); // To be deleted
		driver.findElement(By.xpath("//a[contains(text(),'Quick Reply')]")).click();

		System.out.println("Selecting India option from the container");
		Thread.sleep(4000); // To be deleted
		driver.findElement(By.xpath("//*[@id=\"quick-reply-container\"]/div/a[22]")).click();

	}

	@Test(enabled = true)
	public void VerifyCarouselAndClickJamesLink() throws InterruptedException {

		switchToAvaamoBotFrame();

		addTestBotTextAndSend();

		System.out.println("Clicking Carousel Input option");
		Thread.sleep(4000); // To be deleted
		driver.findElement(By.xpath("//a[contains(text(),'Carousel')]")).click();

	}

	@Test(enabled = true)
	public void VerifyWebViewAndClickAvaamo() throws InterruptedException {

		switchToAvaamoBotFrame();

		addTestBotTextAndSend();

		System.out.println("Clicking Web View Option");
		Thread.sleep(4000); // To be deleted
		driver.findElement(By.xpath("//a[contains(text(),'Web View')]")).click();

		Thread.sleep(4000); // To be deleted
		System.out.println("Clicking Avaamo link");
		driver.findElement(By.xpath("//a[contains(text(),'Avaamo')]")).click();
	}

	@Test(enabled = true)
	public void VerifyWebViewAndClickLocation() throws InterruptedException {

		switchToAvaamoBotFrame();

		addTestBotTextAndSend();

		System.out.println("Clicking Web View Option");
		Thread.sleep(4000); // To be deleted
		driver.findElement(By.xpath("//a[contains(text(),'Web View')]")).click();

		Thread.sleep(4000); // To be deleted
		System.out.println("Clicking Avaamo link");
		driver.findElement(By.xpath("//a[text()='Location']")).click();
	}

	@Test(enabled = true)
	public void VerifyWebViewAndClickCall() throws InterruptedException {

		switchToAvaamoBotFrame();

		addTestBotTextAndSend();

		System.out.println("Clicking Web View Option");
		Thread.sleep(4000); // To be deleted
		driver.findElement(By.xpath("//a[contains(text(),'Web View')]")).click();

		Thread.sleep(4000); // To be deleted
		System.out.println("Clicking Avaamo link");
		driver.findElement(By.xpath("//a[contains(text(),'Call')]")).click();
	}

	@Test(enabled = true)
	public void VerifyAllInputFillFormAndSubmit() throws InterruptedException {

		switchToAvaamoBotFrame();

		addTestBotTextAndSend();

		System.out.println("Clicking All Input option");
		Thread.sleep(4000); // To be deleted
		driver.findElement(By.xpath("//a[contains(text(),'All Input')]")).click();
		
		Thread.sleep(4000);
		List<WebElement> textboxes = driver.findElements(By.xpath("//*[@class='textbox']"));
		System.out.println("Text boxes size :" + textboxes.size());
		if (textboxes.size() > 0) {
			textboxes.get(0).sendKeys("Phani Ranjan");
			textboxes.get(1).sendKeys("Bangalore");
			textboxes.get(2).sendKeys("9014613020");
			textboxes.get(3).sendKeys("12-12-1980");
		}

		driver.findElement(By.xpath("//*[contains(text(),'Male')]")).click();
		driver.findElement(By.xpath("//*[contains(text(),'Master')]")).click();
		driver.findElement(By.xpath("//*[starts-with(@id,'rating')]/label[3]")).click();
		driver.findElement(By.xpath("//*[contains(text(),'Submit')]")).click();

		Thread.sleep(4000); // To be deleted
		System.out.println("Checking if submitted successefully. ");
		driver.findElement(By.xpath("//*[contains(text(),'Submitted Successfully')]"));

	}

	private void addTestBotTextAndSend() {
		System.out.println("Enter and send test bot option");
		driver.findElement(By.xpath("//*[@id=\"messageEditor-region\"]/div/div[3]/div[2]/textarea"))
				.sendKeys("test bot");
		driver.findElement(By.className("Send")).click();
	}

	private void switchToAvaamoBotFrame() throws InterruptedException {
		System.out.println("Switching to Avaamo Bot chat frame");
		driver.switchTo().frame("avaamoIframe");
		Thread.sleep(4000);
	}

	@BeforeMethod
	public void InvokeAvaamoBotSiteAndActivateAvaamoBot() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);

		System.out.println("Accessing Avaamo Bot Site");
		driver.get("https://goo.gl/hh18rF");

		System.out.println("Activating Avaamo Bot chat box");
		driver.findElement(By.className("avaamo__icon")).click();

	}

	@AfterMethod
	public void afterMethod() throws InterruptedException {
		// Thread.sleep(10000); // TODO: delete later, only for testing purpose.
		driver.close();
		driver.quit();
		driver = null;
		avaamoBot = null;
	}

}
