package GUI.Main_Page;

import GUI.ViewHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;



public class MainPageController {
  public Button loginButton;
  public Button prototypeButton;

  public void loginButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showView(ViewHandler.ViewType.LOGIN);
  }

  public void prototypeButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showView(ViewHandler.ViewType.PROTOTYPE);
  }
}
