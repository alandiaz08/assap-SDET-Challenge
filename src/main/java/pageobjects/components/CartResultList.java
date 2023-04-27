package pageobjects.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobjects.base.AbstractComponent;

import java.util.ArrayList;

public class CartResultList extends AbstractComponent {

  // Selectors
  private static final By resultItemsBy = By.cssSelector("table > tbody > tr");
  private static final By buyBy = By.cssSelector("[class='buy-button']");

  // Components
  private final ArrayList<CartResultItem> listOfResults;

  public CartResultList(WebElement container) {
    super(container);
    listOfResults = new ArrayList<>();
  }

  /**
   * Initializes the component without container.
   */
  public CartResultList() {
    super(null);
    listOfResults = new ArrayList<>();
  }

  /**
   * Get a StoreResultItem component.
   * @param index the index of the result item
   * @return a StoreResultItem
   */
  public CartResultItem getResult(int index) {
    logger.debug("Get search result item: {}", index);
    return listOfResults.get(index);
  }

  /**
   * Get the number of store result items in the list.
   * @return number of results in the list
   */
  public int getNumberOfResults() {
    logger.debug("Get number of result items");
    return listOfResults.size();
  }


  @Override
  protected void isLoaded() throws Error {

    try {
      // verify that each element is displayed before continuing
      container.findElement(resultItemsBy);
      logger.debug("Cart result item is displayed");

      // initializes each result item and add it to the list
      int i = 0;
      for (WebElement resultItemContainer: container.findElements(resultItemsBy)) {
        logger.debug("Adding store result item {} to the store result list", i);
        i++;
        CartResultItem resultItem = new CartResultItem(resultItemContainer);
        resultItem.get();
        listOfResults.add(resultItem);
      }
    } catch (Exception noCartList) {
      try {
        logger.debug("Cart list result is not displayed, checking for empty list message",
                noCartList);
        container.findElement(buyBy).isDisplayed();
        logger.debug("Empty restaurant list message is displayed");
        logger.debug("Initializing the CartResultList with zero element");
      } catch (Exception e) {
        throwNotLoadedException("The Cart result list was not loaded correctly", e);
      }
    }

    logger.debug("SearchResultList component was loaded correctly");
  }
}
