package GUI.Prototyping;

import com.google.gson.Gson;
import communication.requests.card_requests.GetCardRequest;
import communication.requests.card_requests.GetLotusRequest;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.entities.card.Card;
import networking.card.CardClient;
import networking.card.TCPCardClient;
import networking.lotus.LotusClient;

import java.util.ArrayList;

public class PrototypeVM {
  //private final LotusClient lotusClient;
  private final TCPCardClient cardClient;
  private final Gson gson;

  public PrototypeVM(TCPCardClient cardClient) {
    this.cardClient = cardClient;
    //this.lotusClient = lotusClient;
    this.gson = new Gson();
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
