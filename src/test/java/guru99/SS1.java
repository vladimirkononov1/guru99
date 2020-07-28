package guru99;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SS1 {

	public static Properties prop;
	
	@Test
	public void validateLogin() throws Exception {
		// TODO Auto-generated method stub
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);
		
		System.setProperty("webdriver.gecko.driver","C:\\Users\\vladi\\workspace\\Guru99\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		String expectedTitle = "Guru99 Bank Manager HomePage";
		By userId = By.name("uid");
		By pwd = By.name("password");
		By login = By.name("btnLogin");

		driver.get(prop.getProperty("url"));
		driver.findElement(userId).sendKeys(prop.getProperty("userId"));
		driver.findElement(pwd).sendKeys(prop.getProperty("password"));
		driver.findElement(login).click();
		Assert.assertEquals(expectedTitle, driver.getTitle());
		
		driver.close();
	}
	
}
