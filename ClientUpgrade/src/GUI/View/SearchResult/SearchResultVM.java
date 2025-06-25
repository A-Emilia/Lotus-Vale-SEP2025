package GUI.View.SearchResult;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.entities.card.Card;
import networking.clients.card.CardClient;

import java.util.ArrayList;

public class SearchResultVM {
  private final ObservableList<Card> displayableCards = FXCollections.observableArrayList();
  private final CardClient cardClient;

  public SearchResultVM(CardClient cardClient, ArrayList<Card> searchResult) {
    this.cardClient = cardClient;
    searchResult.forEach((card -> {
      if (!(card.getMultiverseId() == 0)) {
        displayableCards.add(card);
      }
    }));
  }

  public ObservableList<Card> displayableCardsProperty() {return displayableCards;}
}
