package ProjectMaven.SeleniumWebDriverDesign.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ProjectMaven.SeleniumWebDriverDesign.PageObjects.CartPage;
import ProjectMaven.SeleniumWebDriverDesign.PageObjects.CheckOutPage;
import ProjectMaven.SeleniumWebDriverDesign.PageObjects.ConfirmationPage;
import ProjectMaven.SeleniumWebDriverDesign.PageObjects.LandingPage;
import ProjectMaven.SeleniumWebDriverDesign.PageObjects.OrdersPage;
import ProjectMaven.SeleniumWebDriverDesign.PageObjects.ProductCatalog;
import ProjectMaven.SeleniumWebDriverDesign.Test.TestComponent.BaseTest;

public class GoingWithTestNGFrameWork extends BaseTest{
	
	String Product = "ZARA COAT 3";
	
	@Test(dataProvider="getData", groups = {"Purchase"})
	public void submitAOrder(HashMap<String,String> input) throws IOException
	{
		ProductCatalog productCatalog = Login.LoginApplication(input.get("email"), input.get("password"));
		
		List<WebElement> productsList = productCatalog.getProductList();
		productCatalog.addProductToCart(input.get("Product"));
		productCatalog.gotoCartPage();
		
		CartPage cartPage = new CartPage(driver);
		Boolean match = cartPage.VerifyProductDisplay(input.get("Product"));
		Assert.assertTrue(true);
		cartPage.goToCheckout();
		
		CheckOutPage checkoutpage = new CheckOutPage(driver);
		checkoutpage.SelectCountry("India");
		checkoutpage.submitOrder();
		
		ConfirmationPage confirmation = new ConfirmationPage(driver);
		String ConfirmationMessage = confirmation.getConfirmationMessage();
		Assert.assertTrue(ConfirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods = {"submitAOrder"})
	public void OrdersHistory()
	{
		ProductCatalog productCatalog = Login.LoginApplication("y19ec1367@gmail.com", "Narasimha@123");
		OrdersPage orderspage = productCatalog.gotoOrderPage();
		Assert.assertTrue(orderspage.VerifyOrderDisplay(Product));
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "y19ec1367@gmail.com");
//		map.put("password", "Narasimha@123");
//		map.put("Product", "ZARA COAT 3");
//		
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "anshika@gmail.com");
//		map1.put("password", "Iamking@000");
//		map1.put("Product", "ADIDAS ORIGINAL");
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\ProjectMaven\\SeleniumWebDriverDesign\\Data\\ParchaseOrder.json");
		
		return new Object[][] {{data.get(0)}};
	}
	
//	public Object[][] getData()
//	{
//		return new Object[][] {{"y19ec1367@gmail.com","Narasimha@123","ZARA COAT 3"}, {"anshika@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
//	}

}
