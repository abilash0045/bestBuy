package testCaseBestBuy;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import baseBestBuy.BaseClassBB;
import pagesBestBuy.LocationPageBB;

public class TC_01_navgatingToBestBestApp extends BaseClassBB {
	
	@BeforeMethod
	public void setup() {
		testName = "tc_01_navgatingToBestBestApp";
		testDescription = "Navigating to the Best Buy e-commerce website";
		testCategory = "Regression";
		testAuthor = "Basil Ahamed";
		// Call parent setUp method to initialize WebDriver
		super.setUp();
	}
	
	@Test(priority=1)
	public void tc_01_navgatingToBestBestApp() throws Exception {
		LocationPageBB lp=new LocationPageBB();
		String actTitle = lp.bestBuyPageTitle();
		// After automatic country selection, we should be on the main Best Buy page
		String expTitle="Best Buy | Official Online Store | Shop Now & Save";
		softAssert(actTitle, expTitle);
		if (actTitle.equals(expTitle)) {
			reportStep("Validating Best Buy Page Title - Pass", "Pass", testName);
		} else {
			reportStep("Validating Best Buy Page Title - Fail. Expected: " + expTitle + ", Actual: " + actTitle, "Fail", testName);
		}
	}
}
