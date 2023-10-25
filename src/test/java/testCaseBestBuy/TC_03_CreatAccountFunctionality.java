package testCaseBestBuy;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import baseBestBuy.BaseClassBB;
import pagesBestBuy.CreatAccountPageBB;
import pagesBestBuy.HomePageBB;
import pagesBestBuy.LocationPageBB;

public class TC_03_CreatAccountFunctionality extends BaseClassBB {
	
	@BeforeTest
	public void setup() {
		testName = "tc_03_validateCreatAccountFunctionality";
		testDescription = "CreatAccount Functionality";
		testCategory = "Regression";
		testAuthor = "Basil Ahamed";
		sheetName="loginTestData";
	}
	
	@Test(dataProvider="getFromExcel", priority=3)
	public void tc_03_validateCreatAccountFunctionality(String fname, String lname, String emailId,
			String pass, String repass, String mob, String xpathText, String expMsg) throws Exception {
		LocationPageBB lp=new LocationPageBB();
		lp.deliveryAtUSA();
		HomePageBB hp=new HomePageBB();
		hp.goToCreatAccount();
		CreatAccountPageBB ca=new CreatAccountPageBB();
		String actMsg = ca.creatAccountFunctionality(fname, lname, emailId, pass, repass, mob, xpathText);
		softAssert(actMsg, expMsg);
		if (actMsg.equals(expMsg)) {
			reportStep("Validating CreatAccount Functionality - Pass", "Pass", testName);
		} else {
			reportStep("Validating CreatAccount Functionality - Fail", "Fail", testName);
		}
	}
}
