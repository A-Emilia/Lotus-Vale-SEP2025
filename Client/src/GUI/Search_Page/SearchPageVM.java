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
  private final StringProperty nameSearchProperty = new SimpleStringProperty();
  private final StringProperty textSearchProperty = new SimpleStringProperty();

  public StringProperty nameSearchProperty() {
    return nameSearchProperty;
  }

  public StringProperty textSearchProperty() {
    return textSearchProperty;
  }


  public ArrayList<Card> search() {

    GetCardRequest cardRequest = new GetCardRequest(nameSearchProperty().get(), null, textSearchProperty.get());

    try {
      ArrayList<Card> cards = cardClient.getCard(cardRequest);

      for (Card card : cards) {
        System.out.println(card.toString());
      }

      return cards;
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
