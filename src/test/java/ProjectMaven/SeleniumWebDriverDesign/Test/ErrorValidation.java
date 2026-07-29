package ProjectMaven.SeleniumWebDriverDesign.Test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import ProjectMaven.SeleniumWebDriverDesign.Test.TestComponent.BaseTest;
import ProjectMaven.SeleniumWebDriverDesign.Test.TestComponent.Retry;

public class ErrorValidation extends BaseTest{
	
	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void submitOrder() throws IOException
	{
		String Product = "ZARA COAT 3";
		Login.LoginApplication("y19ec1367@gmail.com", "Narasimha@1234");
		Assert.assertEquals("Incorrect email or password.", Login.getErrorMessage());
		driver.close();

	}
	
}
