package GUI.Search_Page;

import communication.requests.card_requests.GetCardRequest;
import javafx.beans.property.*;
import model.entities.card.Card;
import model.entities.user.User;
import networking.card.CardClient;
import state.AppState;

import java.util.ArrayList;

public class SearchPageVM {
  private final CardClient cardClient;
  private final ReadOnlyBooleanWrapper loggedIn = new ReadOnlyBooleanWrapper();
  private final ReadOnlyStringWrapper username = new ReadOnlyStringWrapper("Login");

  public SearchPageVM(CardClient cardClient) {
    this.cardClient = cardClient;

    User user = AppState.getLoggedInUser();
    loggedIn.set(user != null);
    username.set(user != null ? user.getUsername() : "Login");

    ObjectProperty<User> loggedInUser = new SimpleObjectProperty<>();
    loggedInUser.addListener((observable, previousUser, newUser) -> {
      loggedIn.set(newUser != null);
      username.set(newUser != null ? newUser.getUsername() : "Login");
    });
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

    GetCardRequest cardRequest = new GetCardRequest(null, nameSearchProperty().get(), null, textSearchProperty.get());

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

  public ReadOnlyBooleanProperty loggedInProperty() {
    return loggedIn.getReadOnlyProperty();
  }

  public ReadOnlyStringWrapper usernameProperty() {
    return username;
  }
}
