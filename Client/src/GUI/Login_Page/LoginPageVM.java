package GUI.Login_Page;

import communication.requests.user_requests.LoginRequest;
import communication.requests.user_requests.RegisterRequest;
import javafx.beans.property.*;
import model.entities.user.User;
import networking.user.UserClient;
import state.AppState;

public class LoginPageVM {
  private final UserClient userClient;
  private final ReadOnlyBooleanWrapper loggedIn = new ReadOnlyBooleanWrapper();
  private final ReadOnlyStringWrapper username = new ReadOnlyStringWrapper("Login");
  private final StringProperty usernameFieldProperty = new SimpleStringProperty();
  private final StringProperty passwordFieldProperty = new SimpleStringProperty();

  public LoginPageVM(UserClient userClient) {
    this.userClient = userClient;

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

  public StringProperty usernameFieldProperty() {
    return usernameFieldProperty;
  }

  public StringProperty passwordFieldProperty() {
    return passwordFieldProperty;
  }

  public ReadOnlyBooleanProperty loggedInProperty() {
    return loggedIn.getReadOnlyProperty();
  }

  public ReadOnlyStringWrapper usernameProperty() {
    return username;
  }

  public void login() {
    LoginRequest loginRequest = new LoginRequest(usernameFieldProperty.get(), passwordFieldProperty.get());

    // TODO More logic

    userClient.login(loginRequest);
  }

  public void register() {
    RegisterRequest registerRequest = new RegisterRequest(usernameFieldProperty.get(), passwordFieldProperty().get());

    userClient.register(registerRequest);
  }
}
