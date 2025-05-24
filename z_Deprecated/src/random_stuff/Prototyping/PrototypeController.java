package random_stuff.Prototyping;

import GUI.Shared.ViewController;
import GUI.Shared.ViewType;
import GUI.ViewHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PrototypeController implements ViewController {
  private final PrototypeVM vm;

  public PrototypeController(PrototypeVM vm) {
    this.vm = vm;
  }

  /*---------------------------------------*/
  public TextField searchField;
  public Button searchButton;
  public ImageView cardDisplay;
  public Button registerButton;
  public Button homeButton;
  public Button loginButton;
  public Button prototypeButton;
  public Button searchMenuButton;


  public void initialize() {
    searchField.textProperty().bindBidirectional(vm.searchProperty());

  }

  public void searchButtonPressed(ActionEvent actionEvent) {
    vm.search();
    cardDisplay.setImage(new Image("https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=600&type=card"));
  }

  public void loginButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showView(ViewType.LOGIN);
  }

  public void prototypeButtonPressed(ActionEvent actionEvent) {
    //ViewHandler.showView(ViewHandler.ViewType.PROTOTYPE);
  }

  public void registerButtonPressed(ActionEvent actionEvent) {
  }

  public void homeButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showView(ViewType.MAIN);
  }

  public void searchMenuButtonPressed(ActionEvent actionEvent) {
    ViewHandler.showView(ViewType.SEARCH);
  }
}
