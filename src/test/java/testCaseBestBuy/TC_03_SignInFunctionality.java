package testCaseBestBuy;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import baseBestBuy.BaseClassBB;
import pagesBestBuy.HomePageBB;
import pagesBestBuy.LocationPageBB;
import pagesBestBuy.SignInPageBB;

public class TC_03_SignInFunctionality extends BaseClassBB {
	
	@BeforeTest
	public void setup() {
		testName = "tc_03_SignInFunctionality";
		testDescription = "Sign-In Functionality";
		testCategory = "Regression";
		testAuthor = "Basil Ahamed";
		sheetName="signInTestData";
	}
	
	@Test(dataProvider="getFromExcel", priority=4)
	public void tc_03_SignInFunctionality(String email,String password,String xpath,String expMsg) throws Exception {
		LocationPageBB lp=new LocationPageBB();
		lp.deliveryAtUSA();
		HomePageBB hp=new HomePageBB();
		hp.goToSignIn();
		SignInPageBB sp=new SignInPageBB();
		String actMsg = sp.signInFunctionality(email,password,xpath);
		softAssert(actMsg, expMsg);
		if (actMsg.equals(expMsg)) {
			reportStep("Validating Sign-In Functionality - Pass", "Pass", testName);
		} else {
			reportStep("Validating Sign-In Functionality - Fail", "Fail", testName);
		}
	}
}
