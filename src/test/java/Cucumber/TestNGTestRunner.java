package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/Cucumber",glue="ProjectMaven.SeleniumWebDriverDesign.StepDefination",
monochrome=true,tags = "@ErrorValidation",plugin= {"html:target/cucmber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
