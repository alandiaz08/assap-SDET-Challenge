package pageobjects.components;

import driver.DriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.base.AbstractComponent;
import pageobjects.pages.StorePage;

import java.util.List;

public class StoreResultItem extends AbstractComponent {

  // Selectors
  private static final By pictureBy = By.tagName("img");
  private static final By titleBy = By.cssSelector("[data-test-name='product-title']");
  private static final By productDescriptionBy = By.cssSelector("[data-test-name='product-desc']");
  private static final By addToCartBy = By.cssSelector("[data-test-name='add-to-cart-button']");
  private static final By inStockBy = By.cssSelector("[data-test-name='stock-button']");
  private static final By dropDownOfProductBy = By.cssSelector("div.MuiCardActions-root.MuiCardActions-spacing > div");
  private static final By numberOfProductBy = By.cssSelector("[role='listbox'] >li");



  /**
   * Initializes the selectors specific to the search result item on the search results page.
   * @param container the container of the Search Result Item
   */
  public StoreResultItem(WebElement container) {
    super(container);

    // initializes selectors to be used by the super class
  }

  /**
   * Checks if the store item has a picture.
   * @return True if a picture is visible. False otherwise.
   */
  public boolean hasPicture() {
    logger.debug("Has picture");
    try {
      return container.findElement(pictureBy).isDisplayed();
    } catch (NoSuchElementException e) {
      logger.debug("Picture not found", e);
      return false;
    }
  }

  /**
   * Gets the cuisine tag.
   * @return the title.
   */
  public String getTitle() {
    logger.debug("Get the Title");
    return container.findElement(titleBy).getText();
  }

  /**
   * Gets the product description.
   * @return the product description.
   */
  public String getProductDescription() {
    logger.debug("Get the Title");
    return container.findElement(productDescriptionBy).getText();
  }

  /**
   * Add the number of products to Cart.
   * @return StoreResultItem.
   */
  public StoreResultItem selectNumberOfProductToAddToCart(String value) {
    logger.debug("Add the number of products to Cart: " + value);
    WebDriverWait wait = new WebDriverWait(DriverBase.getDriver(), TIMEOUT_TO_LOAD_COMPONENT);
    WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropDownOfProductBy));
    logger.debug("Open the dropdown");
    dropdown.click();
    List<WebElement> options = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(numberOfProductBy));
    for (WebElement option : options) {
      if (option.getText().equals(value)) {
        logger.debug("Selecting value: " + value);
        option.click();
        break;
      }
    }
    return this;
  }

  /**
   * Adds product to Cart.
   * @return the product description.
   */
  public StorePage addProductToCart() {
    logger.debug("Add product to Cart");
    container.findElement(addToCartBy).click();
    StorePage storePage = new StorePage();
    storePage.get();
    return storePage;
  }

  @Override
  protected void isLoaded() throws Error {
    // To be implemented

    try {
      // verify that each element is displayed before continuing
      container.findElement(titleBy);
      logger.debug("The title is displayed");
      container.findElement(pictureBy);
      logger.debug("The image is displayed");
      container.findElement(productDescriptionBy);
      logger.debug("The product description is displayed");
      container.findElement(addToCartBy);
      logger.debug("The add to cart button is displayed");
      container.findElement(inStockBy);
      logger.debug("The in stock button is displayed");
    } catch (Exception e) {
      throwNotLoadedException("The search result item was not loaded correctly", e);
    }

    logger.debug("SearchResultItem component was loaded correctly");
  }
}
