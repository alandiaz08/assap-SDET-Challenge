package pageobjects.pages;

import driver.DriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.base.AbstractPage;
import pageobjects.components.StoreResultList;
import pageobjects.components.Header;

public class StorePage extends AbstractPage {

  // Selectors
  private static final By headerBy = By.cssSelector("[class='MuiPaper-root MuiPaper-elevation4 MuiAppBar-root"
          + " MuiAppBar-positionStatic MuiAppBar-colorPrimary']");
  private static final By productAddedToCartMessageBy = By.id("snackbar-fab-message-id");
  private static final By cartListContainerBy = By.cssSelector(" header.App-header > div");

  // Components
  private Header header;
  private StoreResultList storeResultList;

  /**
   * Constructor of the StorePage class when coming from another page.
   */
  public StorePage() {
    super();
    logger.debug("Initializing StorePage");
  }

  public Header header() {
    return header;
  }

  public StoreResultList storeResultList() {
    return storeResultList;
  }

  /**
   * Verifies if the product added cart message is displayed.
   * @return True if the message is displayed. False otherwise.
   */
  public boolean isListOfRestaurantsEmpty() {
    WebDriverWait wait = new WebDriverWait(DriverBase.getDriver(), TIMEOUT_TO_LOAD_PAGE);
    try {
      wait.until(ExpectedConditions.presenceOfElementLocated(productAddedToCartMessageBy));
      return driver.findElement(productAddedToCartMessageBy).isDisplayed();
    } catch (NotFoundException e) {
      logger.debug("The product added cart message was not found", e);
      return false;
    }
  }

  /**
   * isLoaded() is called when StorePage.get() is called. Defines when the page has finished
   * loading. It must verify that the components of this page have also finished loading before
   * continuing.
   */
  @Override
  protected void isLoaded() throws Error {
    try {
      // verify that each element is displayed before continuing
      driver.findElement(cartListContainerBy);
      logger.debug("Store list container is displayed");
      driver.findElement(headerBy);
      logger.debug("Header container is displayed");
    } catch (Exception e) {
      throwNotLoadedException("The StorePage was not loaded correctly", e);
    }

    // initialize and verify that each internal component is loaded
    header = new Header(driver.findElement(headerBy));
    header.get();
    storeResultList = new StoreResultList(driver.findElement(cartListContainerBy));
    storeResultList.get();
  }
}
