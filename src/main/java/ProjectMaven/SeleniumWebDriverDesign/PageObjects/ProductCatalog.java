package ProjectMaven.SeleniumWebDriverDesign.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumWebDriverDesign.AbstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent{
	
	WebDriver driver;
	public ProductCatalog(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3") List<WebElement> productsList;
	@FindBy(css=".ng-animating") WebElement Spinner;
	
	By ProductsBy = By.cssSelector(".mb-3");
	By AddToCart = By.cssSelector(".card-body button:last-of-type");
	By ToastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList()
	{
		waitforElementToApper(ProductsBy);
		return productsList;
	}
	
	public WebElement getProductName(String productName)
	{
		WebElement Prod = getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return Prod;
	}
	
	public void addProductToCart(String ProductName)
	{
		WebElement Prod = getProductName(ProductName);
		
		WebElement addCartLoc = Prod.findElement(AddToCart);
		JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click();", addCartLoc);
		
//		Prod.findElement(AddToCart).click();
		waitforElementToApper(ToastMessage);
		waitforElementToDisapper(Spinner);
	}

}
