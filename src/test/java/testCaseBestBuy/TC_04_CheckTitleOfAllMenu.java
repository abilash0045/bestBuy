package testCaseBestBuy;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import baseBestBuy.BaseClassBB;
import pagesBestBuy.HomePageBB;
import pagesBestBuy.LocationPageBB;

public class TC_04_CheckTitleOfAllMenu extends BaseClassBB {
	
	@BeforeTest
	public void setup() {
		testName = "tc_04_CheckTitleOfAllMenu";
		testDescription = "Check the different menu titles";
		testCategory = "Regression";
		testAuthor = "Basil Ahamed";
		sheetName="menuGenricTestData";
	}
	
	@Test(dataProvider="getFromExcel", priority=5)
	public void tc_04_CheckTitleOfAllMenu(String menuName,String expTitle) throws Exception {
		LocationPageBB lp=new LocationPageBB();
		lp.deliveryAtUSA();
		HomePageBB hp=new HomePageBB();
		String actTitle = hp.menuValidation(menuName);
		softAssert(actTitle, expTitle);
		if (actTitle.equals(expTitle)) {
			reportStep("Validating the Title of different Menu - Pass", "Pass", testName);
		} else {
			reportStep("Validating the Title of different Menu - Fail", "Fail", testName);
		}
	}
}
