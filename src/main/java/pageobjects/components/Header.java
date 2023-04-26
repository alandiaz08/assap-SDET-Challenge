package pageobjects.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobjects.base.AbstractComponent;
import pageobjects.pages.CartPage;
import pageobjects.pages.StorePage;
import utils.TestReporter;

public class Header extends AbstractComponent {

  // Selectors
  protected static final By storeBy = By.cssSelector("div > button:nth-child(1)");
  private static final By cartBy = By.cssSelector("div > button:nth-child(2)");

  public Header(WebElement container) {
    super(container);
  }

  /**
   * Open the store page.
   * @return Instance of StorePage.
   */
  public StorePage goToStore() {
    TestReporter.addInfoToReport("Open the store page");
    container.findElement(storeBy).click();
    StorePage storePage = new StorePage();
    storePage.get();
    return storePage;
  }

  /**
   * Open the cart page.
   * @return Instance of CartPage.
   */
  public CartPage goToCart() {
    TestReporter.addInfoToReport("Open the cart page");
    container.findElement(cartBy).click();
    CartPage cartPage = new CartPage();
    cartPage.get();
    return cartPage;
  }


  @Override
  protected void isLoaded() throws Error {
    try {
      // verify that each element is displayed before continuing
      container.findElement(storeBy);
      logger.debug("Store button is displayed");
      container.findElement(cartBy);
      logger.debug("Cart button is displayed");
    } catch (Exception e) {
      throwNotLoadedException("The header component was not loaded correctly", e);
    }

    logger.debug("Header component was loaded correctly");
  }
}
