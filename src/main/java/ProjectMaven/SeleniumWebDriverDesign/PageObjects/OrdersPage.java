package ProjectMaven.SeleniumWebDriverDesign.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumWebDriverDesign.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent{
	WebDriver driver;
	
	@FindBy(css = ".totalRow button")
	WebElement checkoutele;
	
	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> ProductsNames;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public Boolean VerifyOrderDisplay(String productName)
	{
		Boolean match = ProductsNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
}
