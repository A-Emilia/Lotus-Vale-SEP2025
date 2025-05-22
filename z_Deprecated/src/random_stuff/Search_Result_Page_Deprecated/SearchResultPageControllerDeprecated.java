package random_stuff.Search_Result_Page_Deprecated;

import GUI.Shared.ViewController;
import GUI.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.entities.card.Card;
import java.util.ArrayList;
import java.util.List;

public class SearchResultPageControllerDeprecated implements ViewController {
  private final SearchResultPageVMDeprecated vm;
  private final ArrayList<Card> searchResult;
  private int pageNo = 0;
  private final int cardsPerPage = 8;

  public SearchResultPageControllerDeprecated(SearchResultPageVMDeprecated vm, ArrayList<Card> searchResult) {
    this.vm = vm;
    // TODO Remove all additional copies of the same card.
    searchResult.removeIf(card -> card.getMultiverseId() == 0);
    this.searchResult = searchResult;
  }

  /*---------------------------------------*/
  public Button loginButton;
  public Button homeButton;
  public Button searchMenuButton;
  @FXML
  public GridPane searchResultBox;
  public Button nextButton;
  public Button previousButton;
  public Button collectionMenuButton;

  public void initialize() {
    renderCards();
  }

  public void loginButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showView(ViewHandler.ViewType.LOGIN);
  }

  public void homeButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showView(ViewHandler.ViewType.MAIN);
  }

  public void searchMenuButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showView(ViewHandler.ViewType.SEARCH);
  }

  public void collectionMenuButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showView(ViewHandler.ViewType.COLLECTION);
  }

  public void renderCards() {
    searchResultBox.getChildren().clear();

    List<Card> pageCards = getCurrentPageCards();

    // This is some of the worst code I have ever written.
    for (int i = 0; i < pageCards.size(); i++) {
      Card card = pageCards.get(i);
      ImageView cardImage = new ImageView(new Image(card.getImgUrl()));
      cardImage.setFitWidth(233);
      cardImage.setFitHeight(311);
      cardImage.setPreserveRatio(true);

      int row = 2;
      if (i > 3) {
        row = 12;
      }

      int col = 1+(5*i);
      if(i == 0 || i == 4) {
        col = 1;
      } else if (i > 4) {
        col = 1+(5*(i%4));
      }

      searchResultBox.getChildren().add(cardImage);
      GridPane.setRowIndex(cardImage, row);
      GridPane.setColumnIndex(cardImage, col);
      GridPane.setRowSpan(cardImage, 5);
      GridPane.setColumnSpan(cardImage,4);
    }
  }

  public boolean hasPreviousPage() {
    return pageNo > 0;
  }

  public boolean hasNextPage() {
    return (pageNo + 2) * cardsPerPage < searchResult.size();
  }

  public void nextButtonPressed(ActionEvent actionEvent) {
    if (hasNextPage()) {
      pageNo++;
      renderCards();
    }
  }

  public void previousButtonPressed(ActionEvent actionEvent) {
    if (hasPreviousPage()) {
      pageNo--;
      renderCards();
    }
  }

  public List<Card> getCurrentPageCards() {
    int start = pageNo * cardsPerPage;
    // Prevents out of bounds
    int end = Math.min(start+cardsPerPage, searchResult.size());
    return searchResult.subList(start, end);
  }
}
