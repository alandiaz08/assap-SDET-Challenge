package tests;

import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.components.CartResultItem;
import pageobjects.components.RegisterUser;
import pageobjects.pages.CartPage;
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
      enabled = true,
      retryAnalyzer = TestBase.RetryAnalyzer.class
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

  /**
   * Adds products in the cart.
   *
   */
  @Test(
          groups = {"register"},
          description = "Add products in the cart",
          enabled = true,
          retryAnalyzer = TestBase.RetryAnalyzer.class
  )
  public void addProductsToCart() {

    // Arrange
    final String user = "test.user." + RANDOM_GENERATOR.nextString();
    final String userPassword = RANDOM_GENERATOR.nextString();
    final String numberOfStickers = "3";
    final String expectedProduct = "ASAPP Pens";
    final String pensValue = "1";
    final int pens = 1;


    //Act I
    LoginPage loginPage = new LoginPage();
    loginPage.get();

    CartResultItem cartResultItem = loginPage.registerUser()
           .enterUserName(user)
            .enterPassword(userPassword)
            .registerUser()
            .enterUserName(user)
            .enterPassword(userPassword)
            .logIn()
            .storeResultList()
            .getResultOfProducts(pens)
            .selectNumberOfProductToAddToCart(numberOfStickers, pensValue)
            .addProductToCart(pensValue)
            .header()
            .goToCart()
            .cartResultList()
            .getResult(0);

    //Assert I
    TestReporter.addInfoToReport("Assert that the product in the cart is: " + expectedProduct);
    Assert.assertEquals(cartResultItem.getProducts(), expectedProduct, "The product in the cart is not: "
            + expectedProduct);

    TestReporter.addInfoToReport("Assert the quantity of products selected in the cart");
    Assert.assertEquals(cartResultItem.getQuantity(), numberOfStickers, "The quantity of products selected "
            + "in the cart is not: " + numberOfStickers);
  }

  /**
   * Adds products in the cart.
   *
   */
  @Test(
          groups = {"register"},
          description = "Add products in the cart",
          enabled = true
          //retryAnalyzer = TestBase.RetryAnalyzer.class
  )
  public void deleteAProductToCart() {

    // Arrange
    final String user = "test.user." + RANDOM_GENERATOR.nextString();
    final String userPassword = RANDOM_GENERATOR.nextString();
    final String numberOfStickers = "2";
    final String expectedMessageOfEmptyList = "OH NO YOUR CART IS EMPTY";
    final String stickerValue = "2";
    final int sticker = 1;

    //Act I
    LoginPage loginPage = new LoginPage();
    loginPage.get();

    loginPage.registerUser()
            .enterUserName(user)
            .enterPassword(userPassword)
            .registerUser()
            .enterUserName(user)
            .enterPassword(userPassword)
            .logIn()
            .storeResultList()
            .getResultOfProducts(sticker)
            .selectNumberOfProductToAddToCart(numberOfStickers, stickerValue)
            .addProductToCart(stickerValue)
            .header()
            .goToCart()
            .cartResultList()
            .getResult(0)
            .removeProduct();

    CartPage cartPage = new CartPage();

    //Assert I
    TestReporter.addInfoToReport("Assert that the message of the empty list is: " + expectedMessageOfEmptyList);
    Assert.assertEquals(cartPage.getYourCartIsEmpty(), expectedMessageOfEmptyList, "the message of "
            + "the empty list is not: " + expectedMessageOfEmptyList);
  }

}
