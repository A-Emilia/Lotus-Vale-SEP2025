package GUI.Login_Page;

import GUI.Shared.ViewController;
import GUI.ViewHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import state.AppState;

public class LoginPageController implements ViewController {
  private final LoginPageVM vm;

  public LoginPageController(LoginPageVM vm) {
    this.vm = vm;
  }

  /*---------------------------------------*/
  public Button homeButton;
  public Button loginButton;
  public Button searchMenuButton;
  public Button loginConfirmButton;
  public TextField usernameField;
  public TextField passwordField;
  public Button registerButtonConfirm;
  public Button collectionMenuButton;

  public void initialize() {
    usernameField.textProperty().bindBidirectional(vm.usernameProperty());
    passwordField.textProperty().bindBidirectional(vm.passwordProperty());
  }

  public void loginButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showView(ViewHandler.ViewType.LOGIN);
  }

  public void homeButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showView(ViewHandler.ViewType.MAIN);
  }

  public void searchMenuButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showView(ViewHandler.ViewType.SEARCH);
  }

  public void collectionMenuButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showView(ViewHandler.ViewType.COLLECTION);
  }

  public void loginConfirmButtonPressed(ActionEvent actionEvent) {
    vm.login();
  }

  public void registerConfirmButtonPressed(ActionEvent actionEvent) {
    vm.register();
  }
}
