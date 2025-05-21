package GUI.Search_Result_Page;

import communication.requests.card_requests.AddCardRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.entities.card.Card;
import networking.card.CardClient;
import state.AppState;

import java.util.ArrayList;

public class SearchResultPageVM {
  private final ArrayList<Card> searchResult;
  private final ObservableList<Card> displayableCards = FXCollections.observableArrayList();
  private final CardClient cardClient;

  public SearchResultPageVM(ArrayList<Card> searchResult, CardClient cardClient) {
    this.searchResult = searchResult;
    this.cardClient = cardClient;
    searchResult.forEach((card -> {
      if(!(card.getMultiverseId() == 0)) {
        displayableCards.add(card);
      }
    }));
  }

  public ObservableList<Card> displayableCardsProperty() {return displayableCards;}

  public void addToCollection(ArrayList<Integer> selectedCardIds) {
    AddCardRequest addCardRequest = new AddCardRequest(AppState.getLoggedInUser().getId(), selectedCardIds, null);

    cardClient.addCard(addCardRequest);
  }
}
