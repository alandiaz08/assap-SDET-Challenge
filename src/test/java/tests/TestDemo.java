package tests;

import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.components.RegisterUser;
import pageobjects.pages.LoginPage;
import pageobjects.pages.StorePage;
import utils.RandomStringGenerator;
import utils.TestReporter;

import java.security.SecureRandom;

@Test(groups = {"full-regression"})
public class TestDemo extends TestBase {

  private static final int MAX_LENGTH_FILENAME = 5;
  private static final RandomStringGenerator RANDOM_GENERATOR =
          new RandomStringGenerator(MAX_LENGTH_FILENAME, new SecureRandom(),
                  RandomStringGenerator.LOWERCASE);

  /**
   * Register a new user.
   *
   */
  @Test(
      groups = {"register"},
      description = "Register a new user",
      enabled = true
      //retryAnalyzer = TestBase.RetryAnalyzer.class
  )
  public void registerNewUser() {

    // Arrange
    final String user = "test.user." + RANDOM_GENERATOR.nextString();
    final String userPassword = RANDOM_GENERATOR.nextString();
    final String expectedThankYouValue = "Thank you!";
    final String expectedSubTitle = "Please insert Username and Password";

    //Act I
    LoginPage loginPage = new LoginPage();
    loginPage.get();

    RegisterUser registerUser = loginPage.registerUser();

    //Assert I
    TestReporter.addInfoToReport("Assert that the title of the register match with: " + expectedThankYouValue);
    Assert.assertEquals(registerUser.getThankYouMessage(),expectedThankYouValue,
            "The title of the register not match with: " + expectedThankYouValue);

    TestReporter.addInfoToReport("Assert that the sub title of the register match with: " + expectedSubTitle);
    Assert.assertEquals(registerUser.getUserNameAndPasswordMessage(),expectedSubTitle,
            "The sub title of the register not match with: " + expectedSubTitle);

    //Act II
    StorePage storePage = registerUser.enterUserName(user)
            .enterPassword(userPassword)
            .registerUser()
            .enterUserName(user)
            .enterPassword(userPassword)
            .logIn();

    //Assert II
    TestReporter.addInfoToReport("Assert that the product list of the store is not empty");
    Assert.assertTrue(storePage.isListOfProductEmpty(), "The product list of the store is empty ");
  }


}
