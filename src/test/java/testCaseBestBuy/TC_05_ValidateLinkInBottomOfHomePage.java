package testCaseBestBuy;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import baseBestBuy.BaseClassBB;
import pagesBestBuy.HomePageBB;

public class TC_05_ValidateLinkInBottomOfHomePage extends BaseClassBB {
	
	@BeforeMethod
	public void setup() {
		testName = "tc_05_validateLinkInBottomOfHomePage";
		testDescription = "Link in HomePage bottom is broken or not";
		testCategory = "Regression";
		testAuthor = "Basil Ahamed";
		// Call parent setUp method to initialize WebDriver
		super.setUp();
	}
	
	@Test(priority=6)
	public void tc_05_validateLinkInBottomOfHomePage() throws Exception {
		// Country selection is now handled automatically in BaseClassBB.initialization()
		HomePageBB hp=new HomePageBB();
		String actTitle = hp.validateTermsAndCondtionLink();
		String expTitle="BestBuy.com Terms and Conditions";
		softAssert(actTitle, expTitle);
		if (actTitle.equals(expTitle)) {
			reportStep("Validating the Title of link in bottom of Home Page - Pass", "Pass", testName);
		} else {
			reportStep("Validating the Title of link in bottom of Home Page - Fail", "Fail", testName);
		}
	}
}
