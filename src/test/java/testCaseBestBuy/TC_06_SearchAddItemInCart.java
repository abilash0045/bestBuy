package testCaseBestBuy;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import baseBestBuy.BaseClassBB;
import pagesBestBuy.CartPageBB;
import pagesBestBuy.HomePageBB;
import pagesBestBuy.LocationPageBB;
import pagesBestBuy.ProductPageBB;

public class TC_06_SearchAddItemInCart extends BaseClassBB {

	@BeforeTest
	public void setup() {

		testName = "tc_06_SearchAddItemInCart";
		testDescription = "Search a Item in search box and add it in cart";
		testCategory = "Regression";
		testAuthor = "Basil Ahamed";
	}

	@Test(priority=7)
	public void tc_06_SearchAddItemInCart() throws Exception {
		LocationPageBB lp = new LocationPageBB();
		lp.deliveryAtUSA();
		HomePageBB hp = new HomePageBB();
		hp.searchProduct("Mouse");
		ProductPageBB pp = new ProductPageBB();
		String prodTitleAtProductPage = pp.addLogitechMouse();
		CartPageBB cp = new CartPageBB();
		String prodTitleAtCartPage = cp.validateLogitechMouseInCart();
		softAssert(prodTitleAtProductPage, prodTitleAtCartPage);
		if (prodTitleAtProductPage.equals(prodTitleAtCartPage)) {
			reportStep("Validating Product Title - Pass", "Pass", testName);
		} else {
			reportStep("Validating Product Title - Fail", "Fail", testName);
		}
	}
}
