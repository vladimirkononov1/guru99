package guru99;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SS1 {

	public static WebDriver driver;
	public static Properties prop;

	
	@BeforeTest
	public void initializeEnv() throws IOException {

		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\vladi\\workspace\\Guru99\\geckodriver.exe");

	}

	@Test(enabled=true)
	public void validateLogin() throws Exception {
		// TODO Auto-generated method stub
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 10);

		String expectedTitle = "Guru99 Bank Manager HomePage";
		By userId = By.name("uid");
		By pwd = By.name("password");
		By login = By.name("btnLogin");
		By linksBar = By.className("menusubnav");

		driver.get(prop.getProperty("url"));
		driver.findElement(userId).sendKeys(prop.getProperty("userId"));
		driver.findElement(pwd).sendKeys(prop.getProperty("password"));
		driver.findElement(login).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(linksBar));
		Assert.assertEquals(expectedTitle, driver.getTitle(), "Title not found");
		driver.close();
	}

}
