package GUI.Collection_Page;

import communication.requests.collection_requests.GetCollectionRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.entities.card.Card;
import networking.card.CardClient;
import networking.card.TCPCardClient;
import state.AppState;

import java.util.ArrayList;

public class CollectionPageVM {
  private final CardClient cardClient;
  private final ObservableList<Card> cards = FXCollections.observableArrayList();

  public CollectionPageVM(CardClient cardClient) {
    this.cardClient = cardClient;
    ArrayList<Card> cardArrayList = cardClient.getCollection(new GetCollectionRequest(AppState.getLoggedInUser().getId()));
    cards.addAll(cardArrayList);
  }

  /*---------------------------------------*/
  public ObservableList<Card> cardProperty() {return cards;}

}
