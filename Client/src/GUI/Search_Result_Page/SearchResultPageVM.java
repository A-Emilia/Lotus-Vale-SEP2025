package GUI.Search_Result_Page;

import communication.requests.card_requests.AddCardRequest;
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

public class SearchResultPageVM {
  private final ArrayList<Card> searchResult;
  private final ObservableList<Card> displayableCards = FXCollections.observableArrayList();
  private final CardClient cardClient;
  private final ReadOnlyBooleanWrapper loggedIn = new ReadOnlyBooleanWrapper();
  private final ReadOnlyStringWrapper username = new ReadOnlyStringWrapper("Login");

  public SearchResultPageVM(ArrayList<Card> searchResult, CardClient cardClient) {
    this.searchResult = searchResult;
    this.cardClient = cardClient;
    searchResult.forEach((card -> {
      if(!(card.getMultiverseId() == 0)) {
        displayableCards.add(card);
      }
    }));

    User user = AppState.getLoggedInUser();
    loggedIn.set(user != null);
    username.set(user != null ? user.getUsername() : "Login");

    ObjectProperty<User> loggedInUser = new SimpleObjectProperty<>();
    loggedInUser.addListener((observable, previousUser, newUser) -> {
      loggedIn.set(newUser != null);
      username.set(newUser != null ? newUser.getUsername() : "Login");
    });
  }

  public ObservableList<Card> displayableCardsProperty() {return displayableCards;}

  public void addToCollection(ArrayList<Integer> selectedCardIds) {
    CollectionTarget target = new CollectionTarget(TargetType.MAIN_COLLECTION, null, null);
    AddCardRequest addCardRequest = new AddCardRequest(AppState.getLoggedInUser().getId(), selectedCardIds, target);

    cardClient.addCard(addCardRequest);
  }

  public ReadOnlyBooleanProperty loggedInProperty() {
    return loggedIn.getReadOnlyProperty();
  }

  public ReadOnlyStringWrapper usernameProperty() {
    return username;
  }
}
