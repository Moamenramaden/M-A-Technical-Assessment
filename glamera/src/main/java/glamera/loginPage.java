package glamera;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class loginPage {
	WebDriver Driver;
	By userPhone = By.id(Constants.User_Phone_No);	
	By passWord = By.id(Constants.Password);
	By loginBtn = By.xpath(Constants.Login_Btn);

	public loginPage (WebDriver Driver) {
		this.Driver=Driver;
	}

	public void insertUserphone(String phoneNO) {
		Driver.findElement(userPhone).sendKeys(phoneNO);
	}

	public void insertpassword(String password) {
		Driver.findElement(passWord).sendKeys(password);

	}

	public void submit () {
		Driver.findElement(loginBtn).click();
	}

}
