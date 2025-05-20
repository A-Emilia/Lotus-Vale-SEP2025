package GUI.Login_Page;

import communication.requests.user_requests.LoginRequest;
import communication.requests.user_requests.RegisterRequest;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import networking.user.UserClient;

public class LoginPageVM {
  private final UserClient userClient;

  public LoginPageVM(UserClient userClient) {
    this.userClient = userClient;
  }

  /*---------------------------------------*/
  private final StringProperty usernameProperty = new SimpleStringProperty();
  private final StringProperty passwordProperty = new SimpleStringProperty();

  public StringProperty usernameProperty() {
    return usernameProperty;
  }

  public StringProperty passwordProperty() {
    return passwordProperty;
  }

  public void login() {
    LoginRequest loginRequest = new LoginRequest(usernameProperty.get(), passwordProperty.get());

    // TODO More logic

    userClient.login(loginRequest);
  }

  public void register() {
    RegisterRequest registerRequest = new RegisterRequest(usernameProperty.get(), passwordProperty().get());

    userClient.register(registerRequest);
  }
}
