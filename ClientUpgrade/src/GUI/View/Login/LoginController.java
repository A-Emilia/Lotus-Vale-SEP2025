package GUI.View.Login;

import GUI.Shared.ViewType;
import GUI.ViewHandler;
import communication.Response;
import communication.ResponseType;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.entities.user.User;
import state.AppState;

import java.net.SocketTimeoutException;

public class LoginController {
  private LoginVM vm;

  public TextField usernameField;
  public PasswordField passwordField;
  public Button loginConfirmButton;
  public Button registerButtonConfirm;
  public Label errorLabel;

  public void init(LoginVM loginPageVM) {
    this.vm = loginPageVM;

    usernameField.textProperty().bindBidirectional(vm.usernameFieldProperty());
    passwordField.textProperty().bindBidirectional(vm.passwordFieldProperty());
  }

  public void loginConfirmButtonPressed(ActionEvent actionEvent) {
    try {
      Response res = vm.login();

      if (res.type() == ResponseType.OK) {
        AppState.getInstance().login((User) res.payload());
        ViewHandler.switchView(ViewType.MAIN);
      } else {
        errorLabel.textProperty().set((String) res.payload());
      }
    } catch (SocketTimeoutException e) {
      errorLabel.textProperty().set("Connection timeout.");
    }
  }

  public void registerConfirmButtonPressed(ActionEvent actionEvent) {
    if (vm.register()) {
      ViewHandler.switchView(ViewType.MAIN);
    } else {
      errorLabel.textProperty().set("Unknown Error Occurred.");
    }
  }
}
