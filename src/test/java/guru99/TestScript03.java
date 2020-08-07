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

public class TestScript03 {

	public WebDriver driver;
	private static String baseUrl;
	static By locatedElement = By.className("menusubnav");
	static By homePage = By.name("frmLogin");
	
	
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
	
	@BeforeMethod(enabled=false)
	public void launchBrowser() {
		driver.get(baseUrl + "/V4/");
	}

	@AfterTest(enabled=false)
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void loginTest() throws InterruptedException {
		HomePage hp = new HomePage(driver);

		hp.signIn();
		
		System.out.println("First Test probe");
		
	}
}
