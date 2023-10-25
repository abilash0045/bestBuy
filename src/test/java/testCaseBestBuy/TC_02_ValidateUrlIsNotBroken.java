package testCaseBestBuy;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;

import baseBestBuy.BaseClassBB;
import pagesBestBuy.LocationPageBB;

public class TC_02_ValidateUrlIsNotBroken extends BaseClassBB {
	
	@BeforeTest
	public void setup() {
		testName = "tc_02_validateUrlIsNotBroken";
		testDescription = "Given URL Link is broken or not";
		testCategory = "Regression";
		testAuthor = "Basil Ahamed";
	}
	

	@Test(priority=2)
	public void tc_02_validateUrlIsNotBroken() throws Exception {
		LocationPageBB lp=new LocationPageBB();
		if (lp.urlResponseCode()==200) {
			reportStep("Validating Best Buy page URL is broken or not - Pass", "Pass", testName);
		} else {
			reportStep("Validating Best Buy page URL is broken or not - Fail", "Fail", testName);
		}
		AssertJUnit.assertTrue(lp.urlResponseCode()==200);
	}
}
