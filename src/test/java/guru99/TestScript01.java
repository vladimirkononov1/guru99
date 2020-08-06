package guru99;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestScript01 {

	static WebDriver driver;
	private static String baseUrl;
	static By locatedElement = By.className("menusubnav");
	public static Properties prop;

//	public static void setUp() throws IOException {
//
//		File pathToBinary = new File(Util.FIREFOX_PATH);
//		FirefoxBinary ffbinary = new FirefoxBinary(pathToBinary);
//
////		Create new firefoxProfile for Testing
//		FirefoxProfile firefoxProfile = new FirefoxProfile();
//
//		// Setup Firefox driver
//		driver = new FirefoxDriver();
//		baseUrl = Util.BASE_URL;
//
//		// Implicit wait
//		driver.manage().timeouts().implicitlyWait(Util.WAIT_TIME, TimeUnit.SECONDS);
//
//		driver.get(baseUrl + "/V4/");
//
//	}

	@BeforeTest(enabled = true)
	public void initialize() throws IOException {
		driver = new FirefoxDriver();
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\vladi\\workspace\\Guru99\\geckodriver.exe");
		
		driver.get(prop.getProperty("url"));
		
	}

	@Test(enabled = false)
	public void loginSimple() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		String expectedTitle = Util.EXPECT_TITLE;
		// Enter username
		driver.findElement(By.name("uid")).clear();
		driver.findElement(By.name("uid")).sendKeys(Util.USER_NAME);

		// Enter Password
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(Util.PASSWD);

		// Click Login
		driver.findElement(By.name("btnLogin")).click();

		wait.until(ExpectedConditions.presenceOfElementLocated(locatedElement));

		if (expectedTitle.contains(driver.getTitle())) {
			System.out.println("Test Passed");
		} else {
			System.out.println("Test Failed");
		}

		driver.close();
	}

	@Parameters({ "username", "password" })
	@Test(enabled=false)
	public void validUidPwd(@Optional String username, @Optional String password) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		String expectedTitle = Util.EXPECT_TITLE;

		// Enter username
		driver.findElement(By.name("uid")).clear();
		driver.findElement(By.name("uid")).sendKeys(username);

		// Enter Password
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(password);

		// Click Login
		driver.findElement(By.name("btnLogin")).click();

		wait.until(ExpectedConditions.presenceOfElementLocated(locatedElement));

		Assert.assertEquals(driver.getTitle(), expectedTitle);
		driver.close();

	}
	
	@Parameters({"username", "password"})
	@Test(enabled=false)
	public void tryingParameters(@Optional() String username, @Optional() String password) {
		System.out.println("Valid username is: " + username);
		System.out.println("Valid password is: " + password);
	}
}
