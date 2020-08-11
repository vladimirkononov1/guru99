package pageObjects;

import java.util.concurrent.SubmissionPublisher;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ManagerHomePage {
	
	public WebDriver driver;
	public WebElement change;
	public WebElement older;
	public WebElement newer;
	public WebElement confirm;
	public WebElement submit;
	
	private static By changePwd = By.xpath("//a[contains(text(),'Change Password')]");
	private static By oldPasswd = By.xpath("//input[@name='oldpassword']");
	private static By newPwd = By.name("newpassword");
	private static By confirmPwd = By.name("confirmpassword");
	private static By submitBtn = By.name("sub");
	
	public ManagerHomePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public WebElement getChangePwd() {
		return driver.findElement(changePwd);
	}
	
	public WebElement getOldPwd() {
		return driver.findElement(oldPasswd);
	}
	
	public WebElement getNewPwd() {
		return driver.findElement(newPwd);
	}

	public WebElement getConfirmPwd() {
		return driver.findElement(confirmPwd);
	}
	
	public WebElement getSubmitBtn() {
		return driver.findElement(submitBtn);
	}
	

	public void changePassword(String oldPassword, String newPassword, String confirmPassword) {
		ManagerHomePage m = new ManagerHomePage(driver);
		change = m.getChangePwd();
		change.click();

		older = m.getOldPwd();
		newer = m.getNewPwd(); 
		confirm = m.getConfirmPwd();
		submit = m.getSubmitBtn();
		
		older.sendKeys(oldPassword);
		newer.sendKeys(newPassword);
		confirm.sendKeys(confirmPassword);
		submit.click();
	}
}
