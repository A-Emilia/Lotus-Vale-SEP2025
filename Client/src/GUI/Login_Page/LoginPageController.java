package GUI.Login_Page;

import GUI.Shared.ViewController;
import GUI.Shared.ViewType;
import GUI.ViewHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
  public Button myCardsMenuButton;
  public Button collectionsMenuButton;
  public Button decksMenuButton;

  public void initialize() {
    usernameField.textProperty().bindBidirectional(vm.usernameFieldProperty());
    passwordField.textProperty().bindBidirectional(vm.passwordFieldProperty());

    /*
     Initializing things I am not talented enough at making persist across multiple views.
     */
    myCardsMenuButton.disableProperty().bind(vm.loggedInProperty().not());
    collectionsMenuButton.disableProperty().bind(vm.loggedInProperty().not());
    loginButton.textProperty().bind(vm.usernameProperty());
    loginButton.disableProperty().bind(vm.loggedInProperty());
  }

  public void loginButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showView(ViewType.LOGIN);
  }

  public void homeButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showView(ViewType.MAIN);
  }

  public void searchMenuButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showView(ViewType.SEARCH);
  }

  public void myCardsMenuButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showView(ViewType.MY_CARDS);
  }

  public void collectionsMenuButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showView(ViewType.COLLECTIONS);
  }

  public void decksMenuButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showView(ViewType.DECKS);
  }

  public void loginConfirmButtonPressed(ActionEvent actionEvent) {
    vm.login();
    ViewHandler.showView(ViewType.MAIN);
  }

  public void registerConfirmButtonPressed(ActionEvent actionEvent) {
    vm.register();
    ViewHandler.showView(ViewType.MAIN);
  }
}
