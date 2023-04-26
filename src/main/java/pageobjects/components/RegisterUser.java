package pageobjects.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobjects.base.AbstractComponent;
import pageobjects.pages.LoginPage;

/**
 * Class for RegisterUser.
 */
public class RegisterUser extends AbstractComponent {

  //Selector
  private static final By thankYouBy = By.cssSelector("[class='MuiTypography-root MuiTypography-h6']");
  private static final By userNameAndPasswordMessageBy = By.cssSelector("[class='MuiTypography-root "
          + "MuiDialogContentText-root MuiTypography-body1 MuiTypography-colorTextSecondary']");
  private static final By userNameBy = By.id("register-username");
  private static final By passwordBy = By.id("register-password");
  private static final By registerBy = By.cssSelector("[class='MuiButtonBase-root MuiButton-root auth-button"
          + " MuiButton-text MuiButton-textPrimary']");

  public RegisterUser(WebElement container) {
    super(container);
  }

  /**
   * Gets Thank You Message.
   * @return Thank You Message.
   */
  public String getThankYouMessage() {
    logger.debug("Get Thank You Message");
    return container.findElement(thankYouBy).getText();
  }

  /**
   * Gets the userName And Password Message.
   * @return the User Name And Password Message.
   */
  public String getUserNameAndPasswordMessage() {
    logger.debug("Get User Name And Password Message");
    return container.findElement(userNameAndPasswordMessageBy).getText();
  }

  /**
   * Enter userName.
   *
   * @param userName
   * @return RegisterUser.
   */
  public RegisterUser enterUserName(String userName) {
    logger.debug("Enters user name: " + userName);
    container.findElement(userNameBy).sendKeys(userName);
    return this;
  }

  /**
   * Enters password.
   *
   * @param password
   * @return RegisterUser.
   */
  public RegisterUser enterPassword(String password) {
    logger.debug("Enter user name: " + password);
    container.findElement(passwordBy).sendKeys(password);
    return this;
  }

  /**
   * Registers new user.
   * @return LoginPage.
   */
  public LoginPage registerUser() {
    logger.debug("Register new user");
    container.findElement(registerBy).click();
    LoginPage loginPage = new LoginPage();
    loginPage.get();
    return loginPage;
  }

  @Override
  protected void isLoaded() throws Error {
    try {
      // verify that each element is displayed before continuing
      container.findElement(thankYouBy);
      logger.debug("Thank you message is displayed");
      container.findElement(userNameAndPasswordMessageBy);
      logger.debug("User name and password message is displayed");
      container.findElement(userNameBy);
      logger.debug("User name field is displayed");
      container.findElement(passwordBy);
      logger.debug("Password field is displayed");
      container.findElement(registerBy);
      logger.debug("Register button is displayed");
    } catch (Exception e) {
      throwNotLoadedException("The register component was not loaded correctly", e);
    }

    logger.debug("RegisterUser component was loaded correctly");
  }
}
