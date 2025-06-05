package GUI.View.Search;

import communication.requests.card_requests.ColorSort;
import communication.requests.card_requests.GetCardRequest;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import model.entities.card.Card;
import model.entities.card.components.CardSupertype;
import model.entities.card.components.CardType;
import networking.clients.card.CardClient;

import java.util.ArrayList;

public class SearchVM {
  private final CardClient cardClient;

  public SearchVM(CardClient cardClient) {
    this.cardClient = cardClient;
  }

  /*---------------------------------------*/
  private final StringProperty nameSearchProperty = new SimpleStringProperty();
  private final StringProperty textSearchProperty = new SimpleStringProperty();
  private final StringProperty setCodeProperty = new SimpleStringProperty();
  private final BooleanProperty whiteManaProperty = new SimpleBooleanProperty();
  private final BooleanProperty redManaProperty = new SimpleBooleanProperty();
  private final BooleanProperty blueManaProperty = new SimpleBooleanProperty();
  private final BooleanProperty blackManaProperty = new SimpleBooleanProperty();
  private final BooleanProperty greenManaProperty = new SimpleBooleanProperty();
  private final BooleanProperty colorlessManaProperty = new SimpleBooleanProperty();
  private final ObjectProperty<ColorSort> manaSortProperty = new SimpleObjectProperty<>();
  private final BooleanProperty commanderProperty = new SimpleBooleanProperty();
  private final ListProperty<String> subtypeProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
  private final ListProperty<CardType> typeProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
  private final ListProperty<CardSupertype> supertypeProperty = new SimpleListProperty<>(FXCollections.observableArrayList());

  public StringProperty nameSearchProperty() {return nameSearchProperty;}
  public StringProperty textSearchProperty() {return textSearchProperty;}
  public StringProperty setCodeProperty() {return setCodeProperty;}
  public BooleanProperty whiteManaProperty() {return whiteManaProperty;}
  public BooleanProperty redManaProperty() {return redManaProperty;}
  public BooleanProperty blueManaProperty() {return blueManaProperty;}
  public BooleanProperty blackManaProperty() {return blackManaProperty;}
  public BooleanProperty greenManaProperty() {return greenManaProperty;}
  public BooleanProperty colorlessManaProperty() {return colorlessManaProperty;}
  public ObjectProperty<ColorSort> manaSortProperty() {return manaSortProperty;}
  public BooleanProperty commanderProperty() {return commanderProperty;}
  public ListProperty<String> subtypeProperty() {return subtypeProperty;}
  public ListProperty<CardType> typeProperty() {return typeProperty;}
  public ListProperty<CardSupertype> supertypeProperty() {return supertypeProperty;}


  public void verifySearchParameters() {
    System.out.println("Name: " + nameSearchProperty);
    System.out.println("Text: " + textSearchProperty);
    System.out.println("Set Code: " + setCodeProperty);
    System.out.println("White Mana: " + whiteManaProperty);
    System.out.println("Red Mana: " + redManaProperty);
    System.out.println("Blue Mana: " + blueManaProperty);
    System.out.println("Black Mana: " + blackManaProperty);
    System.out.println("Green Mana: " + greenManaProperty);
    System.out.println("Colorless Mana: " + colorlessManaProperty);
    // TODO : MANA SORT PROPERTY
    System.out.println("Commander: " + commanderProperty);
    System.out.println("Subtypes: " + subtypeProperty);
    System.out.println("Types: " + typeProperty);
    System.out.println("Supertypes " + supertypeProperty);
  }

  public ArrayList<Card> search() {

    ArrayList<String> subtypeList = new ArrayList<>(subtypeProperty);
    ArrayList<CardType> typeList = new ArrayList<>(typeProperty);
    ArrayList<CardSupertype> superTypeList = new ArrayList<>(supertypeProperty);

    GetCardRequest cardRequest = new GetCardRequest(
        null,
        nameSearchProperty().get(),
        setCodeProperty.get(),
        textSearchProperty.get(),
        subtypeList,
        typeList,
        superTypeList,
        whiteManaProperty.get(),
        blueManaProperty.get(),
        blackManaProperty.get(),
        redManaProperty.get(),
        greenManaProperty.get(),
        colorlessManaProperty.get(),
        commanderProperty.get());

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
