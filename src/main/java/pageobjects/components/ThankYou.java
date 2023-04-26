package pageobjects.components;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import pageobjects.base.AbstractComponent;
import pageobjects.pages.StorePage;
import utils.TestReporter;

public class ThankYou extends AbstractComponent {

  //Selectors
  private static final By titleBy = By.cssSelector("div.MuiDialogTitle-root > h2");
  private static final By descriptionBy = By.cssSelector("div.MuiDialogContent-root > p");
  private static final By pictureBy = By.tagName("img");
  private static final By awesomeButtonBy = By.cssSelector("[type='button']");

  public ThankYou(WebElement container) {
    super(container);
  }

  /**
   * Gets title.
   * @return the title.
   */
  public String getTitle() {
    logger.debug("Get title");
    return container.findElement(titleBy).getText();
  }

  /**
   * Gets description.
   * @return Thank You Message.
   */
  public String getDescription() {
    logger.debug("Get description");
    return container.findElement(descriptionBy).getText();
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
   * Goes to store page.
   * @return Instance of StorePage.
   */
  public StorePage goToStore() {
    TestReporter.addInfoToReport("Go to store page");
    container.findElement(awesomeButtonBy).click();
    StorePage storePage = new StorePage();
    storePage.get();
    return storePage;
  }

  @Override
  protected void isLoaded() throws Error {
    try {
      // verify that each element is displayed before continuing
      container.findElement(titleBy);
      logger.debug("The title is displayed");
      container.findElement(descriptionBy);
      logger.debug("The description message is displayed");
      container.findElement(pictureBy);
      logger.debug("The picture is displayed");
      container.findElement(awesomeButtonBy);
      logger.debug("The awesome button is displayed");
    } catch (Exception e) {
      throwNotLoadedException("The ThankYou component was not loaded correctly", e);
    }

    logger.debug("ThankYou component was loaded correctly");
  }
}
