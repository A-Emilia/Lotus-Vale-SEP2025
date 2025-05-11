package GUI.Prototyping;

import com.google.gson.Gson;
import communication.requests.card_requests.GetCardRequest;
import communication.requests.card_requests.GetLotusRequest;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.entities.card.Card;
import networking.card.CardClient;
import networking.lotus.LotusClient;

public class PrototypeVM {
  private final LotusClient lotusClient;
  private final Gson gson;

  public PrototypeVM(LotusClient lotusClient) {
    this.lotusClient = lotusClient;
    this.gson = new Gson();
  }

  /*---------------------------------------*/

  private final StringProperty searchProperty = new SimpleStringProperty();



  public StringProperty searchProperty() {
    return searchProperty;
  }

  public void search() {


    GetLotusRequest lotusRequest = new GetLotusRequest(searchProperty.get(), "LEA");
    // Should GSON serialization really happen here?
    // I feel like the packaging should be done in the client.
    try {
      Card lotus = lotusClient.getLotus(lotusRequest);
      System.out.println(lotus);
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
