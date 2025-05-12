package GUI.Login_Page;

import GUI.Shared.ViewController;
import GUI.ViewHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class LoginPageController implements ViewController {
  private final LoginPageVM vm;

  public LoginPageController(LoginPageVM vm) {
    this.vm = vm;
  }

  /*---------------------------------------*/
  public Button registerButton;
  public Button homeButton;
  public Button loginButton;
  public Button prototypeButton;
  public Button searchMenuButton;

  public void initialize() {

  }

  public void loginButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showView(ViewHandler.ViewType.LOGIN);
  }

  public void prototypeButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showView(ViewHandler.ViewType.PROTOTYPE);
  }

  public void registerButtonPressed(ActionEvent actionEvent) {
  }

  public void homeButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showView(ViewHandler.ViewType.MAIN);
  }

  public void searchMenuButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showView(ViewHandler.ViewType.SEARCH);
  }
}
