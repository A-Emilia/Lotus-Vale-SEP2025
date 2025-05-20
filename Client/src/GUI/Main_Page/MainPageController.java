package GUI.Main_Page;

import GUI.Shared.ViewController;
import GUI.ViewHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;



public class MainPageController implements ViewController {
  private final MainPageVM vm;

  public MainPageController(MainPageVM vm) {
    this.vm = vm;
  }

  /*---------------------------------------*/
  public Button loginButton;
  public Button homeButton;
  public Button searchMenuButton;
  public Button collectionMenuButton;

  public void initialize() {

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
}
