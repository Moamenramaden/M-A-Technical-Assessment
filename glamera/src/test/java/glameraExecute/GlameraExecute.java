package glameraExecute;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import glamera.loginPage;

public class GlameraExecute {
	WebDriver driver;
	loginPage login ;

	@Test (priority = 1, enabled = true)
	public void validLogin() {
		login.insertUserphone("01091142146");
		login.insertpassword("3298609a");
		loginSubmitTimer();
		//String expectedResult = driver.getCurrentUrl();
		//Assert.assertEquals(expectedResult, "https://stage.glamera.com/choose-business?RegistrationCycleLogId=2913");
	}

	@Test (priority = 2, enabled = true)
	public void invalidLoginByphone() {
		login.insertUserphone("01000000");
		login.insertpassword("3298609a");
		loginSubmitTimer();
		String pageError = driver.getPageSource();
		String errorMessage = "invalid user name or password";
		Assert.assertNotNull(pageError.contains(errorMessage));

	}

	@Test (priority = 3, enabled = true)
	public void invalidLoginBypassword() {
		login.insertUserphone("01091142146");
		login.insertpassword("3298600");
		loginSubmitTimer();
		String pageError = driver.getPageSource();
		String errorMessage = "invalid user name or password";
		Assert.assertNotNull(pageError.contains(errorMessage));
	}

	@Test (priority = 4, enabled = true)
	public void loginWithPhoneNoOnly() {
		login.insertUserphone("01091142146");
		boolean isButtonDisable = driver.findElement(By.className("remember")) != null;
		Assert.assertEquals(isButtonDisable,true);
	}

	@Test (priority = 5, enabled = true)
	public void loginWithPassswordOnly() {
		login.insertpassword("3298609a");
		boolean isButtonDisable = driver.findElement(By.className("remember")) != null;
		Assert.assertEquals(isButtonDisable,true);
	}
	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://stage.glamera.com");
		try {
			java.lang.Thread.sleep(2000);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		login = new loginPage (driver);
	}

	private void loginSubmitTimer() {
		try {
			java.lang.Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		login.submit();

		try {
			java.lang.Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterMethod

	public void afterMethod() {
		driver.close();
		driver.quit();
	}
}
