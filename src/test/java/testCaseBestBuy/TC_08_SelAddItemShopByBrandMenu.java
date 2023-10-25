package testCaseBestBuy;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import baseBestBuy.BaseClassBB;
import pagesBestBuy.CartPageBB;
import pagesBestBuy.HomePageBB;
import pagesBestBuy.LocationPageBB;
import pagesBestBuy.ProductPageBB;

public class TC_08_SelAddItemShopByBrandMenu extends BaseClassBB {

	@BeforeTest
	public void setup() {

		testName = "tc_08_selAddItemShopByBrandMenu";
		testDescription = "Search a Item by Brands in Menu and add it in cart";
		testCategory = "Regression";
		testAuthor = "Basil Ahamed";
	}

	@Test(priority=9)
	public void tc_08_selAddItemShopByBrandMenu() throws Exception {
		LocationPageBB lp = new LocationPageBB();
		lp.deliveryAtUSA();
		HomePageBB hp = new HomePageBB();
		hp.selectLenovoBrand();
		ProductPageBB pp = new ProductPageBB();
		String lenovoThinkPadTitleAtProdPage = pp.addLenovoThinkPadInProdPage();
		hp.goToCartPage();
		CartPageBB cp = new CartPageBB();
		String lenovoThinkPadTitleAtCartPage = cp.validateLenovoThinkPadInCart();
		softAssert(lenovoThinkPadTitleAtCartPage, lenovoThinkPadTitleAtProdPage);
		if (lenovoThinkPadTitleAtCartPage.equals(lenovoThinkPadTitleAtProdPage)) {
			reportStep("Validating Product Title - Pass", "Pass", testName);
		} else {
			reportStep("Validating Product Title - Fail", "Fail", testName);
		}
	}
}
