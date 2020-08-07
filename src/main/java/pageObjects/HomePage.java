package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HomePage {
	
	public WebDriver driver;
	
	public WebElement username;
	public WebElement pwd;
	public WebElement loginButton;
	
	private static By userId = By.name("uid");
	private static By password = By.name("password");
	private static By loginBtn = By.name("btnLogin");
	private static By resetBtn = By.name("btnReset");

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getUserId() {
		return driver.findElement(userId);
	}
	
	public WebElement getPassword() {
		return driver.findElement(password);
	}
	
	public WebElement getLoginBtn() {
		return driver.findElement(loginBtn);
	}
	
	public WebElement getResetBtn() {
		return driver.findElement(resetBtn);
	}
	
	public void signIn() {
		HomePage p = new HomePage(driver);
		username = p.getUserId();
		pwd = p.getPassword();
		loginButton = p.getLoginBtn();
		
		username.clear();
		username.sendKeys("mngr275032");
		pwd.clear();
		pwd.sendKeys("gEdajAd");
		loginButton.click();
	}

}
