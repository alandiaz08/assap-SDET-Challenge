package tests;

import base.TestBase;
import org.testng.annotations.Test;
import pageobjects.pages.LoginPage;

@Test(groups = {"full-regression"})
public class TestDemo extends TestBase {
  /**
   * Login with a customer.
   *
   */
  @Test(
      groups = {"customer"},
      description = "Login a customer",
      enabled = true
      //retryAnalyzer = TestBase.RetryAnalyzer.class
  )
  public void customerLogin() {

    // Arrange
    final String user = "alan";
    final String userPassword = "1234";


    LoginPage loginPage = new LoginPage();
    loginPage.get();

    loginPage
            .enterUserName(user)
            .enterPassword(userPassword)
            .logIn()
            .storeResultList()
            .getResult(1)
            .selectNumberOfProductToAddToCart("1");




    //Assert
  }
}
