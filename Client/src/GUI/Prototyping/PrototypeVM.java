package GUI.Prototyping;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import networking.card.CardClient;

public class PrototypeVM {
  private final CardClient cardClient;

  public PrototypeVM(CardClient cardClient) {
    this.cardClient = cardClient;
  }

  /*---------------------------------------*/

  private final StringProperty searchProperty = new SimpleStringProperty();



  public StringProperty searchProperty() {
    return searchProperty;
  }

  public void search() {
    System.out.println(searchProperty.get());
  }
}
