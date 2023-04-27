package pageobjects.components;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobjects.base.AbstractComponent;

public class StoreResultList extends AbstractComponent {

  // Selectors
  private static final By resultItemsBy = By.cssSelector("[data-test-name='product-card']");

  // Components
  private final ArrayList<StoreResultItem> listOfResults;

  public StoreResultList(WebElement container) {
    super(container);
    listOfResults = new ArrayList<>();
  }

  /**
   * Initializes the component without container.
   */
  public StoreResultList() {
    super(null);
    listOfResults = new ArrayList<>();
  }

  /**
   * Get a StoreResultItem component.
   * @param index the index of the result item
   * @return a StoreResultItem
   */
  public StoreResultItem getResultOfProducts(int index) {
    logger.debug("Get result item of the products: {}", index);
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
    } catch (Exception e) {
      throwNotLoadedException("The store result list was not loaded correctly", e);
    }

    // initializes each result item and add it to the list
    int i = 0;
    for (WebElement resultItemContainer: container.findElements(resultItemsBy)) {
      logger.debug("Adding store result item {} to the store result list", i);
      i++;
      StoreResultItem resultItem = new StoreResultItem(resultItemContainer);
      resultItem.get();
      listOfResults.add(resultItem);
    }

    logger.debug("SearchResultList component was loaded correctly");
  }
}

