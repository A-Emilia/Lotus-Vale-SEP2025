package GUI.Search_Result_Page;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.entities.card.Card;

import java.util.ArrayList;

public class SearchResultPageVM {
  private final ArrayList<Card> searchResult;
  private final ObservableList<Card> displayableCards = FXCollections.observableArrayList();

  public SearchResultPageVM(ArrayList<Card> searchResult) {
    this.searchResult = searchResult;
    searchResult.forEach((card -> {
      if(!(card.getMultiverseId() == 0)) {
        displayableCards.add(card);
      }
    }));
  }

  public ObservableList<Card> displayableCardsProperty() {return displayableCards;}
}
