package ProjectMaven.SeleniumWebDriverDesign.StepDefination;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import ProjectMaven.SeleniumWebDriverDesign.PageObjects.CartPage;
import ProjectMaven.SeleniumWebDriverDesign.PageObjects.CheckOutPage;
import ProjectMaven.SeleniumWebDriverDesign.PageObjects.ConfirmationPage;
import ProjectMaven.SeleniumWebDriverDesign.PageObjects.LandingPage;
import ProjectMaven.SeleniumWebDriverDesign.PageObjects.ProductCatalog;
import ProjectMaven.SeleniumWebDriverDesign.Test.TestComponent.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationImp extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalog productCatalog;
	public ConfirmationPage confirmation;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage = LaunchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$") //(.+) it represent any character or any value and it is regular expression
	public void Logged_In_With_Username_And_Password(String name,String password)
	{
		productCatalog = landingPage.LoginApplication(name,password);
	}
	
	@When("^I add product (.+) to Cart$")
	public void I_Add_Product_To_Order(String ProductName)
	{
		List<WebElement> productsList = productCatalog.getProductList();
		productCatalog.addProductToCart(ProductName);
	}
	
	@And("^Checkout (.+) and submit the order$")
	public void Checkout_ProductName_And_Submit_The_Order(String ProductName)
	{	
		CartPage cartPage = productCatalog.gotoCartPage();
		Boolean match = cartPage.VerifyProductDisplay(ProductName);
		Assert.assertTrue(true);
		CheckOutPage checkoutpage = cartPage.goToCheckout();
		checkoutpage.SelectCountry("India");
		confirmation = checkoutpage.submitOrder();
	}
	
	@Then("{string} message should display from ConfirmationPage")
	public void Message_Should_Display_From_ConfirmationPage(String string)
	{
		String ConfirmationMessage = confirmation.getConfirmationMessage();
		Assert.assertTrue(ConfirmationMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message should display")
	public void Message_Should_Display(String errorMessage)
	{
		Assert.assertEquals(errorMessage, Login.getErrorMessage());
		driver.close();

	}

}
