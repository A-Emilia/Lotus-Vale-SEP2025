package GUI.Collections_Page;

import javafx.beans.property.*;
import model.entities.user.User;
import state.AppState;

public class CollectionsPageVM {
  private final ReadOnlyBooleanWrapper loggedIn = new ReadOnlyBooleanWrapper();
  private final ReadOnlyStringWrapper username = new ReadOnlyStringWrapper("Login");

  public CollectionsPageVM() {
    User user = AppState.getLoggedInUser();
    loggedIn.set(user != null);
    username.set(user != null ? user.getUsername() : "Login");

    ObjectProperty<User> loggedInUser = new SimpleObjectProperty<>();
    loggedInUser.addListener((observable, previousUser, newUser) -> {
      loggedIn.set(newUser != null);
      username.set(newUser != null ? newUser.getUsername() : "Login");
    });
  }

  public ReadOnlyBooleanProperty loggedInProperty() {
    return loggedIn.getReadOnlyProperty();
  }

  public ReadOnlyStringWrapper usernameProperty() {
    return username;
  }
}
