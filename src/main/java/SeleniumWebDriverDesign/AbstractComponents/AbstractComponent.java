package SeleniumWebDriverDesign.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ProjectMaven.SeleniumWebDriverDesign.PageObjects.CartPage;
import ProjectMaven.SeleniumWebDriverDesign.PageObjects.OrdersPage;

public class AbstractComponent {
	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css ="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css ="[routerlink*='myorders']")
	WebElement orderHeader;
	
	public void waitforElementToApper(By Findby)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(Findby));
	}
	
	public void waitforWebElementToApper(WebElement Findby)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(Findby));
	}
	
	public CartPage gotoCartPage()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click();", cartHeader);
	    
	    return new CartPage(driver);
//		cartHeader.click();
//		CartPage cartpage = new CartPage(driver);
//		return cartpage;
	}
	
	public OrdersPage gotoOrderPage()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click();", orderHeader);
	    
	    return new OrdersPage(driver);
//		orderHeader.click();
//		OrdersPage orderpage = new OrdersPage(driver);
//		return orderpage;
	}
	
	public void waitforElementToDisapper(WebElement Element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(Element));
	}

}
