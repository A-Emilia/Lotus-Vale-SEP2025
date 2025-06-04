package GUI.View.Login;

import GUI.Shared.ViewType;
import GUI.ViewHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    if (vm.login()) {
      ViewHandler.switchView(ViewType.MAIN);
    } else {
      errorLabel.textProperty().set("Unknown Error Occurred.");
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
