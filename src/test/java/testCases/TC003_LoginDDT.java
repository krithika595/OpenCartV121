package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*
 Data is valid - login success - test pass - logout
 Data is valid -- login failed - test fail
 
 Data is invalid - login success - test fail - logout
 Data is invalid -- login failed - test pass
 */

public class TC003_LoginDDT extends BaseClass{
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class,groups="DataDriven") //getting data provider from diff class
	public void verfify_loginDDT(String email,String pwd,String exp) {
		logger.info("***** Starting TC003_LoginDDT *****");
		
		try {
			//HomePage
			HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();
			
			//LoginPage
			LoginPage lp=new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(pwd);
			lp.clickLogin();
			
			//MyAccount
			MyAccountPage maccp=new MyAccountPage(driver);
			boolean targetPage=maccp.isMyAccountPageExists();
			
			if(exp.equalsIgnoreCase("Valid")) {
				if(targetPage==true) {
					Assert.assertTrue(true);
					maccp.clickLogout();
				}else {
					Assert.assertTrue(false);
				}
			}
			
			if(exp.equalsIgnoreCase("Invalid")) {
				if(targetPage==true) {
					maccp.clickLogout();
					Assert.assertTrue(false);
				}else {
					Assert.assertTrue(true);
				}
			}
		} catch (Exception e) {
			Assert.fail();
		}
		
		logger.info("***** Finished TC003_LoginDDT *****");
		
	}

}
