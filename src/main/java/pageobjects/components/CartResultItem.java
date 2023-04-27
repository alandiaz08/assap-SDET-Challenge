package pageobjects.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobjects.base.AbstractComponent;
import pageobjects.pages.CartPage;


public class CartResultItem extends AbstractComponent {

  // Selectors
  private static final By productBy = By.cssSelector("tr > td:nth-child(1)");
  private static final By quantityBy = By.cssSelector("tr > td:nth-child(2)");
  private static final By inStockBy = By.cssSelector("tr > td:nth-child(3)");
  private static final By removeBy = By.cssSelector("tr > td:nth-child(4)");
  private static final By resultListContainerBy = By.cssSelector("tbody");



  /**
   * Initializes the selectors specific to the search result item on the search results page.
   * @param container the container of the Search Result Item
   */
  public CartResultItem(WebElement container) {
    super(container);

    // initializes selectors to be used by the super class
  }

  /**
   * Gets product.
   * @return the product.
   */
  public String getProducts() {
    logger.debug("Get product");
    return container.findElement(productBy).getText();
  }

  /**
   * Gets the quantity the product.
   * @return the quantity the product.
   */
  public String getQuantity() {
    logger.debug("Get the quantity the product");
    return container.findElement(quantityBy).getText();
  }

  /**
   * Gets the product number stocks.
   * @return the title.
   */
  public String getStock() {
    logger.debug("Get the product number stocks");
    return container.findElement(inStockBy).getText();
  }

  /**
   * Gets the cuisine tag.
   * @return the title.
   */
  public CartPage removeProduct() {
    logger.debug("Get the Title");
    container.findElement(removeBy).click();
    CartPage cartPage = new CartPage();
    cartPage.get();
    return cartPage;
  }

  @Override
  protected void isLoaded() throws Error {
    // To be implemented

    try {
      // verify that each element is displayed before continuing
      container.findElement(productBy);
      logger.debug("The product name is displayed");
      container.findElement(quantityBy);
      logger.debug("The quantity is displayed");
      container.findElement(inStockBy);
      logger.debug("The stock value is displayed");
      container.findElement(removeBy);
      logger.debug("The remove button is displayed");
    } catch (Exception e) {
      throwNotLoadedException("The Cart result item was not loaded correctly", e);
    }

    logger.debug("CartResultItem component was loaded correctly");
  }
}
