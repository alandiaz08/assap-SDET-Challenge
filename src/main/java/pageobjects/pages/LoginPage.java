package pageobjects.pages;

import environment.EnvironmentConfig;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import pageobjects.base.AbstractPage;
import pageobjects.components.RegisterUser;
import utils.TestReporter;

/**
 * class for LoginPage.
 */
public class LoginPage extends AbstractPage {

  // Selectors
  private static final By registerContainerBy =
          By.cssSelector("[class='MuiPaper-root MuiPaper-elevation24 MuiDialog-paper"
                  + " MuiDialog-paperScrollPaper MuiDialog-paperWidthSm MuiPaper-rounded']");
  private static final By userNameBy = By.id("username");
  private static final By passwordBy = By.id("password");
  private static final By logInBy = By.cssSelector("div > button:nth-child(2)");
  private static final By registerBy = By.cssSelector("div > button:nth-child(3)");

  private URL loginPageUrl;

  /**
   * Constructor of the LoginPage class.
   */
  public LoginPage() {
    super();
    logger.debug("Initializing Login Page");
    try {
      loginPageUrl = new URL(EnvironmentConfig.getAsappUrl());
    } catch (MalformedURLException e) {
      logger.error("The URL format '{}' is not correct.", EnvironmentConfig.getAsappUrl(), e);
      throwNotLoadedException("The Login Page could not be opened", e);
    }
    logger.debug("Set Login Page url to: '{}'", loginPageUrl);
  }

  /**
   * Enter userName.
   *
   * @param userName
   * @return LoginPage.
   */
  public LoginPage enterUserName(String userName) {
    logger.debug("Enters user name: " + userName);
    driver.findElement(userNameBy).sendKeys(userName);
    return this;
  }

  /**
   * Enters password.
   *
   * @param password
   * @return LoginPage.
   */
  public LoginPage enterPassword(String password) {
    TestReporter.addInfoToReport("Enter user name: " + password);
    driver.findElement(passwordBy).sendKeys(password);
    return this;
  }

  /**
   * Login the user.
   * @return CartPage.
   */
  public StorePage logIn() {
    TestReporter.addInfoToReport("Login the user");
    driver.findElement(logInBy).click();
    StorePage storePage = new StorePage();
    storePage.get();
    return storePage;
  }

  /**
   * Registers new user.
   * @return LoginPage.
   */
  public RegisterUser registerUser() {
    TestReporter.addInfoToReport("Register new user");
    driver.findElement(registerBy).click();
    RegisterUser registerUser = new RegisterUser(driver.findElement(registerContainerBy));
    registerUser.get();
    return registerUser;
  }

  /**
   * load() is called when LoginPage.get() is called. Opens the LoginPage defined.
   */
  @Override
  protected void load() {
    TestReporter.addInfoToReport("Opening Home Page: " + loginPageUrl);
    driver.get(loginPageUrl.toString());
    driver.navigate().refresh();
  }

  /**
   * isLoaded() is called when LoginPage.get() is called. Defines when the page has finished
   * loading. It must verify that the components of this page have also finished loading before
   * continuing.
   */
  @Override
  protected void isLoaded() throws Error {
    try {
      // verify that each element is displayed before continuing
      driver.findElement(userNameBy);
      logger.debug("User Name field is displayed");
      driver.findElement(passwordBy);
      logger.debug("Password field is displayed");
      driver.findElement(logInBy);
      logger.debug("Log in button is displayed");
      driver.findElement(registerBy);
      logger.debug("Register button is displayed");
    } catch (Exception e) {
      throwNotLoadedException("The Login Page was not loaded correctly", e);
    }
  }
}
