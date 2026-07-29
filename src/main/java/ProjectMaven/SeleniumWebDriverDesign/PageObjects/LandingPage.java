package ProjectMaven.SeleniumWebDriverDesign.PageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumWebDriverDesign.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail") WebElement Email;
	@FindBy(id="userPassword") WebElement Password;
	@FindBy(id="login") WebElement Submit;
	@FindBy(css = "[class*='flyInOut']") WebElement error;
	
	public ProductCatalog LoginApplication(String EmailID,String PassWord)
	{
		Email.sendKeys(EmailID);
		Password.sendKeys(PassWord);;
//		Submit.click();
		
		// Use JavaScript click to bypass overlay interception
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click();", Submit);
		
		ProductCatalog productCatalog = new ProductCatalog(driver);
		return productCatalog;
	}
	public String getErrorMessage()
	{
		waitforWebElementToApper(error);
		return error.getText();
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");	
	}

}
