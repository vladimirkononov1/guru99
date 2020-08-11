package guru99;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.lang.reflect.Method;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import freemarker.template.SimpleDate;
import pageObjects.HomePage;
import pageObjects.ManagerHomePage;

public class ChangePwdAndLogin {

	
	public WebDriver driver;

	private static String baseUrl;
	static By locatedElement = By.className("menusubnav");
	static By homePage = By.name("frmLogin");
	String oldPwd = "test123456";
	String newPwd = "t123456!";
	String incorrectNewPwd = "123456";
	String specialCharMessage = "Enter at-least one special character";
	String uname = Util.USER_NAME;
	String password = Util.PASSWD;
	String allFieldsMessage = "Please fill all fields";

	
	@DataProvider(name="GuruTest")
	public Object[][] testData() {
			return new Object[][] {
				{Util.USER_NAME, Util.PASSWD},
				{"invalid", "valid"},
				{"valid", "invalid"},
				{"invalid", "invalid"}
			};
		
		}

	@BeforeTest(enabled = true)
	public void initialize() {
		
		File pathToBinary = new File(Util.FIREFOX_PATH);
		FirefoxBinary ffbinary = new FirefoxBinary(pathToBinary);
	
//		Create new firefoxProfile for Testing
//		FirefoxProfile firefoxProfile = new FirefoxProfile();

		// Setup Firefox driver
		driver = new FirefoxDriver();
		baseUrl = Util.BASE_URL;

		// Implicit wait
		driver.manage().timeouts().implicitlyWait(Util.WAIT_TIME, TimeUnit.SECONDS);

		driver.get(baseUrl + "/V4/");
	}
	
	@BeforeMethod(enabled=true)
	public void launchBrowser() {
		driver.get(baseUrl + "/V4/");
	}

	@AfterTest(enabled=true)
	public void tearDown() {
		driver.quit();
	}
	
	@AfterMethod(enabled=false)
		public void closeBrowser() {
			driver.close();
		}
	
	@Test(enabled=true)
	public void incorrectOldPassword() throws InterruptedException {
		HomePage hp = new HomePage(driver);
		ManagerHomePage mp = new ManagerHomePage(driver);
		String expectedMessage = "Old Password is incorrect";

		hp.signIn(uname, password);				
		mp.changePassword(oldPwd, newPwd, newPwd);
		
		Alert alt = driver.switchTo().alert();
		String message = alt.getText();
		Assert.assertEquals(message, expectedMessage);
		alt.dismiss();
		
	}
	
	@Test(enabled=true)
	public void incorrectNewPassword() {
		HomePage hp = new HomePage(driver);
		ManagerHomePage mp = new ManagerHomePage(driver);
//		String expectedMessage = "Please fill all fields";

		hp.signIn(uname, password);				
		mp.changePassword(oldPwd, incorrectNewPwd, incorrectNewPwd);
		
		Alert alt = driver.switchTo().alert();
		String message = alt.getText();
		Assert.assertEquals(message, allFieldsMessage);
		alt.dismiss();
		
		String spCharText = driver.findElement(By.id("message21")).getText();
		Assert.assertEquals(spCharText, specialCharMessage);
	
	}
	
	@Test(enabled=true)
	public void confirmPasswordUnmatched() {
		HomePage hp = new HomePage(driver);
		ManagerHomePage mp = new ManagerHomePage(driver);
		String expectedMessage = "Passwords do not Match";
		
		hp.signIn(uname, password);
		mp.changePassword(oldPwd, newPwd, incorrectNewPwd);
		
		Alert alt = driver.switchTo().alert();
		String message = alt.getText();
		Assert.assertEquals(message, allFieldsMessage);
		alt.dismiss();
		String pwdNotMatched = driver.findElement(By.id("message22")).getText();
		Assert.assertEquals(pwdNotMatched, expectedMessage);
		
	}
}
