package ProjectMaven.SeleniumWebDriverDesign.Test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import ProjectMaven.SeleniumWebDriverDesign.PageObjects.CartPage;
import ProjectMaven.SeleniumWebDriverDesign.PageObjects.CheckOutPage;
import ProjectMaven.SeleniumWebDriverDesign.PageObjects.ConfirmationPage;
import ProjectMaven.SeleniumWebDriverDesign.PageObjects.LandingPage;
import ProjectMaven.SeleniumWebDriverDesign.PageObjects.ProductCatalog;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WorkingWithPageObjects {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String Product = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		LandingPage Login = new LandingPage(driver);
		Login.goTo();
		Login.LoginApplication("y19ec1367@gmail.com", "Narasimha@123");
		
		ProductCatalog productCatalog = new ProductCatalog(driver);
		List<WebElement> productsList = productCatalog.getProductList();
		productCatalog.addProductToCart(Product);
		productCatalog.gotoCartPage();
		
		CartPage cartPage = new CartPage(driver);
		Boolean match = cartPage.VerifyProductDisplay(Product);
		Assert.assertTrue(true);
		cartPage.goToCheckout();
		
		CheckOutPage checkoutpage = new CheckOutPage(driver);
		checkoutpage.SelectCountry("India");
		checkoutpage.submitOrder();
		
		ConfirmationPage confirmation = new ConfirmationPage(driver);
		String ConfirmationMessage = confirmation.getConfirmationMessage();
		Assert.assertTrue(ConfirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		

	}

}
