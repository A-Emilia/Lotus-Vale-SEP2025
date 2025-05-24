package GUI.My_Cards_Page;

import communication.requests.card_requests.GetCardRequest;
import communication.requests.card_requests.target.CollectionTarget;
import communication.requests.card_requests.target.TargetType;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.entities.card.Card;
import model.entities.user.User;
import networking.card.CardClient;
import state.AppState;

import java.util.ArrayList;

public class MyCardsPageVM
{
  private final CardClient cardClient;
  private final ObservableList<Card> cards = FXCollections.observableArrayList();
  private final ReadOnlyBooleanWrapper loggedIn = new ReadOnlyBooleanWrapper();
  private final ReadOnlyStringWrapper username = new ReadOnlyStringWrapper("Login");

  public MyCardsPageVM(CardClient cardClient) {
    this.cardClient = cardClient;

    CollectionTarget target = new CollectionTarget(TargetType.MAIN_COLLECTION, null, AppState.getLoggedInUser().getId());

    ArrayList<Card> cardArrayList = cardClient.getCollection(new GetCardRequest(target, null, null, null));
    //ArrayList<Card> cardArrayList = cardClient.getCollection(new GetCollectionRequest(AppState.getLoggedInUser().getId()));
    cards.addAll(cardArrayList);

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
  public ObservableList<Card> cardProperty() {return cards;}

  public ReadOnlyBooleanProperty loggedInProperty() {
    return loggedIn.getReadOnlyProperty();
  }

  public ReadOnlyStringWrapper usernameProperty() {
    return username;
  }

}
