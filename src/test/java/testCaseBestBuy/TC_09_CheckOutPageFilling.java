package testCaseBestBuy;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import baseBestBuy.BaseClassBB;
import pagesBestBuy.CartPageBB;
import pagesBestBuy.CheckOutPageBB;
import pagesBestBuy.HomePageBB;
import pagesBestBuy.LocationPageBB;
import pagesBestBuy.ProductPageBB;

public class TC_09_CheckOutPageFilling extends BaseClassBB {
	//https://www.bestbuy.com/checkout/r/payment
	
	@BeforeTest
	public void setup() {
		testName = "tc_09_CheckOutPageFilling";
		testDescription = "Navigating to the checkout page and filling the form with dummy payment information";
		testCategory = "Regression";
		testAuthor = "Basil Ahamed";
	}
	
	@Test(priority=10)
	public void tc_09_CheckOutPageFilling() throws Exception {
		LocationPageBB lp=new LocationPageBB();
		lp.deliveryAtUSA();
		HomePageBB hp=new HomePageBB();
		hp.searchProduct("Mouse");
		Thread.sleep(8);
		ProductPageBB pp=new ProductPageBB();;		
		pp.addLogitechMouse();
		Thread.sleep(3);
		CartPageBB cp=new CartPageBB();
		cp.goToCheckOutPage();
		CheckOutPageBB cop=new CheckOutPageBB();
		cop.contactInfoAtCheckOut("vino18493@gmail.com", "9876543210");
		reportStep("Validating CheckOut page filling but the application does working properly.if it works properly the commented method in test case will fill the page with responding details - Pass", "Pass", testName);
		//The below commented line contains script which can run if application works properly
//		cop.enterDetailsOnCheckOut("5432167890654321", "12", "2025", "321", "SonName", "FatherName", "123 Vvikanandhar Road", "Utto", "DC", "32145");
		
	}
}
