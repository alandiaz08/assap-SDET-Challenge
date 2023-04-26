package pageobjects.pages;

import org.openqa.selenium.By;
import pageobjects.base.AbstractPage;
import pageobjects.components.CartResultList;
import pageobjects.components.Header;
import pageobjects.components.ThankYou;
import utils.TestReporter;

/**
 * Class for CartPage.
 */
public class CartPage extends AbstractPage {

  //Selectors
  private static final By buyBy = By.cssSelector("div.buy-button > button");
  private static final By headerBy = By.cssSelector("[class='MuiPaper-root MuiPaper-elevation4 MuiAppBar-root"
          + " MuiAppBar-positionStatic MuiAppBar-colorPrimary']");
  private static final By resultListContainerBy = By.cssSelector("tbody");
  private static final By thankYouContainerBy = By.cssSelector("div.MuiDialog-container.MuiDialog-scrollPaper > div");


  // Components
  private Header header;
  private CartResultList cartResultList;



  /**
   * Constructor of the SearchPage class when coming from another page.
   */
  public CartPage() {
    super();
    logger.debug("Initializing Search Page");
  }

  /**
   * Buys product.
   * @return Instance of StorePage.
   */
  public ThankYou buy() {
    TestReporter.addInfoToReport("Buy product");
    driver.findElement(buyBy).click();
    ThankYou thankYou = new ThankYou(driver.findElement(thankYouContainerBy));
    thankYou.get();
    return thankYou;
  }

  /**
   * isLoaded() is called when CartPage.get() is called. Defines when the page has finished
   * loading. It must verify that the components of this page have also finished loading before
   * continuing.
   */
  @Override
  protected void isLoaded() throws Error {
    // Continue loading the rest of the page
    try {
      // verify that each element is displayed before continuing
      driver.findElement(buyBy);
      logger.debug("Buy button is displayed");
    } catch (Exception e) {
      throwNotLoadedException("The Cart page is no loaded", e);
    }

    header = new Header(driver.findElement(headerBy));
    header.get();

    try {
      // special case for the list of results, if there are no cart results the list does not
      // appear
      driver.findElement(resultListContainerBy);
      logger.debug("Cart list result is displayed");

      // only initializes the cart result list if it is visible
      cartResultList = new CartResultList(driver.findElement(resultListContainerBy));
      cartResultList.get();

    } catch (Exception noSearchList) {
      // if the cart list was not found, try to find the empty restaurant list message
      try {
        logger.debug("Search list result is not displayed, checking for empty list message",
                noSearchList);
        driver.findElement(buyBy).isDisplayed();
        logger.debug("Empty restaurant list message is displayed");
        cartResultList = new CartResultList();
        logger.debug("Initializing the SearchResultList with zero element");
      } catch (Exception e) {
        throwNotLoadedException("Cart Page was not loaded correctly", e);
      }
    }
  }
}
