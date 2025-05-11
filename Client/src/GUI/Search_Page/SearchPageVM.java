package GUI.Search_Page;

import communication.requests.card_requests.GetCardRequest;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.entities.card.Card;
import networking.card.TCPCardClient;

import java.util.ArrayList;

public class SearchPageVM {
  private final TCPCardClient cardClient;

  public SearchPageVM(TCPCardClient cardClient) {
    this.cardClient = cardClient;
  }

  /*---------------------------------------*/
  private final StringProperty searchProperty = new SimpleStringProperty();
  public StringProperty searchProperty() {
    return searchProperty;
  }

  public void search() {

    GetCardRequest cardRequest = new GetCardRequest(searchProperty().get(), null, null);

    try {
      ArrayList<Card> cards = cardClient.getCard(cardRequest);

      for (Card card : cards) {
        System.out.println(card.toString());
      }

    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
