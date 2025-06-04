package GUI.View.Login;

import GUI.Shared.ViewType;
import GUI.ViewHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {
  private LoginVM vm;

  public TextField usernameField;
  public TextField passwordField;
  public Button loginConfirmButton;
  public Button registerButtonConfirm;

  public void init(LoginVM loginPageVM) {
    this.vm = loginPageVM;

    usernameField.textProperty().bindBidirectional(vm.usernameFieldProperty());
    passwordField.textProperty().bindBidirectional(vm.passwordFieldProperty());
  }

  public void loginConfirmButtonPressed(ActionEvent actionEvent) {
    vm.login();
    ViewHandler.switchView(ViewType.MAIN);
  }

  public void registerConfirmButtonPressed(ActionEvent actionEvent) {
    vm.register();
    ViewHandler.switchView(ViewType.MAIN);
  }
}
